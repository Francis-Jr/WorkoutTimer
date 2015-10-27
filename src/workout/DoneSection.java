package workout;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import main.Win;

public class DoneSection implements WorkoutSection, Serializable {

	/**
	 * Oct 9 15
	 */
	private static final long serialVersionUID = -5441793379629492520L;
	private Workout wo;
	private boolean selected;
	public int type = 3;
	
	public DoneSection(Workout a){
		wo = a;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		
		g.setFont(Win.doneFont);
		g.setColor(Win.doneColor);
		g.drawString("DONE!!", 450, 300);
		
	}

	@Override
	public void calculate(long delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void togglePaused() {
		wo.quitWorkout();
	}

	@Override
	public void editPaint(Graphics g, int a, int b , Color c) {
		g.setColor(selected ? Win.selectColor : Win.textColor);
		g.setFont(Win.menuFont);
		g.drawString("create new section", 450, 200+100*a);
	}
	
	public void setSelected(boolean a){
		selected = a;
	}

	@Override
	public int getType() {
		return 3;
	}

	@Override
	public void addValue(int a) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getTitle() {
		return "Done";
	}

	@Override
	public void setTitle(String a) {
		
	}

	@Override
	public int getAmount() {
		return -1;
	}

	@Override
	public long getDuration() {
		return -1;
	}

	@Override
	public void setAmount(int a){}

	@Override
	public void setDuration(long a){}

	@Override
	public void setWorkout(Workout a) {
		wo = a;
	}

}
