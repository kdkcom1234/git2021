package sec05.exam01_bounded_type;

public class Util {
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue(); 
		//System.out.println(t1.getClass().getName());
		double v2 = t2.doubleValue();
		//System.out.println(t2.getClass().getName());
		return Double.compare(v1, v2);
	}
}
