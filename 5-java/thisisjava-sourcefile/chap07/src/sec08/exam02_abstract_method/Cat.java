package sec08.exam02_abstract_method;

public class Cat extends Animal {
	public Cat() {
		this.kind = "Æ÷À¯·ù";
	}

	@Override
	public void sound() {
		System.out.println("¾ß¿Ë");
	}
}
