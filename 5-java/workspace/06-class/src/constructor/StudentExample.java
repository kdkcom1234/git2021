package constructor;

public class StudentExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// new Ŭ������()
		// Ŭ������() -> ������(Constructor)
		// Student()

		// �����ڴ� Ŭ������� ������ �̸��� �޼���(�Լ�)
		// �ַ� ��ü�� ������ �� �ʱ�ȭ�� ó����

		// Ŭ������() -> �Ű������� ���� ������
		// �⺻������(default constructor)
		// �⺻�����ڴ� �������� �ʾƵ� Ŭ������ �����.

		// new �����ڸ޼���
		// ������ �޼��带 �����Ͽ� ��ü�� ������

		// Student()��� ������ �޼��带 �����ؼ�
		// Student Ŭ���� ������ ���ο�(new) ��ü�� �����
		// Student Ŭ���� ������ s1 ������ �����ض�.

		// �ڹٿ����� ���� ���� �ʴ� ���
		// ��ü�� �����ϰ� �ʵ忡 ���� ����
		Student s1 = new Student();
		s1.name = "ȫ�浿";
		s1.age = 20;
		s1.semester = 1;
		s1.major = "��ǻ�Ͱ���";

		// �ڹٿ��� ��ü�� �����ϴ� �Ѱ��� ���
		// �����ڷ� �ʵ带 �ʱ�ȭ�Ͽ� ����
		Student s2 = new Student("�����̽�", 21);
		System.out.println(s2.name + " " + s2.age);
		s2.semester = 3;
		s2.major = "�濵��";

		// �ٸ� ������� ��ü ����
		Student s3 = new Student("������", 28, 3, "�ڹ���");
		System.out.println(s3.name + " " + s3.age);
	}

}
