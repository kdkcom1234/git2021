package sec05.exam10_identity;

import java.util.function.UnaryOperator;

public class IdentityExample {
	public static void main(String[] args) {
		UnaryOperator<Member> operator = UnaryOperator.identity();
		Member member = operator.apply(new Member("홍길동", "hong"));
		System.out.println("이름: " + member.getName());
		System.out.println("아이디: " + member.getId());
	}
}
