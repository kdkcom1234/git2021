package com.git.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootConfiguration: ������ ������ �� �� �ֵ�����. ��ü�����ڸ� ����(IoC �����̳�)
//@EnableAutoConfiguration: ����ϴ� �������� ���� �ڵ����� ȯ���� ������
//  -> spring-boot-starter-web: 
//       -> embeded Tomcat �������� ������, 8080��Ʈ ������
//       -> Dispatcher Servlet ��ü�� ������
//  -> spring-boot-devtools: �ڵ带 ��ġ�� ������ �ٽ� ��������
//@ComponentScan: ������Ʈ���� �˻��Ͽ�(mainŬ���� ����/���� ��Ű�鿡��) �̱������� ��ü������ ��
//  -> Spring Framework���� ������Ʈ(��-@Controller) ������̼��� �ִ� Ŭ�������� �˻���
//  -> Spring Framework���� �̱������� ��ü�� ������

@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

}
