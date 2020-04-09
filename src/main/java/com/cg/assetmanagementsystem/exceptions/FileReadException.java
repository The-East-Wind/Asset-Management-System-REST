package com.cg.assetmanagementsystem.exceptions;

public class FileReadException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message=null;
	public FileReadException(){
		message = "Uh Oh!An error occured when logging you in.Please try again later";
	}
	@Override
	public String toString() {
		return message;
	}
	
}
