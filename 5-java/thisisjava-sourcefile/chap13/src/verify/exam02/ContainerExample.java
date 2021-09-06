package verify.exam02;

public class ContainerExample {
	public static void main(String[] args) {
		Container<String> container1 = new Container<String>();
		container1.set("ȫ�浿");
		String str = container1.get();

		Container<Integer> container2 = new Container<Integer>();
		container2.set(6);
		int value = container2.get();
	}
}
