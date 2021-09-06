package sec03.exam02_while;
public class  WhileKeyControlExample {
	public static void main(String[] args) throws Exception {
		boolean run = true;		
		int speed = 0;
		int keyCode = 0;
		
		while(run) {
			if(keyCode!=13 && keyCode!=10) {
				System.out.println("-----------------------------");
				System.out.println("1.증속 | 2.감속 | 3.중지");
				System.out.println("-----------------------------");
				System.out.print("선택: ");
			}
			
			keyCode = System.in.read();
			
			if (keyCode == 49) { //1
				speed++;
				System.out.println("현재 속도=" + speed);
			} else if (keyCode == 50) { //2
				speed--;
				System.out.println("현재 속도=" + speed);
			} else if (keyCode == 51) { //3
				run = false;
			}
		}	
		
		System.out.println("프로그램 종료");
	}
}


