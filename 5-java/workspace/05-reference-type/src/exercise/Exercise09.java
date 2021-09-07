package exercise;

import java.util.Scanner;

//https://github.com/kdkcom1234/git2021/blob/master/5-java/thisisjava-exercise/05.%20%EC%B0%B8%EC%A1%B0%20%ED%83%80%EC%9E%85/20210906_121505.jpg
public class Exercise09 {
	public static void main(String[] args) {
		boolean run = true;

		int studentNum = 0;
		int[] scores = null;

		Scanner scanner = new Scanner(System.in); // System.in(standard input stream, 키보드 입력)

		while (run) {
			System.out.println("---------------------------");
			System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
			System.out.println("---------------------------");
			System.out.print("선택> ");

			// 숫자 입력값을 콘솔창에서 입력 받음
			int selectNo = scanner.nextInt();

			switch (selectNo) {
			case 1:
				System.out.println("학생수> ");
				// 학생수를 입력받음
				// 입력한 학생수 만큼 배열크기를 초기화
				scores = new int[scanner.nextInt()];
				break;
			case 2:
				// 배열크기만큼 반복해서 점수를 입력 받음
				for (int i = 0; i < scores.length; i++) {
					System.out.print("scores[" + i + "]> ");
					scores[i] = scanner.nextInt();
				}
				break;
			case 3:
				// 배열크기만큼 반복해서 점수 목록을 출력
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
				// 최고점수와 평균점수 출력
				System.out.println("최고 점수: " + max);
				System.out.println("평균 점수: " + sum * 1.0 / scores.length);
				break;
			case 5:
				// run false로 반복문 탈출
				run = false;
				break;
			}
		}

		System.out.println("프로그램 종료");
	}

}
