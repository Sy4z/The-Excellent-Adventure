package clientServer;

public class ClockThread extends Thread{
	private final int delay;//delay between game ticks

	public ClockThread(int delay){
		this.delay = delay;
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(delay);
			}
			catch(InterruptedException e){

			}
		}
	}


}
