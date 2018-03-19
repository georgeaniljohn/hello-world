/*
 * UndoManagerBean.java   February 26,2018
 * 
 */
package undo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This is the implementation of <i>UndoManager</i> interface and performs change operations
 * 
 * @author George Anil John
 *
 */
public class UndoManagerBean implements UndoManager {

	private Document doc;
	private int bufferSize;
	private static Deque<Change> undoStack;
	private static Deque<Change> redoStack;

	/**
	 * 
	 * @param doc
	 * @param bufferSize
	 */
	public UndoManagerBean(Document doc, int bufferSize) {
		this.doc = doc;
		this.bufferSize = bufferSize;
		undoStack = new ArrayDeque<Change>();
		redoStack = new ArrayDeque<Change>();
	}

	/**
	 * 
	 */
	public void registerChange(Change change) {
		try {
			pushWithinBufferLimit(undoStack, change, bufferSize);
			if (change != null) {
				change.apply(doc);
			}
		} catch (Exception e) {
			throw new IllegalStateException("error in registerChange");
		}
	}

	/**
	 * 
	 */
	public boolean canUndo() {
		if (undoStack.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 */
	public void undo() {
		try {
			if (canUndo()) {
				Change change = undoStack.pop();
				if (change != null) {
					change.revert(doc);
				}
				pushWithinBufferLimit(redoStack, change, bufferSize);
			} else {
				throw new IllegalStateException("error in undo:nothing to undo");
			}
		} catch (Exception e) {
			throw new IllegalStateException("error in undo:change failed");
		}
	}

	/**
	 * 
	 */
	public boolean canRedo() {
		if (redoStack.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 */
	public void redo() {
		try {
			if (canRedo()) {
				Change change = redoStack.pop();
				if (change != null) {
					change.apply(doc);
				}
				pushWithinBufferLimit(undoStack, change, bufferSize);
			} else {
				throw new IllegalStateException("error in redo:nothing to redo");
			}
		} catch (Exception e) {
			throw new IllegalStateException("error in redo:change failed");
		}
	}

	/**
	 * 
	 * @param changeStack
	 * @param change
	 * @param bufferSize
	 */
	private void pushWithinBufferLimit(Deque<Change> changeStack, Change change, int bufferSize) {
		if (changeStack != null) {
			if (changeStack.size() == bufferSize) {
				changeStack.removeLast();
				changeStack.push(change);
			} else {
				changeStack.push(change);
			}
		}
	}
}
