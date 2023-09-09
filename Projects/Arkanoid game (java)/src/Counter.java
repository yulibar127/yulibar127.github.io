
public class Counter {
	
	private int sum;
	
	 // add number to current count.
	   public Counter() {
		   this.sum = 0;
	   }
	   public Counter(int number) {
		   this.sum = number;
	   }
	   public void increase(int number) {
		   
		   this.sum = this.sum + number;
	   }
	   // subtract number from current count.
	   public void decrease(int number) {
		   
		   this.sum = this.sum - number;
	   }
	   // get current count.
	   public int getValue() {
		   
		   return sum;
	   }

}
