/*
 * UndoManagerFactoryBean.java   February 26,2018
 * 
 */
package undo;

/**
 * This is the implementation of <i>UndoManagerFactory</i> interface and creates UndoManager
 * 
 * @author George Anil John
 *
 */
public class UndoManagerFactoryBean implements UndoManagerFactory {

	/**
	 * 
	 */
	public UndoManagerFactoryBean() {

	}

	/**
	 * 
	 */
	public UndoManager createUndoManager(Document doc, int bufferSize) {
		UndoManager undoManager = new UndoManagerBean(doc, bufferSize);
		return undoManager;
	}

	
}
