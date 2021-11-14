
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Producer extends java.lang.Thread {
	//Necessary variables and object declaration
	Random randomWithSeed;						//initlize varibles 
	int id;
	int numCount;
	Buffer b;
	int count;
	static int checkSum=0;

	public Producer(Buffer buff, int count, int id, int seed) {	//calls the buffer constructor witht the varibles
		//Assign values to the variables
		randomWithSeed = new Random(seed);
		b = buff;
		this.id = id;
		this.count = count;					//sets varibles tot the propper value
		int checkSum =0;
	}
	
	public static int getChkSum(){
		return checkSum;					//returns the check for the final line of the assignment
		
	}
	
	@Override
	public void run() {
		int checkSum =0;					//runs while true
		while (true) {
			int rand = randomWithSeed.nextInt(100);
			if(count==0) {					//breaks if the thread has done its count items/num producers
				break;
			}
			synchronized(b){
				while(b.full()) {			//while b is full wait
					try {
						b.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int index = b.insert(rand);		//insert the random number 
					this.checkSum = this.checkSum + rand;
					count = count -1;		//decrese the count
					System.out.println("Producer "+id+" insterted "+rand+ " at index " + index +" at time " + Coordinator.getTime() );
					b.notifyAll();			//notify all other thread that the list contains at least 1 itme
					}
				}
			}
		
		
		/* Your code goes in here
		*
		*
		*
		--->To generate a value between 0 (inclusive) and 99 (inclusive) using the seeded random  number generator use the following code
		--->          int variable = randomWithSeed.nextInt(100);
		*
		*
		*
		*/
	}


