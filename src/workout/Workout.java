package workout;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Vector;

import main.Win;
import phases.WorkoutPlayer;

public class Workout implements Serializable{
	
	/**
	 * 	Oct 9 15
	 */
	private static final long serialVersionUID = 6935213543794084854L;
	public Vector<WorkoutSection> sections = new Vector<WorkoutSection>(2);
	private int currentSection = 0;
	
	private String title = "title";
	private boolean selected = false;
	
	private WorkoutPlayer wp = null;
	
	public Workout(String a, Vector<WorkoutSection> b){
		title = a;
		sections = b;
	}
	
	public Workout(String a){ //for testwise creation only
		title = a;
		sections.add(new TimerSection("TimerSection 1",(long) 2e9 , this));
		sections.add(new AmountSection("Amount Section" , 20 , this));
		sections.add(new TimerSection("TimerSection 2",(long) 3e9 , this));
	}
	
	public void setWorkoutPlayer(WorkoutPlayer a){
		wp = a;
	}
	
	/**
	 * for testing / testwise creation only
	 */

	public void paint(Graphics g) {
		sections.elementAt(currentSection).paint(g);
	}

	public void calculate(long delta) {
		sections.elementAt(currentSection).calculate(delta);
	}	
	
	protected void nextSection(){
		currentSection = currentSection + 1;
		if (currentSection >= 
				sections.size()
				)	currentSection = -1;
	}

	public void space() {
		sections.elementAt(currentSection).togglePaused();
	}

	public void quitWorkout() {
		currentSection = 0;
		wp.quitWorkout();
	}

	public void menuPaint(Graphics g, int a) {
		g.setColor(selected ? Win.selectColor : Win.textColor);
		g.setFont(Win.menuFont);
		g.drawString(title, 400, 200+100*a);
	}
	
	public void setSelected(boolean a){
		selected = a;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void createNewSection(){
		sections.addElement(new TimerSection("new Section",(long)1e9,this));
	}
	
	public void setSections(Vector<WorkoutSection> a){
		sections = a;
	}
}
