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
}
