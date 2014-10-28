import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import sun.security.util.Length;

import com.sun.jmx.snmp.Timestamp;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;



public class Algorithms {
  
	public static void quicksort(long[] array){
		quicksort(array, 0, array.length-1);
	}
	
	private static void quicksort(long[] array, int beginIndex, int endIndex){
		
		if(beginIndex < endIndex){
			int pivotIndex = partition(array, beginIndex, endIndex);
			quicksort(array, beginIndex, pivotIndex-1);
			quicksort(array, pivotIndex+1, endIndex);
		}
	}

	private static int partition(long[] array, int beginIndex, int endIndex) {
		//pivot will be chosen randomly
		int pivotIndex = Misc.randomNrFromRange(beginIndex, endIndex);
		long pivotValue = array[pivotIndex];
		Misc.swap(array, pivotIndex, endIndex); //pivot to the end of the partition
		int storeIndex = beginIndex;
		
		for(int i = beginIndex; i < endIndex; i++){
			if(array[i] < pivotValue){
				Misc.swap(array, storeIndex, i);
				storeIndex++;
			}
		}
		Misc.swap(array, storeIndex, endIndex);
		
		return storeIndex;
	}
	
	public static long[] radixSort(long[] array, int length){
		for(int k = 1; k <= length; k++){
			array = countSort(array, k);
		}
		
		return array;
	}
	
	/**
	 *sort array of longs by ith digit and returns the sorted array 
	 */
	public static long[] countSort(long[] array, int digitNr){
		int[] frequenzy = new int[10];
		
		//count the frequencys
		for(long elem : array){
			frequenzy[Misc.getIthDigit(digitNr, elem)]++;
		}
		
		int counter = 0;
		//make frequency array into index array
		for(int i = 0; i <= 9; i++){
			int oldcount = frequenzy[i];
			frequenzy[i] = counter;
			counter += oldcount;
		}
		
		long [] output = new long[array.length];
		//reordering according to the ith digit
		for(long elem : array){
			int digit = Misc.getIthDigit(digitNr, elem);
			output[frequenzy[digit]] = elem;
			frequenzy[digit]++;
		}
		
		return output;
	}
	
	public static long[] radixSort2(long[] a) {
	     int w = 64;
	     int d = 1;
		 long[] b = null;
	        for (int p = 0; p < w/d; p++) {
	            long c[] = new long[1<<d];
	            // the next three for loops implement counting-sort
	            b = new long[a.length];
	            for (int i = 0; i < a.length; i++)
	                c[(int)(a[i] >> d*p)&((1<<d)-1)]++;
	            for (int i = 1; i < 1<<d; i++)
	                c[i] += c[i-1];
	            for (int i = a.length-1; i >= 0; i--)
	                b[(int) --c[(int) ((a[i] >> d*p)&((1<<d)-1))]] = a[i];
	            a = b;
	        }
	        return b;
	  }
	
	public static void radixSort3(long[] array, int offset, int end, int shift) {
        int[] last = new int[256];
        int[] pointer = new int[256];

        for (int x=offset; x<end; ++x) {
            ++last[(int) ((array[x] >> shift) & 0xFF)];
        }

        last[0] += offset;
        pointer[0] = offset;
        for (int x=1; x<256; ++x) {
            pointer[x] = last[x-1];
            last[x] += last[x-1];
        }

        for (int x=0; x<256; ++x) {
            while (pointer[x] != last[x]) {
                int value = (int) array[pointer[x]];
                int y = (value >> shift) & 0xff;
                while (x != y) {
                    int temp = (int) array[pointer[y]];
                    array[pointer[y]++] = value;
                    value = temp;
                    y = (value >> shift) & 0xff;
                }
                array[pointer[x]++] = value;
            }
        }
        if (shift > 0) {
            shift -= 8;
            for (int x=0; x<256; ++x) {
                int size = x > 0 ? pointer[x] - pointer[x-1] : pointer[0] - offset;
                if (size > 64) {
                    radixSort3(array, pointer[x] - size, pointer[x], shift);
                } else if (size > 1) {
                    insertionSort(array, pointer[x] - size, pointer[x]);
                    // Arrays.sort(array, pointer[x] - size, pointer[x]);
                }
            }
        }
    }

    private static void insertionSort(long array[], int offset, int end) {
        for (int x=offset; x<end; ++x) {
            for (int y=x; y>offset && array[y-1]>array[y]; y--) {
                int temp = (int) array[y];
                array[y] = array[y-1];
                array[y-1] = temp;
            }
        }
    }
    
    private static int timeStamp;
    
