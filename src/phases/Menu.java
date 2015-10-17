package phases;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Win;
import menu.MenuItem;


public class Menu implements Phase {

	private PhaseHandler ph;
	private MenuItem[] items = {new MenuItem("Start Workout", 300,200,""),	//0
								new MenuItem("Edit Workout", 300,300,""),	//1
								new MenuItem("Settings", 300,400,""),		//2
								new MenuItem("Quit", 300,500,"")};			//3
	private int selected = 0;
	
	
	public Menu(){
		items[selected].select();
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		
		g.setColor(Win.headerColor);
		g.setFont(new Font(Win.menuFont.getFontName(),Font.BOLD,Win.menuFont.getSize()+20));
		g.drawString("THE GREAT WORKOUT TIMER", 200, 100);
		
		for(int n = 0; n<items.length ; n++){
			items[n].paint(g);
		}
		
	}

	@Override
	public void calculate(long delta) {
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onEnd() {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_ESCAPE:	ph.setPhase(4);				break;
			case KeyEvent.VK_UP:		setSelected(selected-1);	break;
			case KeyEvent.VK_DOWN:		setSelected(selected+1);	break;
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_ENTER:		enter();					break;
		}
	}
	
	private void enter(){
		switch(selected){
		case 0:		ph.setPhase(2);			break;
		case 1:		ph.setPhase(3);			break;
		case 2:		ph.setPhase(5);			break;
		case 3:		ph.setPhase(4);			break;
		}
	}

	private void setSelected(int i) {
		if(i>=items.length){	i = i-items.length; if(i>=items.length) return;}
		if(i<0){	i = i+items.length; if(i<0) return;}
		
		items[selected].unselect();
		selected = i;
		items[selected].select();
	}

	@Override
	public void setPhaseHandler(PhaseHandler a) {
		ph = a;
	}

}
