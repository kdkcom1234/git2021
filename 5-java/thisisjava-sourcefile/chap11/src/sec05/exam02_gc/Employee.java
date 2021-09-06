package sec05.exam02_gc;

public class Employee {
	public int eno;	
	
	public Employee(int eno) {
		this.eno = eno;
		System.out.println("Employee(" + eno + ") 가 메모리에 생성됨");
	}

	public void finalize() {
		System.out.println("Employee(" + eno + ") 이 메모리에서 제거됨");
	}
}

