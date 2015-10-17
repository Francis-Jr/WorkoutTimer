package workout;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import main.Win;

public class AmountSection implements WorkoutSection, Serializable{

	/**
	 * Oct 9 15
	 */
	private static final long serialVersionUID = -2960374380671935721L;
	private Workout wo;
	private String title;
	private int amount;
	private boolean selected;
	
	public AmountSection(String a, int b, Workout c){
		title = a;
		amount = b;
		wo = c;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		
		g.setFont(Win.timerFont);
		g.setColor(Win.timerColor);
		g.drawString(""+amount, 450, 300);
		
		g.setFont(Win.sectionFont);
		g.setColor(Win.sectionColor);
		g.drawString(title, 450, 550);
	}

	@Override
	public void calculate(long delta) {
		
	}

	@Override
	public void togglePaused() {
		wo.nextSection();
	}

	@Override
	public void editPaint(Graphics g, int a, int b, Color c) {
		g.setFont(Win.menuFont);
		
		if(!selected)	g.setColor(Win.textColor);
		else if(b==3)	g.setColor(c);
		else if(b==0)	g.setColor(Win.selectColor);
		else			g.setColor(Win.textColor);
		g.drawString("" + amount, 325, 200+100*a);
		
		if(!selected)	g.setColor(Win.textColor);
		else if(b==4)	g.setColor(c);
		else if(b==0)	g.setColor(Win.selectColor);
		else			g.setColor(Win.textColor);
		g.drawString(title, 450, 200+100*a);
	}

	public void setSelected(boolean a){
		selected = a;
	}

	@Override
	public int getType() {
		return 2;
	}
	
}
