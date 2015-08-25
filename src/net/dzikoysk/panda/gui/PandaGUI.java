package net.dzikoysk.panda.gui;

import net.dzikoysk.panda.Panda;
import net.dzikoysk.panda.PandaLoader;
import net.dzikoysk.panda.PandaScript;
import net.dzikoysk.panda.core.Core;
import net.dzikoysk.panda.gui.util.PositionUtils;
import net.wvffle.panda.gui.Version;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class PandaGUI extends JFrame {

	private TextPanel textPanel;
	private OutputPanel outputPanel;
	private JButton parseButton;

	public PandaGUI(){
		this.setTitle("PandaGUI");
		this.setSize(925, 500);
		PositionUtils.center(this);

		this.textPanel = new TextPanel();
		this.outputPanel = new OutputPanel();

		this.parseButton = new JButton("Parse");
		this.parseButton.setEnabled(false);
		this.parseButton.setPreferredSize(new Dimension(900, 35));
		this.parseButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		this.parseButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!parseButton.isEnabled()) return;
				parseButton.setEnabled(false);
				outputPanel.clear();

				final String source = textPanel.getSource();
				System.out.println("[INFO] Analyzing...");
				new Thread(new Runnable() {
					@Override
					public void run() {
						long l = System.currentTimeMillis();
						PandaScript script = PandaLoader.loadSimpleScript(source);
						System.out.println("[INFO] Success (" + (System.currentTimeMillis() - l) + "ms)!");
						if (script != null) {
							System.out.println("[INFO] Calling main()...");
							script.callMethod("main");
						}
					}
				}).start();
				parseButton.setEnabled(true);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		this.add(textPanel, BorderLayout.LINE_START);
		this.add(outputPanel, BorderLayout.LINE_END);
		this.add(parseButton, BorderLayout.PAGE_END);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		initPanda();
	}

	public void initPanda(){
		System.out.println("[INFO] Loading Panda " + Panda.PANDA_VERSION + "...");
		long l = System.currentTimeMillis();
		new Panda();
		
		Core.registerDefault();
		System.out.println("[INFO] Done (" + (System.currentTimeMillis() - l) + "ms)!");
		this.parseButton.setEnabled(true);
	}


	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() { 
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				new PandaGUI();
			}
		});
	}

}
