package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import phases.PhaseHandler;


public class Win extends JPanel {
	
	private final String TITLE = "Workout Timer v1";
	public static final int WIDTH = 1280, HEIGHT = 760;
	
	public static Color bgColor = Color.black,
						textColor = Color.white,
						selectColor = Color.magenta,
						timerColor = Color.red,
						sectionColor = Color.white,
						pausedColor = Color.white,
						doneColor = Color.green,
						headerColor = Color.yellow;
	
	public static Font 	menuFont = new Font("Helvetica",Font.BOLD, 45),
						timerFont = new Font("Helvetica",Font.BOLD,200),
						sectionFont = new Font("Helvetica",Font.PLAIN,70),
						doneFont = new Font("Helvetica",Font.BOLD,200);
	
	
	
	protected PhaseHandler ph;
	private JFrame frame;
	
	public Win(){
		ph = new PhaseHandler();
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.add(this);
		frame.addKeyListener(ph);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void refresh(){
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		ph.paint(g);
	}
	
	public boolean isVisible(){
		return frame.isVisible();
	}
}
