package phases;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Vector;

import main.Win;
import workout.Workout;
import workout.WorkoutManager;
import workout.WorkoutSerializer;


public class Edit implements Phase {

	private PhaseHandler ph;
	private WorkoutManager wm;
	private Vector<Workout> workouts = new Vector<Workout>(2);
	private int selected = 0;
	
	private WorkoutEditor we = new WorkoutEditor();
	private boolean editing = false;
	
	public Edit(){
		WorkoutSerializer ws = new WorkoutSerializer();
		wm = ws.readWorkoutManager();
		workouts = wm.getWorkouts();
		workouts.add(new Workout("Create new Workout"));
		workouts.firstElement().setSelected(true);
	}
	
	@Override
	public void paint(Graphics g) {
		if(editing){we.paint(g);	return;}
		
		g.setColor(Win.bgColor);
		g.fillRect(0,0,Win.WIDTH,Win.HEIGHT);
		
		g.setColor(Win.headerColor);
		g.setFont(new Font(Win.menuFont.getFontName(),Font.BOLD,Win.menuFont.getSize()+20));
		g.drawString("Select Workout to Edit", 200, 100);
		
		Enumeration<Workout> wnum = workouts.elements();
		int n = 0;
		while(wnum.hasMoreElements()){
			wnum.nextElement().menuPaint(g,n);
			n = n + 1;
		}
	}

	@Override
	public void calculate(long delta) {
		if(editing){we.calculate(delta);	return;}
		
	}

	@Override
	public void setPhaseHandler(PhaseHandler a) {
		ph = a;
		we.setPhaseHandler(a);
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onEnd() {
		((WorkoutSelect) ph.phases[2]).loadWorkoutsFromFile();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(editing){we.keyPressed(e);	return;}
		
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
		case KeyEvent.VK_ENTER:		editWorkout(selected);	
		}
	}
	
	public void stopEditing(){
		we.onEnd();
		editing = false;
	}
	
	public void editWorkout(int a){
		we.setWorkout(workouts.elementAt(a));
		editing = true;
	}

}
