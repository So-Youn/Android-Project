package basic;

class DigitThread2 implements Runnable {


	public void run() {
		// 1부터 100까지 출력하는 코드
		for (int i = 1; i <= 100; i++) {
			System.out.print(i + " ");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (i % 10 == 0) {
				System.out.println();
			}
		}
	}
}

class AlphaThread2 implements Runnable {
	
	public void run() {
		//A~Z까지 출력
		for (char k = 'A'; k <= 'Z'; k++) {
			System.out.print(k + " ");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

public class RunnableTest02 {

	public static void main(String[] args) {
		System.out.println("******프로그램 시작******");
		/*DigitThread2 digit = new DigitThread2();
		AlphaThread2 alpha = new AlphaThread2();
		
		Thread alpha2 = new Thread(alpha);
		Thread digit2 = new Thread(digit);*/
		
		new Thread(new AlphaThread2()).start();
		Thread thread2 = new Thread(new DigitThread2());
		thread2.start();
		for (int i = 1; i <= 10; i++) {
			System.out.print("main");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("******프로그램 끝******");

	}

}
