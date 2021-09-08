package exercise;

public class AccountExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account();

		account.setBalance(10000);
		System.out.println("현재 잔고: " + account.getBalance()); // 예상결과: 10000

		account.setBalance(-100);
		System.out.println("현재 잔고: " + account.getBalance()); // 예상결과: 10000

		account.setBalance(2000000);
		System.out.println("현재 잔고: " + account.getBalance()); // 예상결과: 10000

		account.setBalance(300000);
		System.out.println("현재 잔고: " + account.getBalance()); // 예상결과: 300000
	}

}
