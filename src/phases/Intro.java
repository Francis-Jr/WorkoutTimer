package phases;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Win;


public class Intro implements Phase {

	private long duration = (long) 4e9, timer = 0;
	
	private PhaseHandler ph;
	
	public Intro(){
	}
	
	public void endIntro(){
		ph.setPhase(1);
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		
		g.setFont(Win.menuFont);
		g.setColor(Win.textColor);
		g.drawString("Intro", 300, 300);
	}

	@Override
	public void calculate(long delta) {
		timer = timer + delta;
		if(timer >= duration){
			endIntro();
		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		endIntro();
	}

	@Override
	public void setPhaseHandler(PhaseHandler a) {
		ph = a;
	}

}
