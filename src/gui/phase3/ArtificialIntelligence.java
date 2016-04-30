package gui.phase3;

import java.security.SecureRandom;

import gui.phase2.SubController;
import Archeology.v2.Map.Coordinate;

/**
 * @author ronin
 *
 */
public class ArtificialIntelligence implements Runnable{
	
	private Thread thread;
	private String threadName;
	private Coordinate startGrid;
	private SubController subController = new SubController();
	private StringBuffer out;
	
	/**
	 * Create a Thread with a name and a starting Coordinate.
	 * @param _name the name of the thread
	 * @param _start start from top of map and work straight down (column++)
	 */
	public ArtificialIntelligence(String _name, Coordinate _start)
	{
		setThreadName(_name);
		setStartGrid(_start);
	}
	
	

	@Override
	/**
	 * Start from top of thread and work straight down in a column.
	 * Wait 10 seconds. Append finds to popup report sent to subcontroller
	 */
	public void run() 
	{
		// TODO Auto-generated method stub
		AI(this.threadName, this.startGrid);
		
	}

	public void AI(String _name, Coordinate _start)
	{
		Integer numberOfThingsToDig = 6;	//in all should wait per thread 60 seconds
		Integer timeToWait = 10000;
		Integer _previous = 0, _new = 0;
		out = new StringBuffer();
		
		for(int i = 0; i <= numberOfThingsToDig; i++)
		{
			try 
		    {	
				// delay slightly to offset saving at same time
				Thread.sleep(randomNumbers(500, 1000));		//Interval between 1/2 & 1 second
				_previous = this.startGrid.getRow();
				_new = _previous + i;
				//NULL POINTER
		    	out.append( subController.digMap( this.startGrid ) );
		    	this.startGrid.setRow( _new );
		    	//Save Map
		    	subController.save("PopUp");
		    	//Wait
		    	Thread.sleep(timeToWait);
		    }
		    catch (InterruptedException e) 
		    {
		       System.out.println("Thread " +  this.threadName + " interrupted.");
		    }
		}
		//PopUp Dialog
		System.out.println("Thread " +  this.threadName + " exiting.");
		setString( out );
		subController.popupDialog(out.toString() , this.threadName, "information");
	}
	
	public void start()
	{
		System.out.println("Starting " +  this.threadName );
		if (this.thread == null)
		{
			this.thread = new Thread (this, this.threadName);
			this.thread.start();
		}
	}
	
	/**
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}


	/**
	 * @param thread the thread to set
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}


	/**
	 * @return the threadName
	 */
	public String getThreadName() {
		return threadName;
	}


	/**
	 * @param threadName the threadName to set
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}


	/**
	 * @return the startGrid
	 */
	public Coordinate getStartGrid() {
		return startGrid;
	}


	/**
	 * @param startGrid the startGrid to set
	 */
	public void setStartGrid(Coordinate startGrid) {
		this.startGrid = startGrid;
	}
	
	/**
	 * @return the out
	 */
	public StringBuffer getString() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setString(StringBuffer out) {
		this.out = out;
	}

	/**
	 * Generate a random Number between two minimum & maximum. Used to delay saving, 
	 * so no thread saves at exactly the same time.
	 * @param min minimum number
	 * @param max maximum number
	 * @return an authentic random number
	 */
	public static int randomNumbers(int min, int max)
	{ // formula to generate #'s within a given range
		// default java behavior is not dynamic
		SecureRandom randomNumbers = new SecureRandom();
		int generatedNumber = randomNumbers.nextInt( max - min + 1) + min;
		return generatedNumber;
	}

}
