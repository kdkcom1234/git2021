package sec07.exam02_default_method_extends;

public class DefaultMethodExtendsExample {
	public static void main(String[] args) {
		ChildInterface1 ci1 = new ChildInterface1() {
			@Override
			public void method1() { /*실행문*/ }
			@Override
			public void method3() { /*실행문*/ }
		};
		
		ci1.method1();
		ci1.method2();  //ParentInterface의 method2() 호출
		ci1.method3();
		
		//-------------------------------------------------------------
		
		ChildInterface2 ci2 = new ChildInterface2() {
			@Override
			public void method1() { /*실행문*/ }
			@Override
			public void method3() { /*실행문*/ }
		};
		
		ci2.method1();
		ci2.method2();  //ChildInterface2의 method2() 호출
		ci2.method3();		
		
		//-------------------------------------------------------------
		
		ChildInterface3 ci3 = new ChildInterface3() {
			@Override
			public void method1() { /*실행문*/ }
			@Override
			public void method2() { /*실행문*/ }
			@Override
			public void method3() { /*실행문*/ }
		};
		
		ci3.method1();
		ci3.method2();  //ChildInterface3 구현 객체의 method2() 호출
		ci3.method3();				
	}
}
