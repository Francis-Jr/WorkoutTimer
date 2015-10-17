package phases;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Vector;

import main.Win;
import workout.Workout;
import workout.WorkoutManager;
import workout.WorkoutSerializer;

public class WorkoutSelect implements Phase {

	private long timeCounter = 0;
	
	private PhaseHandler ph;
	private WorkoutPlayer wp = null;
	private boolean playing = false;
	private Vector<Workout> workouts = new Vector<Workout>(2);
	private int selected = 0;
	public WorkoutManager wm;
	
	public WorkoutSelect(){
		WorkoutSerializer ws = new WorkoutSerializer();
		wm = ws.readWorkoutManager();
		workouts = wm.getWorkouts();
		workouts.firstElement().setSelected(true);
	}
	
	@Override
	public void paint(Graphics g) {
		//TODO Überschrift Select
		if(playing){wp.paint(g);	return;}
		
		g.setColor(Win.bgColor);
		g.fillRect(0,0,Win.WIDTH,Win.HEIGHT);
		
		Enumeration<Workout> wnum = workouts.elements();
		int n = 0;
		while(wnum.hasMoreElements()){
			wnum.nextElement().menuPaint(g,n);
			n = n + 1;
		}
	}

	@Override
	public void calculate(long delta) {
		if(playing){wp.calculate(delta);	return;}
		
		timeCounter = timeCounter + delta;
	}

	@Override
	public void setPhaseHandler(PhaseHandler a) {
		ph = a;
	}

	@Override
	public void onStart() {
		timeCounter = 0;
	}
	
	public void loadWorkoutsFromFile(){
		WorkoutSerializer ws = new WorkoutSerializer();
		wm = ws.readWorkoutManager();
		workouts = wm.getWorkouts();
		workouts.firstElement().setSelected(true);
	}

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(playing){wp.keyPressed(e);	return;}
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:		workouts.elementAt(selected).setSelected(false);
									if(selected==0){workouts.lastElement().setSelected(true); selected = workouts.size()-1;}
									else {selected = selected - 1; workouts.elementAt(selected).setSelected(true);}
		break;
		case KeyEvent.VK_DOWN:		workouts.elementAt(selected).setSelected(false);
									if(selected==workouts.size()-1){workouts.firstElement().setSelected(true); selected=0;}
									else {selected = selected + 1; workouts.elementAt(selected).setSelected(true);}
		break;
		case KeyEvent.VK_ESCAPE:	ph.setPhase(1);
		break;
		case KeyEvent.VK_ENTER:		playWorkout(selected);	
		}
	}
	
	public void playWorkout(int a){
		wp = new WorkoutPlayer(workouts.elementAt(a));
		wp.setPhaseHandler(ph);
		playing = true;
	}
	
	public void stopPlaying(){
		playing = false;
	}

}
