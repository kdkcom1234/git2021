package sec05.exam09_minby_maxby;

import java.util.function.BinaryOperator;

public class OperatorMinByMaxByExample {
	public static void main(String[] args) {
		BinaryOperator<Fruit> binaryOperator;
		Fruit fruit;
		
		binaryOperator = BinaryOperator.minBy((f1,f2)->Integer.compare(f1.price, f2.price));
		fruit = binaryOperator.apply(new Fruit("µþ±â", 6000), new Fruit("¼ö¹Ú", 10000));
		System.out.println(fruit.name);
		
		binaryOperator = BinaryOperator.maxBy((f1,f2)->Integer.compare(f1.price, f2.price));
		fruit = binaryOperator.apply(new Fruit("µþ±â", 6000), new Fruit("¼ö¹Ú", 10000));
		System.out.println(fruit.name);
	}
}