package com.meta1203.ChessPlayer.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class Command implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object source = ae.getSource();
		String command = "";
		if (source instanceof JTextComponent) {
			command = ((JTextComponent)source).getText();
		} else {
			command = ((JTextComponent)((JComponent)source).getParent().getComponent(1)).getText();
		}
	}
}
