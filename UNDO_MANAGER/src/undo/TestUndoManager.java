package undo;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

/**
 * This class contains functions to test the UndoManager via JUnit
 * 
 * @author George Anil John
 *
 */
public class TestUndoManager {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private static String document_Url;
	
	static {
		//hard coded for the time being
		document_Url = "E:\\george\\workspace\\eclipse\\TRAX_TEST\\src\\undo\\data_file.txt";
	}
	
	/**
	 * 
	 */
	public  TestUndoManager() {
		
	}

	/**
	 * 
	 */
	@Test
	public void testEquals() {

		try {
			UndoManagerFactory factory = new UndoManagerFactoryBean();
			RandomAccessFile randomFile = new RandomAccessFile(document_Url, "rw");
			byte[] bytes = null;
			Document doc = new DocumentBean(document_Url);
			UndoManager manager = factory.createUndoManager(doc, 3);
			TestUndoManager testManager = new TestUndoManager();

			assertEquals("abcsample1defg",
					testManager.makeChange("abcdefg", "sample1", 3, manager, randomFile, bytes));
			assertEquals("abcdefg", testManager.makeUndo(manager, randomFile, bytes));
			assertEquals("abcsample1defg", testManager.makeRedo(manager, randomFile, bytes));
			
			if(randomFile!=null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Test
	public void testException() {
		exception.expect(IllegalStateException.class);
		RandomAccessFile randomFile = null;
		UndoManagerFactory factory = new UndoManagerFactoryBean();
		try {
			randomFile = new RandomAccessFile(document_Url, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bytes = null;
		Document doc = new DocumentBean(document_Url);
		UndoManager manager = factory.createUndoManager(doc, 3);

		TestUndoManager testManager = new TestUndoManager();
		testManager.makeChange("abcdefg", "sample1", 3, manager, randomFile, bytes);
		testManager.makeUndo(manager, randomFile, bytes);
		testManager.makeUndo(manager, randomFile, bytes);
		if(randomFile!=null) {
			try {
				randomFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param randomFile
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	private String getData(RandomAccessFile randomFile, byte[] bytes) throws Exception {
		bytes = new byte[Long.valueOf(randomFile.length()).intValue()];
		randomFile.seek(0);
		randomFile.read(bytes);
		String data = new String(bytes);
		return data;
	}

	/**
	 * 
	 * @param initialString
	 * @param insertString
	 * @param position
	 * @param manager
	 * @param randomFile
	 * @param bytes
	 * @return
	 */
	private String makeChange(String initialString, String insertString, int position, UndoManager manager,
			RandomAccessFile randomFile, byte[] bytes) {
		String data = "";
		try {

			randomFile.setLength(0);
			randomFile.writeBytes(initialString);
			Change change = new ChangeBean(UndoManagerConstant.INSERT, insertString, position);
			manager.registerChange(change);
			data = getData(randomFile, bytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 
	 * @param manager
	 * @param randomFile
	 * @param bytes
	 * @return
	 */
	private String makeUndo(UndoManager manager, RandomAccessFile randomFile, byte[] bytes) {
		String data = "";
		try {
			manager.undo();
			data = getData(randomFile, bytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 
	 * @param manager
	 * @param randomFile
	 * @param bytes
	 * @return
	 */
	private String makeRedo(UndoManager manager, RandomAccessFile randomFile, byte[] bytes) {
		String data = "";
		try {
			manager.redo();
			data = getData(randomFile, bytes);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
