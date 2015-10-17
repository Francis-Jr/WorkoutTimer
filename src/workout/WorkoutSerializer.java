package workout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WorkoutSerializer {

	
	private String path = "C:/Users/Jakob/workspace/Workout Timer/Save/";
	
	public void writeWorkout(Workout a){
		try{   
			FileOutputStream fout = new FileOutputStream(path + a.getTitle() + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(a);
			oos.close();
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
	}
	
	public Workout readWorkout(String name){
		Workout workout;
		System.out.print("[Serializer] Reading Workout '" + name + "':   ");
		
		try{
		   FileInputStream fin = new FileInputStream(path + name + ".ser");
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   workout = (Workout) ois.readObject();
		   ois.close();
			
		   System.out.println("success");
		   return workout;
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
			   System.out.println("failed");
			   return null;
		   } 
	}
	
	public String[] getWorkoutNames(){
		
		
		//TODO
		return null;
	}
	
	public void writeWorkoutManager(WorkoutManager a){
		try{   
			FileOutputStream fout = new FileOutputStream(path + "manager.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(a);
			oos.close();
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
	}
	
	public WorkoutManager readWorkoutManager(){
		WorkoutManager tmp;
		System.out.print("[Serializer] Reading Workoutmanager:   ");
		
		try{
		   FileInputStream fin = new FileInputStream(path +  "manager.ser");
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   tmp = (WorkoutManager) ois.readObject();
		   ois.close();
			
		   System.out.println("success");
		   return tmp;
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
			   System.out.println("failed");
			   return null;
		   } 
	}
}
