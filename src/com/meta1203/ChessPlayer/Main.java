package com.meta1203.ChessPlayer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	public static Board board = null;
	public static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) throws SecurityException, IOException {
		// Setup the logger
		logger.setLevel(Level.INFO);
		FileHandler fileTxt = new FileHandler("testing.log");
		logger.addHandler(fileTxt);
	}
}
