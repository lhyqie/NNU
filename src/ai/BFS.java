package ai;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static Queue<int[][]> pq = new LinkedList<int[][]>();
    static HashSet<String> states = new HashSet<String>();
    
	static boolean is_posit(int[][] a)
    {
        int should_be = 1;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (i == 1 && j == 1) break;
                if (a[i][j] != should_be) return false;
                should_be++;
            }
        }

        for (int i = 1; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (i == 1 && j < 2) continue ;
                if (a[i][j] != should_be) return false;
                should_be++;
            }
        }
        return true;
    }
	
    static int[] find_zero(int[][]a) throws Exception
    {
    	int cindex = -1, rindex = -1;   
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 0) {
                    rindex = i;
                    cindex = j;
                }
                //System.out.print(i);
                //System.out.printLine(" ");
               // System.out.printLine(j);
               // System.out.printLine(a[i][j]);
            }
        }
        if(cindex == -1 && rindex == -1){
        	throw new Exception("invalid input!");
        }
        return new int[]{rindex,cindex};
    }
    
    static String toString(int[][] a){  // StringBuffer is better 
    	String ret = "";
    	for(int i=0; i<3; i++)
    		for(int j=0; j<3; j++)
    			ret += a[i][j];
    	return ret;
    }
    
    /*static char[] tochar(int[][] a){
    	char[] ret = new char[9];
    	int t = 0;
    	for(int i=0; i<3; i++)
    		for(int j=0; j<3; j++,t++)
    			ret[t] = (char)a[i][j];
    	return ret;
    }*/
    
    static void move(int[][] a) {
        int[] r = new int[2]; 
        try {
			r = find_zero(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int[][]b = new int[3][]; 
        for (int i = 0; i < 3; i++ )
            b[i] = new int[3];
        
    	//left
        if (r[1] > 0 ){
        	b = swap(a, r[0], r[1], r[0], r[1]-1);
        	if(!states.contains(toString(b))){
        		System.out.println("左移：←");
        		pq.add(b);
        	}
        	states.add(toString(b));
        }
        
    	//up
        if (r[0] > 0 ){
            b = swap(a, r[0], r[1], r[0]-1,r[1]);
            if(!states.contains(toString(b))){
            	System.out.println("上移：↑");
        		pq.add(b);
            }
        	states.add(toString(b));
        }
    
		//right
        if (r[1] < 2 ){
            b = swap(a, r[0], r[1], r[0],r[1]+1);
            if(!states.contains(toString(b))){
            	System.out.println("右移：→");
        		pq.add(b);
            }
        	states.add(toString(b));
        }
        
        //down
        if (r[0] < 2 ){
        	b = swap(a, r[0], r[1], r[0]+1,r[1]);
        	if(!states.contains(toString(b))){
            	System.out.println("下移：↓");
        		pq.add(b);
        	}
        	states.add(toString(b));
        }
    }

    
    static int[][] swap( int[][] a, int i1, int j1, int i2, int j2)
    {
    	int[][]tmpM = new int[3][];
        for (int i = 0; i < 3; i++ )
            tmpM[i] = new int[3];
        
        copy(a,tmpM,3,3);
        int temp = tmpM[i1][j1];
        tmpM[i1][j1] = tmpM[i2][j2];
        tmpM[i2][j2] = temp;
        return tmpM;
    }
    static void print(int[][] m)
    {
        for (int ii = 0; ii < 3; ii++)
        {
            int count = 0;
            for (int jj = 0; jj < 3; jj++)
            {
                System.out.print(m[ii][jj]);
                System.out.print(" ");
                count++;
                if (count % 3 == 0)
                    System.out.println();
            }
        }
        System.out.println();
    }

    static void copy(int[][]source,int[][]destination,int n,int m)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) { 
                destination[i][j] = source[i][j];
            }
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		int[][] a = 
//				new int[3][];
//        for (int i = 0; i < 3; i++ )
//            a[i] = new int[3];
//        a[0][0] = 1;
//        a[0][1] = 5;
//        a[0][2] = 2;
//        a[1][0] = 4;
//        a[1][1] = 0;
//        a[1][2] = 3;
//        a[2][0] = 6;
//        a[2][1] = 7;
//        a[2][2] = 8;
        
        int [][] a = {{1,5,2},
        			  {4,0,3},
        			  {6,7,8}};
        
        /*a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 0;
        a[1][2] = 5;
        a[2][0] = 6;
        a[2][1] = 7;
        a[2][2] = 8;*/
        //test is_posit()
        //System.out.println(is_posit(a));
        
        //test find_zero()
        /*int[] index = new int[2];
        index = find_zero(a);
        System.out.println(index[0]+","+index[1]);*/
        
        //test print()
        //print(a);
        
        //test swap()
        /*int[][]b = new int[3][];
        for (int i = 0; i < 3; i++ )
            b[i] = new int[3];
        b = swap(a,0,0,2,2);
        print(b);
        print(a);*/
        
        //test toString()
        /*String b = "";
        b = toString(a);
        System.out.println(b);*/
        
        //System.out.println(a.length);		3
        
        //hashset的add参数类型
        /*String s = "12345";
        states.add(s);*/
        
//        int[][]b = new int[3][];
//        for (int i = 0; i < 3; i++ )
//            b[i] = new int[3];
//        pq.add(a);
        
        int [][] b = a;
        while(!is_posit(b)){
        	if(!pq.isEmpty()){
        		b = pq.poll();
        		print(b);
        	}
        	move(b);
        }
        print(b);
	}

}
