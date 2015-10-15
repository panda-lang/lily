package net.dzikoysk.panda.gui;

import net.dzikoysk.panda.gui.util.ConsoleOutputStream;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.PrintStream;

public class OutputPanel extends JPanel {

	private JTextArea textArea;
	private JScrollPane scrollPane;
	private ConsoleOutputStream out;
	private PrintStream ps;

	public OutputPanel(){
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setFont(new Font("Consolas", Font.PLAIN, 10));

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(320, 428));

		this.out = new ConsoleOutputStream(textArea);
		this.ps = new PrintStream(out);

		this.add(scrollPane);
		System.setOut(ps);
	}

	public void clear(){
		this.textArea.setText("");
	}

}
