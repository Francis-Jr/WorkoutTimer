package phases;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PhaseHandler implements KeyListener {

	public Phase[] phases = {new Intro() , new Menu() , new WorkoutSelect() , new Edit() , new QuitMenu(), new Settings(), }; 
	//								0			1				2					3			4					5			
	private int currentPhase;
	
	public PhaseHandler(){
		currentPhase = 0;
		for (int i = 0 ; i < phases.length ; i++){
			phases[i].setPhaseHandler(this);
		}
	}
	
	public void paint(Graphics g){
		phases[currentPhase].paint(g);
	}
	
	public void calculate(long delta){
		phases[currentPhase].calculate(delta);
	}
	
	public void setPhase(int a){
		if(a < 0 || a>=phases.length){
			System.err.println("PhaseHandler:   tried to set invalid Phase");
			return;
		}
		phases[currentPhase].onEnd();
		currentPhase = a;
		phases[currentPhase].onStart();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		phases[currentPhase].keyPressed(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		phases[currentPhase].keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
