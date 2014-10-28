import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Main {

	public static void main(String[] args) {
		
		
		String[][] edgeList = {{"A", "D"}, {"A", "C"}, {"N", "A"}, {"A", "G"}, {"D", "B"},
				 {"B", "K"}, {"K", "D"}, {"C", "D"}, {"C", "E"}, {"E", "N"}, {"N", "G"}, {"E", "F"},
				 {"E", "H"}, {"H", "G"}, {"F", "I"}, {"J", "I"}, {"M", "F"}, {"M", "L"}, {"L", "M"}};
		
		String[][] edgeListUndirected = {{"A", "D"},{"D", "A"}, {"A", "C"}, {"C", "A"},{"N", "A"}, {"A", "N"},{"A", "G"},
				{"G", "A",},{"D", "B"},{"B", "D"},{"B", "K"}, {"K", "B"},{"K", "D"},{"D", "K"}, 
				{"C", "D"}, {"D", "C"},{"C", "E"}, {"E", "C"},{"E", "N"}, {"N", "E"},{"N", "G"},{"G", "N"}, {"E", "F"},
				{"F", "E"},{"E", "H"}, {"H", "E"},{"H", "G"}, {"G", "H"},{"F", "I"}, {"I", "F"},{"J", "I"}, {"I", "J"},
				{"M", "F"}, {"F", "M"},{"M", "L"}, {"L", "M"}};
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(String[] e : edgeList){
		  edges.add(new Edge(e[0], e[1]));	
		}
		
		Algorithms.priorityDFT_Traversal(edges);
		
		/*
	
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("C:/Users/Janno/Dropbox/kool/Advanced algorithms/HW 6/1000_keys.txt"));
			String line = null;
			
			int index = 0;
			while ((line = reader.readLine()) != null) {
			    values[index] = Integer.parseInt(line);
			    index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] bestFeatures = new int[4];

		for(int p = 1000000; p < 1010000; p++ ){
			for(int i = 0; i < 100; i++){
				int[] features = testHash(p, 1000, values);
				
				if(bestFeatures[3] < features[3]){
					bestFeatures = features;
				}
			}
		}
		
		System.out.println(Arrays.toString(bestFeatures));
		System.out.println(Arrays.toString(testHash(1099000, 1000, values)));*/
		
		
/*	  int sizes1[] = {100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 750000,1000000};	     
	  int sizes[] = {500000, 750000,1000000};
	  
	  for(int length : sizes){

		 File data = new File(length + "binarySearch.txt");
		 

		try {
			 FileWriter writer = new FileWriter(data);
			 
			 for(int i = 0 ; i <5; i++){
				 int counter = 0;
				 long array[] = Misc.getOrderedArray(length);
				 long beginTime = System.currentTimeMillis();
				 long endTime = beginTime;
				 
				 System.out.println(i);
				 while(endTime - beginTime < 60000){
					 long key = Misc.randomNrFromRange(0, length-1);
					 Arrays.binarySearch(array, key);
					 endTime = System.currentTimeMillis();
					 counter++;
				 }
				 
				 writer.append(counter + "\n");
			 }
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }  
	  */
	}
	
	/**
	 * returns the number of collision the hash function generates.
	 */
	public static int[] testHash(int p, int arrayLen, int[] numbers){
		
		//[a, b, p, collisions]
		int[] features = new int[4];
		
		RandomHash hash = new RandomHash(p, arrayLen);
		Set<Integer> hashValueSet = new HashSet<Integer>();
		
		features[0] = hash.getA();
		features[1] = hash.getB();
		features[2] = p;
		int count = 0;
		
		for(int i : numbers){
			int hashValue = hash.getHash(i);
			if(hashValueSet.contains(hashValue)){
				count++;
			}else{
				hashValueSet.add(hashValue);
			}
		}
		
		features[3] = count;
		
		return features;
	}
	
	public static long measureSpeed(int length){
		
		long[] randomLongs = Misc.createRandomArrayPos(length);
		long beginTime = System.currentTimeMillis();
		//Change this to measure something else
		Algorithms.radixSort3(randomLongs, 0, randomLongs.length, 1);
		//Arrays.sort(randomLongs);
		long endTime = System.currentTimeMillis();
		
		return endTime-beginTime;
	}

}
