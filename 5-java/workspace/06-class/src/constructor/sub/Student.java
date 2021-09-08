package constructor.sub;

public class Student {

	// default ���� ������
	// �ܺ� ��Ű�������� ���Ұ�
	String name;

	// public ���� ������
	// ��� ��Ű���� Ŭ�������� ��� ������
	public int age;

	// protected ���� ������
	// �ܺ� ��Ű������ ���Ұ��ε� �� Ŭ������ ��ӹ޾Ƽ�(extends) ����ϸ� ��밡��
	protected int semester;
	String major;

	// �����ε�(Overloading)
	// �޼��� �ñ״�ó(method signature)
	// : �޼��� �̸� + �Ű� ����
	// �޼��� �̸��� �����ϰ� �Ű������� ����, Ÿ��, ������
	// �ٸ� �޼��带 �����ϴ� ��

	// ��ü���� ���α׷����� ������ ������ ����
	// ������(polymorphism) = �پ��� ���¸� ������.
	// �޼��� �����ε�
	// = ��ü�� �޼��尡 �پ��� ���¸� ������ ��

	// �Ű������� ���� �⺻ �����ڴ� Ŭ������ �����
	// �ٸ� �����ڸ� �������� ���� ������ �����ؾ� ��
	public Student() {
		// �ƹ��͵� ó���� ��
		// ��ü ������ ��
	}

	// �����ڸ� ���Ƿ� �����, �⺻�����ڴ� ���ŵ�
	// �̸��� ���̸� �Ű������� �޾Ƽ�
	// ��ü(�ν��Ͻ�)�� �����ϴ� ������ �޼���
	Student(String name, int age) {
//	Student(String _name, int _age) {
		// this.�ʵ�
		// ������� ��ü�� �ʵ忡 ����
		this.name = name;
		this.age = age;

		// ������ ������ java������ �� ������� �ʴ� ���
//		name = _name;
//		age = _age;
	}

	Student(String name, String major) {

	}

	Student(int age, String name) {

	}

	// �̸�, ����, �б�, �а� �ް� �ʵ� �ʱ�ȭ �� ��ü ����
	Student(String name, int age, int semester, String major) {
		this.name = name;
		this.age = age;
		this.semester = semester;
		this.major = major;
	}

	protected void joinCourse() {
	}
}
