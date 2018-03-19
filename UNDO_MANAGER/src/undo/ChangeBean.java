/*
 * ChangeBean.java   February 26,2018
 * 
 */
package undo;

/**
 * 
 * @author George Anil John
 *
 */
public class ChangeBean implements Change {

	private UndoManagerConstant editType;
	private String stringInput;
	private int pos;

	/**
	 * 
	 * @param editType
	 * @param stringInput
	 * @param pos
	 */
	public ChangeBean(UndoManagerConstant editType, String stringInput, int pos) {
		this.editType = editType;
		this.stringInput = stringInput;
		this.pos = pos;
	}

	/**
	 * 
	 * @return
	 */
	public UndoManagerConstant getEditType() {
		return editType;
	}

	/**
	 * 
	 * @param editType
	 */
	public void setEditType(UndoManagerConstant editType) {
		this.editType = editType;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getStringInput() {
		return stringInput;
	}
	
	/**
	 * 
	 * @param stringInput
	 */
	public void setStringInput(String stringInput) {
		this.stringInput = stringInput;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * 
	 * @param pos
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}

	/**
	 * 
	 * @param doc
	 */
	public void apply(Document doc) {
		try {
			if (editType==UndoManagerConstant.INSERT) {
				doc.insert(pos, stringInput);
			} else if (editType==UndoManagerConstant.DELETE) {
				doc.delete(pos, stringInput);
			}
		} catch (Exception e) {
			throw new IllegalStateException("error in apply:the change cannot be applied to document");
		}
	}

	/**
	 * 
	 * @param doc
	 */
	public void revert(Document doc) {
		try {
			if (editType==UndoManagerConstant.INSERT) {
				doc.delete(pos, stringInput);
			} else if (editType==UndoManagerConstant.DELETE) {
				doc.insert(pos, stringInput);
			}
		} catch (Exception e) {
			throw new IllegalStateException("error in apply:the change cannot be reverted in document");
		}
	}

}
