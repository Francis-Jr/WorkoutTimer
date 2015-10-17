package phases;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Win;


public class QuitMenu implements Phase {

	private PhaseHandler ph;
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		
		g.setFont(Win.menuFont);
		g.setColor(Win.textColor);
		g.drawString("really wanna quit?", 300, 300);
	}

	@Override
	public void calculate(long delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPhaseHandler(PhaseHandler a) {
		ph = a;
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
		switch(e.getKeyCode()){
		case KeyEvent.VK_ENTER:		quit();		break;
		case KeyEvent.VK_ESCAPE:	ph.setPhase(1);		break;
		}
	}

	private void quit(){
		System.exit(0);
		//TODO save WorkoutManager
	}
}
