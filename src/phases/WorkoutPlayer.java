package phases;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import workout.Workout;


public class WorkoutPlayer implements Phase {

	private PhaseHandler ph;
	private Workout wo;
	
	public WorkoutPlayer(Workout a){
		wo = a;
		wo.setWorkoutPlayer(this);
	}
	
	@Override
	public void paint(Graphics g) {
		wo.paint(g);
	}

	@Override
	public void calculate(long delta) {
		wo.calculate(delta);
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
		case KeyEvent.VK_ESCAPE:	quitWorkout();		break;
		case KeyEvent.VK_SPACE:		wo.space();			break;
		}
	}

	public void quitWorkout() {
		ph.setPhase(1);
		
		((WorkoutSelect) ph.phases[2]).stopPlaying();
	}

}
