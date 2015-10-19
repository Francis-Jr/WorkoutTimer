package phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Vector;

import main.Win;
import workout.Workout;
import workout.WorkoutSection;
import workout.TimerSection;
import workout.AmountSection;

public class WorkoutEditor implements Phase {

	private final long COLOR_DURATION = (long) 4e8;
	
	private PhaseHandler ph;
	private Workout wo = null;
	private Vector<WorkoutSection> sections = new Vector<WorkoutSection>(2);
	private int selected = 0;
	private int mode = 0;	//0=select section ; 1=changeMins ; 2=changeSeconds ; 3 = changeAmount ; 4=changeTitle
	private long colorCounter = 0;
	private boolean selectColorOn = true;
	private Color colorColor = Win.selectColor;
	
	public WorkoutEditor() {
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		
		g.setColor(Win.headerColor);
		g.setFont(new Font(Win.menuFont.getFontName(),Font.BOLD,Win.menuFont.getSize()+20));
		g.drawString("Editing '"+ wo.getTitle() +"'", 200, 100);
		
		Enumeration<WorkoutSection> wsnum = sections.elements();
		int n = 0;
		while(wsnum.hasMoreElements()){
			wsnum.nextElement().editPaint(g,n,mode,colorColor);
			n = n + 1;
		}
	}

	@Override
	public void calculate(long delta) {
		colorCounter = colorCounter + delta;
		if(colorCounter >= COLOR_DURATION){
			colorCounter = 0;
			selectColorOn = !selectColorOn;
			colorColor = selectColorOn ? Win.selectColor : Win.textColor;
		}
	}

	@Override
	public void setPhaseHandler(PhaseHandler a) {
		ph = a;
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onEnd() {
		//TODO save Workout...
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_ESCAPE:		if(mode==0)((Edit) ph.phases[3]).stopEditing();	else mode = 0;
		break;
		case KeyEvent.VK_UP:			if(mode==0)selectSection(selected-1);
										else if(mode == 1) sections.elementAt(selected).addValue(60);
										else if(mode == 2 || mode == 3) sections.elementAt(selected).addValue(1);
		break;
		case KeyEvent.VK_DOWN:			if(mode==0)selectSection(selected+1);
										else if(mode == 1) sections.elementAt(selected).addValue(-60);
										else if(mode == 2 || mode == 3) sections.elementAt(selected).addValue(-1);
		break;
		case KeyEvent.VK_ENTER:			enter();
		break;
		case KeyEvent.VK_BACK_SPACE:	if(mode==4) backspace(sections.elementAt(selected).getTitle());
		break;
		}
		
		if(mode ==4 && Character.isAlphabetic(e.getKeyChar()) ){
			sections.elementAt(selected).setTitle(sections.elementAt(selected).getTitle()+e.getKeyChar());
			System.out.println("[Testing] Adding " + e.getKeyChar());
		}
	}

	private void backspace(String a) {
		sections.elementAt(selected).setTitle(a.substring(0, a.length()-1));
	}

	private void enter() {
		switch(mode){
		case 0:	if(sections.elementAt(selected).getType()==1)	mode = 1;
				else if(sections.elementAt(selected).getType() == 2)	mode = 3;
				//TODO type 3, creat new
			break;
		case 1:	mode = 2;
			break;
		case 2:
		case 3:	mode = 4;
			break;
		case 4:	mode = 0;
			break;
		}
	}

	public void selectSection(int a){
		if(a>=sections.size())	{ a = a-sections.size(); if(a>=sections.size())return;}
		if(a<0)	{ a = a+sections.size(); if(a<0)return;}
		sections.elementAt(selected).setSelected(false);
		selected = a;
		sections.elementAt(selected).setSelected(true);
	}
	
	public void setWorkout(Workout a) {
		wo = a;
		selected = 0;
		sections = new Vector<WorkoutSection>(2);
		for(WorkoutSection s : a.sections){
			s.setSelected(false);
			sections.add(s);
		}
		sections.firstElement().setSelected(true);
	}
}
