package main;

public class Calculation implements Runnable {
	
	private Win win;
	private Thread th;
	
	private long last = System.nanoTime(), delta = 1;
	
	
	public Calculation(Win a){
		win = a;
		th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		while(win.isVisible()){
			delta = System.nanoTime()-last;
			last = System.nanoTime();
			
			win.ph.calculate(delta);
			win.refresh();
			
			try {
				Thread.sleep(5);
			} 
			catch (InterruptedException e) {
				System.err.println("Calculation:   Exception at Thread sleep");
				e.printStackTrace();
			}
		}
	}
}
