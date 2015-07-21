import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Shooter {
	public static void main(String[] args) {
		new Shooter().getMaxScore(new int[]{100000, 30,100000,50000000,400000,80});
		
		new Shooter().getMaxScore(new int[]{3,1,5,4,8});
		
		new Shooter().getMaxScore(new int[]{100,90,80,11,0,200,60});
	}
	
	private int getMaxScore(int[] targets) {
		if(targets.length == 0) {
			return -1;
		}
		
		int[] tempScore = new int[targets.length];
		int lastSelectedIndex = 0;
		Map<Integer, LinkedList<Integer>> map = new HashMap<Integer, LinkedList<Integer>>();
		
		
		for(int i = 0; i < targets.length; i++) {
			if(i == 0) {
				tempScore[i] = targets[i];
				lastSelectedIndex = 0;
				LinkedList<Integer> list = new LinkedList<Integer>();
				list.addLast(0);
				map.put(0, list);
			} else if(i == 1) {
				if(targets[1] > targets[0]) {
					tempScore[1] = targets[1];
					lastSelectedIndex = 1;
					LinkedList<Integer> list = new LinkedList<Integer>();
					list.addLast(1);
					map.put(1, list);
				} else {
					tempScore[1] = targets[0];
					LinkedList<Integer> list = new LinkedList<Integer>();
					list.addLast(0);
					map.put(1, list);
				}
			} else {
				if(lastSelectedIndex == i - 2) {
					tempScore[i] = tempScore[i - 2] + targets[i];
					lastSelectedIndex = i;
					LinkedList<Integer> list = (LinkedList<Integer>) map.get(i - 2).clone();
					list.addLast(i);
					map.put(i, list);
				} else if(lastSelectedIndex == i - 1) {
					if(tempScore[i - 2] + targets[i] > tempScore[i - 1]) {
						tempScore[i] = tempScore[i - 2] + targets[i];
						lastSelectedIndex = i;
						LinkedList<Integer> list = (LinkedList<Integer>) map.get(i - 2).clone();
						list.addLast(i);
						map.put(i, list);
					} else {
						tempScore[i] = tempScore[i - 1];
						LinkedList<Integer> list = (LinkedList<Integer>) map.get(i - 1).clone();
						map.put(i, list);
					}
				}
			}
		}
		System.out.print(map.get(targets.length - 1).toString());
		System.out.println();
		System.out.println(tempScore[targets.length - 1]);
		System.out.println();
		return tempScore[targets.length - 1];
	}
}
