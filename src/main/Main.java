package main;

public class Main {

	public static Win win;
	public static Calculation calc;
	
	public static void main(String[] args) {
		win = new Win();
		calc = new Calculation(win);
	}

}
