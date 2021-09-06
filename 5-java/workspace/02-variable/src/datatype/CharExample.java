package datatype;

public class CharExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char c1 = 'A';		// 문자를 직접 저장
		char c2 = 65;		// 10진수 코드값
		char c3 = '\u0041';	// 16진수 유니코드
		
		char c4 = '가';
		char c5 = 44032;
		char c6 = '\uac00';
		
		int unicodeA = c1;	
		int unicodeGa = c4;
		
		System.out.println(c1);	// 글자로 출력
		System.out.println(unicodeA);	// 숫자로 출력
		System.out.println(c4);
		System.out.println(unicodeGa);
	}

}
