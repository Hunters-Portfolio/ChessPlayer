package com.meta1203.ChessPlayer.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class InterfaceWindow {

	public JFrame frame;
	private JTable board;
	protected JTextField input;
	protected JTextPane output;
	protected JButton btnSubmit;

	/**
	 * Create the application.
	 */
	public InterfaceWindow() {
		initialize();
	}
	
	public void addText(String text) {
		output.setText(output.getText() + text + "\n");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		board = new JTable();
		board.setFillsViewportHeight(true);
		board.setRowSelectionAllowed(false);
		board.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		board.setModel(new DefaultTableModel(
			new Object[][] {
				{"8", "", null, null, null, null, null, null, null},
				{"7", null, null, null, null, null, null, null, null},
				{"6", null, null, null, null, null, null, null, null},
				{"5", null, null, null, null, null, null, null, null},
				{"4", null, null, null, null, null, null, null, null},
				{"3", null, null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null, null},
				{"1", null, null, null, null, null, null, null, null},
				{"", "A", "B", "C", "D", "E", "F", "G", "H"},
			},
			new String[] {
				"none", "A", "A", "C", "D", "E", "F", "G", "H"
			}
		) {
			private static final long serialVersionUID = -6322696897479779586L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		board.getColumnModel().getColumn(1).setResizable(false);
		board.getColumnModel().getColumn(2).setResizable(false);
		board.getColumnModel().getColumn(3).setResizable(false);
		board.getColumnModel().getColumn(4).setResizable(false);
		board.getColumnModel().getColumn(5).setResizable(false);
		board.getColumnModel().getColumn(6).setResizable(false);
		board.getColumnModel().getColumn(7).setResizable(false);
		board.getColumnModel().getColumn(8).setResizable(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		board.setRowHeight(75);
		panel.add(board);
		
		JPanel form = new JPanel();
		panel.add(form);
		
		output = new JTextPane();
		output.setEditable(false);
		
		input = new JTextField();
		input.setColumns(80);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new Command());
		
		GroupLayout gl_form = new GroupLayout(form);
		gl_form.setHorizontalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addGroup(gl_form.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_form.createSequentialGroup()
							.addContainerGap()
							.addComponent(output, GroupLayout.PREFERRED_SIZE, 829, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_form.createSequentialGroup()
							.addContainerGap()
							.addComponent(input, GroupLayout.PREFERRED_SIZE, 805, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_form.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSubmit)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_form.setVerticalGroup(
			gl_form.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_form.createSequentialGroup()
					.addComponent(output, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(input, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubmit)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_form.setAutoCreateGaps(true);
		gl_form.setAutoCreateContainerGaps(true);
		form.setLayout(gl_form);
	}
}
