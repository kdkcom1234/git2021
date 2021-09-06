package sec11.exam01_arrays;

import java.util.Arrays;

public class SearchExample {
	public static void main(String[] args) {
		//±âº» Å¸ÀÔ°ª °Ë»ö
		int[] scores = { 99, 97, 98 };
		Arrays.sort(scores);
		int index = Arrays.binarySearch(scores, 99);
		System.out.println("Ã£Àº ÀÎµ¦½º: " + index);
		
		//¹®ÀÚ¿­ °Ë»ö
		String[] names = { "È«±æµ¿", "¹Úµ¿¼ö", "±è¹Î¼ö" };
		Arrays.sort(names);
		index = Arrays.binarySearch(names, "È«±æµ¿");
		System.out.println("Ã£Àº ÀÎµ¦½º: " + index);
		
		//°´Ã¼ °Ë»ö
		Member m1 = new Member("È«±æµ¿");
		Member m2 = new Member("¹Úµ¿¼ö");
		Member m3 = new Member("±è¹Î¼ö");
		Member[] members = { m1, m2, m3 };
		Arrays.sort(members);
		index = Arrays.binarySearch(members, m1);
		System.out.println("Ã£Àº ÀÎµ¦½º: " + index);
	}
}
