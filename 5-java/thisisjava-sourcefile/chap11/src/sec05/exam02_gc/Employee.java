package sec05.exam02_gc;

public class Employee {
	public int eno;	
	
	public Employee(int eno) {
		this.eno = eno;
		System.out.println("Employee(" + eno + ") �� �޸𸮿� ������");
	}

	public void finalize() {
		System.out.println("Employee(" + eno + ") �� �޸𸮿��� ���ŵ�");
	}
}

