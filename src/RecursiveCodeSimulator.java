
public class RecursiveCodeSimulator {
	
	/**
	 * Simulates function T(n) = a*T(n/b) + f(n)
	 */
	public static int recursiveSimulator(int n, int a, int b){
		//step counter for the simulator
		int counter = 0;
				
		
		//Base
		if(n <= 1){
			return counter;
		}
		
		//a*(T(n/b))
		for(int i = 0; i < a; i++){
			counter += recursiveSimulator(n/b, a, b);
		}
		
		
		/*f(n) part. f(n) must be inserted manually as i do not know how to do
		 functional programming*/
		for(int i = 0; i < n*n; i++){
			counter = counter + 1;
		}
		
		return counter;
	}
}
