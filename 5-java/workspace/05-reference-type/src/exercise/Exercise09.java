package exercise;

import java.util.Scanner;

//https://github.com/kdkcom1234/git2021/blob/master/5-java/thisisjava-exercise/05.%20%EC%B0%B8%EC%A1%B0%20%ED%83%80%EC%9E%85/20210906_121505.jpg
public class Exercise09 {
	public static void main(String[] args) {
		boolean run = true;

		int studentNum = 0;
		int[] scores = null;

		Scanner scanner = new Scanner(System.in); // System.in(standard input stream, Ű���� �Է�)

		while (run) {
			System.out.println("---------------------------");
			System.out.println("1.�л��� | 2.�����Է� | 3.��������Ʈ | 4.�м� | 5.����");
			System.out.println("---------------------------");
			System.out.print("����> ");

			// ���� �Է°��� �ܼ�â���� �Է� ����
			int selectNo = scanner.nextInt();

			switch (selectNo) {
			case 1:
				// �л����� �Է¹���
				// �Է��� �л��� ��ŭ �迭ũ�⸦ �ʱ�ȭ
				break;
			case 2:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ������ �Է� ����
				break;
			case 3:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ���� ����� ���
				break;
			case 4:
				// �ְ������� ������� ���
				break;
			case 5:
				// run false�� �ݺ��� Ż��
				run = false;
				break;
			}
		}

		System.out.println("���α׷� ����");
	}

}
