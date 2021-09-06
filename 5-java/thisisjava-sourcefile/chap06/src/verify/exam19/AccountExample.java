package verify.exam19;

public class AccountExample {
	public static void main(String[] args) {
		Account account = new Account();
		
		account.setBalance(10000);
		System.out.println("ÇöÀç ÀÜ°í: " + account.getBalance());
		
		account.setBalance(-100);
		System.out.println("ÇöÀç ÀÜ°í: " + account.getBalance());
		
		account.setBalance(2000000);
		System.out.println("ÇöÀç ÀÜ°í: " + account.getBalance());
		
		account.setBalance(300000);
		System.out.println("ÇöÀç ÀÜ°í: " + account.getBalance());
	}
}
