package exercise;

// 1. Ŭ������ import�ϰ�
import constructor.sub.Student;
// �ٸ� ��Ű���� ���� Ŭ�������� ���� Ŭ������ import �Ұ���
//import constructor.Student;

public class MemberExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// �̸�, id�� �Ű������� �޾Ƽ� ��ü ����
		// �ش��ϴ� �����ڸ� ����
		Member member1 = new Member("ȫ�浿", "hong");
		Member member2 = new Member("���ڹ�", "java");
		member1.setAge(20);

		// member1.password // private�̶� �ܺο��� ���� �Ұ���

		// 2. Ŭ������ ��� - constructor.sub.Student
		Student student = new Student();
		System.out.println(student.age); // public���� �����ִ� �ʵ�

		// 2. Ŭ������ ��� - ��Ű������� �ۼ�
		constructor.Student student2 = new constructor.Student();

		System.out.println(member1.getName() + " " + member1.getId());
		System.out.println(member2.getName() + " " + member2.getId());
	}
}
