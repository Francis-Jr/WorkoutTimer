package workout;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

public class WorkoutManager implements Serializable{
	/**
	 * Oct 9 15
	 */
	private static final long serialVersionUID = 6701580262342495964L;
	private Vector<String> names;	
	
	public WorkoutManager(){
	}
	
	public Vector<Workout> getWorkouts(){
			WorkoutSerializer ws = new WorkoutSerializer();
			Vector<Workout> tmp = new Vector<Workout>();
			Enumeration<String> snum = names.elements();
			while(snum.hasMoreElements()){
				tmp.add(ws.readWorkout(snum.nextElement()));
			}
			System.out.println("[DEBUG] WorkoutManager gets " + tmp.size() + "Workouts" );
			System.out.println("[DEBUG] WorkoutManager 'getWorkouts()' gets the first Workout with  " + tmp.firstElement().sections.size() + " Sections");
			return tmp;
			
		}
		
	
		
}
