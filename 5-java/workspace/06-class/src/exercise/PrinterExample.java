package exercise;

public class PrinterExample {
	public static void main(String[] args) {
		Printer printer = new Printer();
		printer.println(10);
		printer.println(true);
		printer.println(5.7);
		printer.println("ȫ�浿");

		// ����� �� �տ� ���ڷ����͸� �߰��ؼ� ���
		// println("�޽����Դϴ�.", 1)
		// 1 �޽����Դϴ�.
		printer.println("�޽����Դϴ�", 1);

		// ����� �� ��/�ڿ� ���ڷ����͸� �߰��ؼ� ���
		// println("�˸��޽����Դϴ�.", 1, "!!")
		// 1 �˸��޽����Դϴ� !!
		printer.println("�˸��޽����Դϴ�", 1, "!!");
	}
}
