package sec10.exam04_singleton;

public class SingletonExample {
	public static void main(String[] args) {
		/*
		Singleton obj1 = new Singleton();  //컴파일 에러
		Singleton obj2 = new Singleton();  //컴파일 에러
		*/
		
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance();
		
		if(obj1 == obj2) {
			System.out.println("같은 Singleton 객체 입니다.");
		} else {
			System.out.println("다른 Singleton 객체 입니다.");
		}
	}
}
