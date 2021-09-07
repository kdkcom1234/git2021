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
				System.out.println("�л���> ");
				// �л����� �Է¹���
				// �Է��� �л��� ��ŭ �迭ũ�⸦ �ʱ�ȭ
				scores = new int[scanner.nextInt()];
				break;
			case 2:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ������ �Է� ����
				for (int i = 0; i < scores.length; i++) {
					System.out.print("scores[" + i + "]> ");
					scores[i] = scanner.nextInt();
				}
				break;
			case 3:
				// �迭ũ�⸸ŭ �ݺ��ؼ� ���� ����� ���
				for (int i = 0; i < scores.length; i++) {
					System.out.println("scores[" + i + "]: " + scores[i]);
				}
				break;
			case 4:
				int sum = 0;
				int max = 0;
				for (int score : scores) {
					sum += score;
					max = score > max ? score : max;
				}
				// �ְ������� ������� ���
				System.out.println("�ְ� ����: " + max);
				System.out.println("��� ����: " + sum * 1.0 / scores.length);
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
