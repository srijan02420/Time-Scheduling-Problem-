import java.util.Collections;
import java.util.Comparator;
import java.util.List; 
import jp.co.wap.exam.lib.Interval; 

public class Problem2 { 
	
	private int lastcompatibletime;
	private int intervalsize;
	
 public int getMaxWorkingTime(List<Interval> intervals) { 
 // TODO: Implement this method.
	 intervalsize = intervals.size();
	 Collections.sort(intervals,new Sort());	//Sorting intervals according to start time
	 
	 int[] store = new int[intervalsize];	//arrays to store maximum time till ith job

	 for(int i=0;i<intervalsize;i++)
	 {
		 lastcompatibletime = 0;
		 store[i] = 0;
		 //searching for last compatible job
		 for(int j=1;j<=i;j++)
		 {
			 if(intervals.get(i).getBeginMinuteUnit() > intervals.get(i-j).getEndMinuteUnit())
				 {
				 	lastcompatibletime = store[i-j];
				 	break;
				 } 
		 }
		 if(i!=0)
		 {
			 if((intervals.get(i).getIntervalMinute()+lastcompatibletime) > store[i-1])
				 store[i] = lastcompatibletime + intervals.get(i).getIntervalMinute();
			 else
				 store[i] = store[i-1];
		 }
		 else
			 store[i] = intervals.get(i).getIntervalMinute();
	 }
	 
	 return store[intervalsize-1];
 } 
 
}
//Class for comparing the intervals
class Sort implements Comparator<Interval>{
	@Override
	public int compare(Interval o1, Interval o2) {
		if(o1.getBeginMinuteUnit() < o2.getBeginMinuteUnit()){
            return -1;
        } else {
            return 1;
        }
	}
}

