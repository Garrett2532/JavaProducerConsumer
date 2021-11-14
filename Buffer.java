import java.util.ArrayList;
public class Buffer {
int size;						
int[] indexes;						//Initializee varibles that will be used
int[] rnums;
ArrayList<Integer>  buff;
int numFin;
int itemsAdded =0;

public Buffer(int size, int items) {
	this.size = size;
	this.buff = new ArrayList<Integer>();		//set varibles equal to the varibles given to the constructor
	this.indexes =  new int[items];
	this.rnums =  new int[items];
}
	

boolean full() {
if(buff.size()==size)	{				//full method will return true if full and false if not full
	return true;
}else {
	return false;
}
}
boolean empty() {					//empty will return trur if empty and false if not empty 
	if(buff.size()==0) {
		return true;
	}else {
	return false;
	}
	}

int insert(int rnum) {
	
	buff.add(rnum);					//insets s number into the array list which is acting as the buffer
	indexes[itemsAdded] = buff.size()-1;
	rnums[itemsAdded] = rnum;			//add random number to an array to get the index later
	itemsAdded++;
	return buff.size()-1;				//returns the index in the array list that it was added to
	
}
int remove() {
	
	Integer item = buff.get(0);			//remove will remove the next element in the list 
    buff.remove(0);
    numFin++;						// incresed the count of number of finihsed items used to help print the index
    return item;
}
int getIndex(int rnum) {				//retunrs the index that an item was removed from since it changed so much in the array lsit 
	int temp=-1;
//	int[] copy = indexes;
//	int []copy1 = rnums;
	for(int i =0;i<rnums.length;i++) {		//find the 1st random number that matches then set the number in taht posion to 100 which is out of range
		if(rnum == rnums[i]) {
			 temp = indexes[i];		//indexes is an array of indexes that relates to the random num looking back I should hvae donea 2d array
			rnums[i]=100;
			break;	
		}
	}
	return temp;					//returns the index
}

}


