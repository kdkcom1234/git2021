package sec02.exam02_threadname;

public class ThreadA extends Thread {	
	public ThreadA() {
		setName("ThreadA");
	}
	
	public void run() {		
		for(int i=0; i<2; i++) {		
			System.out.println(getName() + "�� ����� ����");
		}
	}
}

