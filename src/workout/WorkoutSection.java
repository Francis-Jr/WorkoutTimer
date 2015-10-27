package workout;

import java.awt.Color;
import java.awt.Graphics;

public interface WorkoutSection{
	public void paint(Graphics g);
	public void calculate(long delta);
	public void togglePaused();
	public void editPaint(Graphics g, int a, int mode , Color colorColor);
	public void setSelected(boolean a);
	public int getType(); //0=undefined/error ; 1=timer ; 2=amount ; 3=done;
	public void addValue(int a);	//Seconds or Amount
	public String getTitle();
	public void setTitle(String a);
	public int getAmount();
	public long getDuration();
	public void setAmount(int a);
	public void setDuration(long a);
	public void setWorkout(Workout a);
}
