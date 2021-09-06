package array;

public class CreateByValuesExample {
	public static void main(String[] args) {
		// ������ �迭 ���� �� �ʱ�ȭ
		// �迭Ÿ��[] �迭������ = {.. �ʱ�ȭ ���}
		int[] scores = { 83, 90, 87 };

		// !! Java�� �迭�� ó�� ������� ũ�Ⱑ ������
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);

		// �迭 ����� �߰�, ���� �Ұ�����
		System.out.println("---- �� ���� ----");
		scores[0] = 100;
		System.out.println(scores[0]);
		System.out.println(scores[1]);
		System.out.println(scores[2]);
		scores[0] = 0; // �⺻�� ������ Ÿ���� null ó���� �Ұ�����

		// �������� �迭�� �����
		String[] languages = { "Java", "Typescript", "HTML", "CSS", "ECMAScript" };
		for (String lang : languages) {
			System.out.println(lang);
		}
	}
}
