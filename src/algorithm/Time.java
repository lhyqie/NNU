package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
public class Time {
	int st;
	int ft;
	int dt;
	int interval;
	int lateness;
	Time(int st,int ft,int dt,int interval,int lateness ){
		this.st = st;
		this.ft = ft;
		this.dt = dt;
		this.interval = interval;
		this.lateness = lateness;
	}
	int lateness(Time t){
		if(t.ft <= t.dt)	return 0;
		else	return t.ft - t.dt;
	}
	
	public static void main(String[] args) {
		Time t1= new Time(-1,-1,6,3,-1);
		Time t2= new Time(-1,-1,15,3,-1);
		Time t3= new Time(-1,-1,8,2,-1);
		Time t4= new Time(-1,-1,9,1,-1);
		Time t5= new Time(-1,-1,9,4,-1);
		Time t6= new Time(-1,-1,14,3,-1);
		
		Comparator<Time> cmp;
		cmp = new Comparator<Time>() { 
			public int compare(Time t1, Time t2) {
				return t1.dt - t2.dt;
			}
		};
		Queue<Time> pq = new PriorityQueue<Time>(5, cmp);
		int t = 0;
		pq.offer(t1);
		pq.offer(t2);
		pq.offer(t3);
		pq.offer(t4);
		pq.offer(t5);
		pq.offer(t6);
		System.out.println("start"+"  "+"finish"+"  "+"due"+"  "+"interval"+"  "+"lateness");
		while(!pq.isEmpty()){
			Time s = pq.poll();
			s.st = t;
			t += s.interval;
			s.ft = t;
			s.lateness = s.lateness(s);
			System.out.print(s.st+"\t"+s.ft+"\t"+s.dt+"\t"+s.interval+"\t"+s.lateness);
			System.out.println();
		}
	}
}

