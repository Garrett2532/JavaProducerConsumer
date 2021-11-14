
import java.time.Instant;
import java.time.Clock;
import java.time.Duration;

class Coordinator {
	public static void main(String[] args) {
		if (args.length <= 4) {
			System.out.println("Not enough arguments");					//Checks to make sure that the propper number of args are given
			System.exit(1);
		}
		int buffsize=Integer.parseInt(args[0]);  
		int items=Integer.parseInt(args[1]);  
		int numConsumers =Integer.parseInt(args[2]);  
		int numProducers =Integer.parseInt(args[3]);  						//puts args into varibles
		int seed =Integer.parseInt(args[4]);
		
		Buffer b;
		b =  new Buffer(buffsize,items);
		int [] ppp =  getnum(items,numProducers);
		Producer[] pro = new Producer[numProducers];						//makes and starts a number of produces equalt to the args
		for(int i =0; i < numProducers; i++) {
			pro[i]= new Producer(b,ppp[i],i+1,seed);
			pro[i].start();
			
		}
		int [] cpp =  getnum(items,numConsumers);						//makes and starts a number of consumers equal to the args
		Consumer[] con = new Consumer[numConsumers];
		for(int i =0; i < numConsumers; i++) {
			con[i]= new Consumer(b,cpp[i],i+1,seed);
			con[i].start();
			
		}
		


		for(int i =0; i <con.length;i++) {
			try {
				con[i].join();
			} catch (InterruptedException e) {						// for each consumer becase the consumer should have the last line join
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

													//print number of producers and  with formating 
		System.out.println("Producer(s): Finished producing "+items+" items with checksum being "+Producer.getChkSum());
	//	System.out.println("Consumer(s): Finished producing "+items+" items with checksum being "+Consumer.getChecksum());
	
		System.out.format("\033[0;4mConsumer(s): Finished consuming %d items with checksum being %d\033[0;0m\n", items,Consumer.getChecksum());

		System.exit(1);		
		}
		
	//Call this function from your producer or your consumer to get the time stamp to be displayed
	public static String getTime() {
		Clock offsetClock = Clock.offset(Clock.systemUTC(), Duration.ofHours(-9));
		Instant time = Instant.now(offsetClock);
		String timeString = time.toString();
		timeString = timeString.replace('T', ' ');
		timeString = timeString.replace('Z', ' ');
		return(timeString);
	}
	public static int[] getnum(int total,int div) {							//gets the numer that each prducer and consumer should create 
		int[] a = new int[div];
		int i=0;
		while (true) {
			a[i] = a[i]+1;
			total = total -1;
			if(total==0) {									//adds 1 to each producer and consumer until there are no more total items this will make it so the 
													//producers and consumers have as close to as even jobs as possible
				return a;
			}
			if (i == div-1) {								
				i = 0;
						}
			else {
				i++;
			}
			
		}
		
	}
}
