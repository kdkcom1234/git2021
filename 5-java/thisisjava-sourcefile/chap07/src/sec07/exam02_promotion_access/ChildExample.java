package sec07.exam02_promotion_access;

public class ChildExample {
	public static void main(String[] args) {
		  Child child = new Child();

		  Parent parent = child;

		  parent.method1();

		  parent.method2();

		  //parent.method3();  (호출 불가능)
	}
}
