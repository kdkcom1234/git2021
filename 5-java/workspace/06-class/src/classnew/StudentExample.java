package classnew;

public class StudentExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// new Ŭ������()
		// : �ν��Ͻ�(instance)�� ����, instantiation
		// -> Ŭ���� ������ �޸� ������ ����
		// �ν��Ͻ� == ��ü(object)

		// Student s1 = ������ �ν��Ͻ�
		// Ŭ������ �ν��Ͻ����� = new Ŭ������()

		// ȫ�浿�̶�� �л��� ���� ����
		Student s1 = new Student();
		// �ʵ�: ȫ�浿�̶�� �л��� ������
		// �ʵ忡 ����: �ν��Ͻ�������.�ʵ��
		s1.name = "ȫ�浿";
		s1.age = 20;
		s1.semester = 2;
		s1.major = "��ǻ�Ͱ���";
		System.out.println(s1.name + " " + s1.age);
		// �޼���: ȫ�浿�̶�� �л��� ������û ���
		// 2�б�, ��ǻ�Ͱ��а� �л��� ������û...
		// �޼��忡 ����: �ν��Ͻ�������.�޼����
		s1.joinCourse();

		// �����̽���� �л��� ���� ����
		Student s2 = new Student();
		// �ʵ�: �����̽���� �л��� ������
		s2.name = "John Smith";
		s2.age = 21;
		s2.semester = 3;
		s2.major = "�濵��";
		System.out.println(s2.name + " " + s2.age);
		// �޼���: �����̽���� �л��� ������û ���
		// 3�б�, �濵�� �л��� ������û
		s2.joinCourse();
	}

}
