package net.dzikoysk.panda.gui.util;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutputStream extends OutputStream {

	private final JTextArea textArea;

	public ConsoleOutputStream(JTextArea textArea){
		this.textArea = textArea;
	}

	@Override
	public void write(int c) throws IOException {
		this.textArea.append(Character.toString((char) c));
	}

}
