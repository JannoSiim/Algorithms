import java.util.Random;


public class Misc {
  
	private static Random rand = new Random();
	 
	public static long[] createRandomArray(int nrOfElements){
	  long[] numbers = new long[nrOfElements];
	  
	 
	  for(int i = 0; i < numbers.length; i++){
		  numbers[i] = rand.nextLong();
	  }
	  
	  return numbers;
   }
	
	public static long[] createRandomArrayPos(int nrOfElements){
		  long[] numbers = new long[nrOfElements];
		  
		 
		  for(int i = 0; i < numbers.length; i++){
			  numbers[i] = Math.abs(rand.nextLong());
		  }
		  
		  return numbers;
	 }

   /**
    * @param a - start index(included)
    * @param b - end index(included)
    * Gives random integer from range [a,b]
    */
   public static int randomNrFromRange(int a, int b){
	   return rand.nextInt(b-a+1)+a;
   }
   
   /**
    * 
    * @param array
    * @param i - first index
    * @param j - second index
    * Swaps elements with indices i and j in an array
    */
   public static void swap(long[] array, int i, int j){
	   long tempValue = array[j];
	   array[j] = array[i];
	   array[i] = tempValue;
   }
   
   public static long[] getOrderedArray(int length){
	   long[] array = new long[length];
	   
	   for(int i = 0; i < array.length; i++){
		   array[i] = i;
	   }
	   
	   return array;
   }
  
  /**
   *gives i-th digit of number
   */
  public static int getIthDigit(int i, long number){
	  
	  return (int)((number % Math.pow(10, i)) /Math.pow(10, i-1));
  }
	
}