    public static void graphDepthFirstTraversal(String[][] edgeList){
    	if(edgeList.length == 0){
    		return;
    	}else{
    		ArrayList<String> discovered = new ArrayList<String>();
    		timeStamp = 0; 
    		for(String[] edge : edgeList){
    			String node1 = edge[0];
    			String node2 = edge[1];
    			if(!discovered.contains(node1)){
    				recursiveDFTraversal(node1, edgeList, discovered);
    			}else if(!discovered.contains(node2)){
    				recursiveDFTraversal(node2, edgeList, discovered);
    			}
    		}
    	}
    }
    
    private static void recursiveDFTraversal(String node, String[][] edgeList, ArrayList discovered){
    	discovered.add(node);
    	timeStamp += 1;
    	System.out.println(node);
    	//System.out.println("Node=" + node + ", beginTime=" + timeStamp);
    	ArrayList<String> adjacentNodes = getAdjacent(node, edgeList);
    	
    	for(String v : adjacentNodes){
    		if(!discovered.contains(v)){
    			recursiveDFTraversal(v, edgeList, discovered);
    		}
    	}
    	timeStamp+=1;
    	//System.out.println("Node=" + node + ", endTime=" + timeStamp);
    }
    
    private static ArrayList getAdjacent(String node, String[][] edgeList){
    	ArrayList adjacentNodes = new ArrayList<String>();
    	
    	for(int i = 0; i < edgeList.length ; i++){
    		if(edgeList[i][0].equals(node)){
    			adjacentNodes.add(edgeList[i][1]);
    		}
    	}
    	
    	return adjacentNodes;
    }
    
    private static ArrayList getAdjacent(String node, ArrayList<Edge> edges){
    	ArrayList adjacentNodes = new ArrayList<String>();
    	
    	for(int i = 0; i < edges.size() ; i++){
    		if(edges.get(i).getTail().equals(node)){
    			adjacentNodes.add(edges.get(i).getHead());
    		}
    	}
    	
    	return adjacentNodes;
    }
    
    public static void stackDFTTraversal(String[][] edgeList){
    	Stack st = new Stack<String>();
    	ArrayList<String> discovered = new ArrayList<String>();
		
    	for(String[] edge : edgeList){
    		for(int i = 0; i < 2; i++){
    		   String node = edge[i];
    		   if(!discovered.contains(node)){
    			   st.add(node);
    			   
    			   while(!st.empty()){
    				   String n = (String) st.pop();
    				   discovered.add(n);
    				   System.out.println(n);
    				   ArrayList<String> adjacentNodes = getAdjacent(n, edgeList);
    			    	
    			    	for(int j = adjacentNodes.size()-1; j >=0 ; j-- ){
    			    		String v = adjacentNodes.get(j);
    			    		if(!discovered.contains(v)){
    			    			st.add(v);
    			    		}
    			    	}
    			   }
    		   }
    		}
    	}
    }
    
    public static void priorityDFT_Traversal(ArrayList<Edge> edges) {
		PriorityQueue<String[]> priorityQueue = new PriorityQueue<>(10, new Comparator<String[]>() {

			@Override
			public int compare(String[] a, String[] b) {
				if(Integer.parseInt(a[1]) > Integer.parseInt(b[1])){
					return -1;
				}
				
				if(Integer.parseInt(a[1]) < Integer.parseInt(b[1])){
					return 1;
				}
				
				return 0;
			}
			
		});
		
		ArrayList<String> discovered = new ArrayList<String>();
		
    	for(Edge edge : edges){
    		for(int i = 0; i < 2; i++){
    			String node;
    			if(i == 0){
    				node = edge.getTail();
    			}else{
    				node = edge.getHead();
    			}
    		   
    		   if(!discovered.contains(node)){
    			   
    			   String[] pair = {node, Integer.toString(Edge.getDegree(edges, node))};
    			   priorityQueue.add(pair);
    			   
    			   while(!priorityQueue.isEmpty()){
    				   String n = priorityQueue.poll()[0];
    				   if(discovered.contains(n)){
    					   continue;
    				   }
    				   discovered.add(n);
    				   System.out.println(n);
    				   ArrayList<String> adjacentNodes = getAdjacent(n, edges);
    			    	
    			    	for(int j = 0; j < adjacentNodes.size(); j++){
    			    		String v = adjacentNodes.get(j);
    			    		if(!discovered.contains(v)){
    			    			String[] p = {v,Integer.toString(Edge.getDegree(edges, v))};
    			    			priorityQueue.add(p);
    			    		}
    			    	}
    			   }
    		   }
    		}
    	}
    }
}
