package sec05.exam07_andthen_compose;

import java.util.function.Consumer;

public class ConsumerAndThenExample {
	public static void main(String[] args) {
		Consumer<Member> consumerA = (m) -> { 
			System.out.println("consumerA: " + m.getName()); 
		};
		
		Consumer<Member> consumerB = (m) -> { 
			System.out.println("consumerB: " + m.getId()); 
		};
		
		Consumer<Member> consumerAB = consumerA.andThen(consumerB);
		consumerAB.accept(new Member("ȫ�浿", "hong", null));
	}
}
