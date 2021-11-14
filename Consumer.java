import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Consumer extends java.lang.Thread {
	//Necessary variables and object declaration
	Random randomWithSeed;
	int id;
	int numCount;										//initlize varibles
	Buffer b;
	int count;
	static int checkSum =0;

	public Consumer(Buffer buff, int count, int id, int seed) {
		//Assign values to the variables
		randomWithSeed = new Random(seed);
		b = buff;									//set varibles to the onces given tp constuctor 
		this.id = id;
		this.count = count;
	}
	
	public static int getChecksum() {							//returns the checksum number for the consumers
		return checkSum;
	}
	
	@Override
	
	public void run() {
		while(true) {									//infinite loop that breaks when each thread has done its count of jobs
			if(count ==0) {
				break;
			}
			synchronized(b){
				while(b.empty()) {						//while b is empty wait
					try {
						b.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int chk = b.remove();						//get the item that was removed
					this.checkSum = this.checkSum + chk;
					b.notifyAll();						//notify that the buffer has at least 1 open space
					count = count -1;
				//	System.out.println("Consumer "+id+" consumed "+chk+ " at index " + b.getIndex(chk) +" at time " + Coordinator.getTime() );
					System.out.format("\033[0;4mConsumer %d consumed %d  at index %d  at time %s\033[0;0m",id,chk,b.getIndex(chk), Coordinator.getTime());
					System.out.println("");					//print out wtih formating and print a new line
					
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


