import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Exam_papers {
	public static void main(String[] args) {
		try {
			new Exam_papers().seperatePapers(8, new int[]{0,1,0,3,1,2,3,4,1,7});
			
			new Exam_papers().seperatePapers(8, new int[]{0,1,3,4,3,0,1,2,1,7});
			
			new Exam_papers().seperatePapers(8, new int[]{0,2,4,5,1,3,2,4,3,5});
			
			new Exam_papers().seperatePapers(8, new int[]{0,1,0,2,0,3,0,4,0,5});
			
			new Exam_papers().seperatePapers(8, new int[]{0,1,1,2,0,2});
			
			new Exam_papers().seperatePapers(8, new int[]{0,2,4,5,1,3,2,4,3,5});
			
			new Exam_papers().seperatePapers(8, new int[]{0,2,1,3,4,5});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void seperatePapers(int num, int[] relation) throws Exception {
		if(relation.length == 0) {
			return;
		}
		////////
		System.out.print("before arrangement : ");
		for(int i = 0; i < relation.length; i++) {
			System.out.print(" " + relation[i]);
			if(i % 2 == 1) {
				System.out.print(",");
			}
		}
		System.out.println();
		////////
		relation = arrangeArray(num, relation);
		////////
		System.out.print("after arrangement  : ");
		for(int i = 0; i < relation.length; i++) {
			System.out.print(" " + relation[i]);
			if(i % 2 == 1) {
				System.out.print(",");
			}
		}
		System.out.println();
		////////
		
		Set<Integer> set_a = new HashSet<Integer>();
		Set<Integer> set_b = new HashSet<Integer>();
		
		for(int i = 0; i < relation.length; i += 2) {
			if((set_a.contains(relation[i]) && set_a.contains(relation[i + 1]))
					|| (set_b.contains(relation[i]) && set_b.contains(relation[i + 1]))){
				// error
				throw new Exception();
			} else if((set_a.contains(relation[i]) && set_b.contains(relation[i + 1]))
					|| (set_b.contains(relation[i]) && set_a.contains(relation[i + 1]))) {
				continue;
			} else if(set_a.contains(relation[i]) && !set_b.contains(relation[i + 1])) {
				set_b.add(relation[i + 1]);
			} else if(set_b.contains(relation[i]) && !set_a.contains(relation[i + 1])) {
				set_a.add(relation[i + 1]);
			} else if(!set_a.contains(relation[i]) && set_b.contains(relation[i + 1])) {
				set_a.add(relation[i]);
			} else if(!set_b.contains(relation[i]) && set_a.contains(relation[i + 1])) {
				set_b.add(relation[i]);
			} else if((!set_a.contains(relation[i]) && !set_b.contains(relation[i + 1]))
					&& (!set_b.contains(relation[i]) && !set_a.contains(relation[i + 1]))) {
				set_a.add(relation[i]);
				set_b.add(relation[i + 1]);
			}
		}
		
		for(int i = 0; i < num; i++) {
			if(!set_a.contains(i) && !set_b.contains(i)) {
				set_a.add(i);
			}
		}
		
		System.out.println("set_a : " + set_a.toString());
		
		System.out.println("set_b : " + set_b.toString());
	}
	
	private int[] arrangeArray(int num, int[] relation) {
		boolean[] completeArrange = new boolean[num];
		LinkedList<Integer> list = new LinkedList<Integer>();
		int index = 0;
		
		while(index < relation.length) {
			while(list.size() != 0) {
				int key = list.removeFirst();
				completeArrange[key] = true;
				
				int i = index;
				while(i < relation.length) {
					if(relation[i] == key) {
						if(i % 2 == 1) {
							if(completeArrange[relation[i - 1]] == false) {
								list.add(relation[i - 1]);
							}
							int tempA = relation[index];
							int tempB = relation[index + 1];
							
							relation[index] = relation[i - 1];
							relation[index + 1] = relation[i];
							
							relation[i - 1] = tempA;
							relation[i] = tempB;
						} else {
							if(completeArrange[relation[i + 1]] == false) {
								list.add(relation[i + 1]);
							}
							int tempA = relation[index];
							int tempB = relation[index + 1];
							
							relation[index] = relation[i];
							relation[index + 1] = relation[i + 1];
							
							relation[i] = tempA;
							relation[i + 1] = tempB;
						}
						index += 2;
					}
					i++;
				}
			}
			if(index < relation.length) {
				list.add(relation[index]);
				list.add(relation[index + 1]);
				index += 2;
			}
			
		}
		return relation;
	}
}
