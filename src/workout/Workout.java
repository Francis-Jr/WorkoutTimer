package workout;

import java.awt.Graphics;
import java.io.Serializable;

import main.Win;
import phases.WorkoutPlayer;

public class Workout implements Serializable{
	
	/**
	 * 	Oct 9 15
	 */
	private static final long serialVersionUID = 6935213543794084854L;
	public WorkoutSection[] sections;
	private int currentSection = 0;
	
	private String title = "title";
	private boolean selected = false;
	
	private WorkoutPlayer wp = null;
	
	public Workout(String a, WorkoutSection[] b){
		title = a;
		sections = b;
	}
	
	public Workout(String a){
		title = a;
		initSections(); 
	}
	
	public void setWorkoutPlayer(WorkoutPlayer a){
		wp = a;
	}
	
	/**
	 * for testing / testwise creation only
	 */
	private void initSections(){ 
		sections = new WorkoutSection[1];
		sections[0] = new DoneSection(this);
	}

	public void paint(Graphics g) {
		sections[currentSection].paint(g);
	}

	public void calculate(long delta) {
		sections[currentSection].calculate(delta);
	}	
	
	protected void nextSection(){
		currentSection = currentSection + 1;
		if (currentSection >= sections.length)	currentSection = -1;
	}

	public void space() {
		sections[currentSection].togglePaused();
	}

	public void quitWorkout() {
		initSections();
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
		WorkoutSection[] tmp = new WorkoutSection[sections.length + 1];
		for(int n= 0 ; n< sections.length ; n++){
			tmp[n] = sections[n];
		}
		tmp[sections.length] = new TimerSection("new Section", 10000000000L, this);
	}
}
