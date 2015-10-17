package phases;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public interface Phase {	
	public void paint(Graphics g);
	public void calculate(long delta);
	public void setPhaseHandler(PhaseHandler a);
	public void onStart();
	public void onEnd();
	public void keyPressed(KeyEvent e);
}
