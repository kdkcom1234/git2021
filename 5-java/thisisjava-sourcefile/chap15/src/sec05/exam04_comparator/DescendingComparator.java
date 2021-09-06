package sec05.exam04_comparator;

import java.util.Comparator;

public class DescendingComparator implements Comparator<Fruit> {
	@Override
	public int compare(Fruit o1, Fruit o2) {
		if(o1.price < o2.price) return 1;
		else if(o1.price == o2.price) return 0;
		else return -1;	
	}
}


/*public class DescendingComparator implements Comparator {	
	public int compare(Object o1, Object o2) {
		Fruit fruit1 = (Fruit) o1;
		Fruit fruit2 = (Fruit) o2;
		if(fruit1.price < fruit2.price) return 1;
		else if(fruit1.price == fruit2.price) return 0;
		else return -1;
	}
}*/

