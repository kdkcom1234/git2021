package super_constructor;

public class Parent {
	public String nation;

	// ȣ�� - 2(�Ͻ���)
	public Parent() {
		this("���ѹα�"); // -> Parent("���ѹα�") ȣ��
		// ��� - 2��
		System.out.println("Parent() call");
	}

	// ȣ�� - 3
	public Parent(String nation) {
		this.nation = nation;
		// ��� - 1
		System.out.println("Parent(String nation) call");
	}
}
