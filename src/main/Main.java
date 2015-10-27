package main;
import java.util.Enumeration;
import java.util.Vector;

import workout.AmountSection;
import workout.TimerSection;
import workout.Workout;
import workout.WorkoutSerializer;
import workout.WorkoutSection;

public class Main {

	public static Win win;
	public static Calculation calc;
	
	public static void main(String[] args) {
		//writeTestWorkouts();
		win = new Win();
		calc = new Calculation(win);
	}

	public static void writeTestWorkouts(){
		WorkoutSerializer ws = new WorkoutSerializer();
		
		Workout wo1 = new Workout("TestWorkout 1");
		Workout wo2 = new Workout("TestWorkout 2");
		
		Vector<WorkoutSection> sections = new Vector<WorkoutSection>(2);
		sections.add(new TimerSection("TimerSection 1",(long) 2e9 , wo1));
		sections.add(new AmountSection("Amount Section" , 20 , wo1));
		sections.add(new TimerSection("TimerSection 2",(long) 3e9 , wo1));
		wo1.setSections(sections);
		Enumeration<WorkoutSection> wsnum = wo1.sections.elements();
		while(wsnum.hasMoreElements()){
			wsnum.nextElement().setWorkout(wo1);
		}
		
		sections = new Vector<WorkoutSection>(2);
		sections.add(new TimerSection("TimerSection 1",(long) 2e9 , wo2));
		sections.add(new AmountSection("Amount Section" , 20 , wo2));
		sections.add(new TimerSection("TimerSection 2",(long) 3e9 , wo2));
		wo1.setSections(sections);
		Enumeration<WorkoutSection> wsnum2 = wo2.sections.elements();
		while(wsnum2.hasMoreElements()){
			wsnum2.nextElement().setWorkout(wo2);
		}
		
		
		ws.writeWorkout(wo1);
		ws.writeWorkout(wo2);
	}
}
