package sec02.exam01_if;

public class IfElseExample {
	public static void main(String[] args) {
		int score = 85;
		
		if(score>=90) {
			System.out.println("점수가 90보다 큽니다.");
			System.out.println("등급은 A 입니다.");
		} else {	
			System.out.println("점수가 90보다 작습니다.");
			System.out.println("등급은 B 입니다.");
		}
	}
}
