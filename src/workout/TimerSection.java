package workout;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.Win;

public class TimerSection implements WorkoutSection, Serializable {

	/**
	 * Oct 9 15
	 */
	private static final long serialVersionUID = 8003150158990388367L;
	private String title = "title";
	private long duration =  17000000000L; //in nanoseconds (factor 10^9)
	private long remains = duration, minutes = 61, seconds = 61;
	private Workout wo;
	private boolean paused = true;
	private boolean selected;
	public int type = 1;
	public int wouldBeAmount = 20;
	
	public TimerSection(String a, long b, Workout c){ //b in nanoseconds
		title = a;
		duration = b+990000000L;
		remains = duration;
		minutes = (long) Math.floor(remains/(60e9));
		seconds = (long) Math.floor(remains/(1e9))%60;
		wo = c;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Win.bgColor);
		g.fillRect(0, 0, Win.WIDTH, Win.HEIGHT);
		g.setFont(Win.timerFont);
		g.setColor(paused ? Win.pausedColor : Win.timerColor);
		g.drawString((minutes<10 ? "0" : "") + minutes+":"+(seconds<10 ? "0" : "")+seconds, 450, 300);
		g.setFont(Win.sectionFont);
		g.setColor(Win.sectionColor);
		g.drawString(title, 450, 550);
	}

	@Override
	public void calculate(long delta) {
		if(!paused){
			remains = remains - delta;
			minutes = (long) Math.floor(remains/(60e9));
			seconds = (long) Math.floor(remains/(1e9))%60;
			if(remains <=0)	{
				playSound();
				wo.nextSection();
			}
		}
		
	}
	
	public void pause(){
		paused = true;
	}
	
	public void unpause(){
		paused = false;
	}
	
	public void togglePaused(){
		if(paused)	unpause();
		else		pause();
	}

	@Override
	public void editPaint(Graphics g, int a, int b, Color c) {
		g.setFont(Win.menuFont);
		
		if(!selected)	g.setColor(Win.textColor);
		else if(b==1)	g.setColor(c);
		else if(b==0)	g.setColor(Win.selectColor);
		else			g.setColor(Win.textColor);
		g.drawString((minutes<10 ? "0" : "") + minutes+":",250,200+100*a);
		
		g.setColor(selected&&b==0?Win.selectColor:Win.textColor);
		g.drawString(":",300,200+100*a);
		
		if(!selected)	g.setColor(Win.textColor);
		else if(b==2)	g.setColor(c);
		else if(b==0)	g.setColor(Win.selectColor);
		else			g.setColor(Win.textColor);
		g.drawString((seconds<10 ? "0" : "") +seconds, 315, 200+100*a);
		
		if(!selected)	g.setColor(Win.textColor);
		else if(b==4)	g.setColor(c);
		else if(b==0)	g.setColor(Win.selectColor);
		else			g.setColor(Win.textColor);
		g.drawString(title, 450, 200+100*a);
	}
	
	public void setSelected(boolean a){
		selected = a;
	}

	@Override
	public int getType() {
		return 1;
	}

	@Override
	public void addValue(int a) {
		duration = duration + a*(long)1e9;
		if(duration < 0) duration = 0;
		remains = duration;
		minutes = (long) Math.floor(remains/(60e9));
		seconds = (long) Math.floor(remains/(1e9))%60;
		
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String a) {
		title = a;
	}
	
	public void playSound(){
		try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
            	new File(WorkoutSerializer.path + "beep-02.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        
        catch(UnsupportedAudioFileException uae) {
            System.out.println(uae);
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
        catch(LineUnavailableException lua) {
            System.out.println(lua);
        }
	}

	@Override
	public int getAmount() {
		return wouldBeAmount;
	}

	@Override
	public long getDuration() {
		return duration;
	}
}