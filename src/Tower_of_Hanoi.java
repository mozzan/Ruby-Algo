
public class Tower_of_Hanoi {
	public static void main(String[] args) {
		
		
		float result  = new Tower_of_Hanoi().getExpectedPrice(new int[] {1,1,1});
		System.out.println(result);
	}
	
	private float getExpectedPrice(int[] plates) {
		if(plates.length == 0)
			return -1;
		int length = plates.length;
		float[] expected = new float[length];
		
		expected[0] = plates[0];
		for(int i = 1; i < length; i++) {
			for(int j = i; j >= 1; j--) {
				float tmp = 0;
				for(int x = 0; x < j; x++) {
					tmp +=  expected[x];
				}
				expected[j] = tmp + plates[j];
			}
			expected[0] = (float)plates[0];
			for(int j = 0; j <= i; j++) {
				System.out.println("j : " + j);
				expected[j] = expected[j] / (i + 1);
			}
		}
		
		float total = 0;
		for(float e : expected) {
			total += e;
		}
		
		return total;
	}
}
