/*
 * DocumentBean.java   February 26,2018
 * 
 */
package undo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * @author George Anil John
 *
 */
public class DocumentBean implements Document {

	private File actionDocument;
	private String document_Url;

	/**
	 * 
	 * @param document_Url
	 */
	public DocumentBean(String document_Url) {
		try {
			this.document_Url = document_Url;
			actionDocument = new File(document_Url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param pos
	 * @param s
	 */
	public void insert(int pos, String s) {
		try {
			FileInputStream fstream = new FileInputStream(actionDocument);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String data = br.readLine();
			if(data!=null) {
				if (data.length() <= pos) {
					br.close();
					throw new IllegalStateException("error in insert:tried to insert in an illegal position");
				}
				String finalData = data.substring(0, pos) + s + data.substring(pos, data.length());
				FileWriter writer = new FileWriter(actionDocument);
				writer.write(finalData);
				writer.close();
			}
			br.close();
			fstream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param pos
	 * @param s
	 */
	public void delete(int pos, String s) {
		try {
			FileInputStream fstream = new FileInputStream(actionDocument);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String data = br.readLine();
			if(data!=null) {
				if (data.indexOf(s, pos) == -1) {
					br.close();
					throw new IllegalStateException("error in delete:the document doesn't have the string");
				}
				String finalData = data.substring(0, pos) + data.substring(pos + s.length(), data.length());
				FileWriter writer = new FileWriter(actionDocument);
				writer.write(finalData);
				writer.close();
			}
			br.close();
			fstream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void setDot(int pos) {
		try {
			RandomAccessFile randomFile = new RandomAccessFile(document_Url, "r");
			randomFile.seek(pos);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
