package sec04.exam01_nestedinterface;

public class CallListener implements Button.OnClickListener {
	@Override
	public void onClick() {
		System.out.println("��ȭ�� �̴ϴ�.");
	}
}
