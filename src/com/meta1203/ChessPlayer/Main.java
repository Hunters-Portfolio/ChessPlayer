package com.meta1203.ChessPlayer;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.meta1203.ChessPlayer.ui.InterfaceWindow;

public class Main {
	// public static Board board = null;
	public static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) throws SecurityException, IOException {
		// Setup the logger
		logger.setLevel(Level.INFO);
		FileHandler fileTxt = new FileHandler("testing.log");
		logger.addHandler(fileTxt);

		// Launch the UI.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceWindow window = new InterfaceWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
