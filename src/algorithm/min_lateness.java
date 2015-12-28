package algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

public class min_lateness {
	
	static void print(int[]a){
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]+"\t ");
		System.out.println();
	}
	static String toString(int[] due_time){
		String ret = "";
		for(int i=0; i<due_time.length; i++)
			ret += due_time[i];
		return ret;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int t = 0;
		int[] time_interval = {3,2,1,4,3,2};
		int[] due_time = {6,3,8,9,14,4};//4改成15  15的下标是6？
		int[] start = new int[due_time.length];
		int[] end = new int[due_time.length]; 
		String s = toString(due_time);
		//print(time_interval);
		//print(due_time);
		Queue<Integer> pq = new PriorityQueue<Integer>(); 
		for(int i=0; i<due_time.length; i++)
			pq.offer(due_time[i]);
		//System.out.println(s.indexOf(100));-1
		while(!pq.isEmpty()){
			//System.out.print(String.valueOf(pq.poll()));
			int index = s.indexOf(String.valueOf(pq.poll()));
			
			//System.out.println(index);
			start[index] = t;
			t += time_interval[index];
			end[index] = t;
		}
		System.out.print("start:\t");
		print(start);
		System.out.print("end:\t");
		print(end);
	}

}
