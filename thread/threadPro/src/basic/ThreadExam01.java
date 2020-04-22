package basic;

class DigitThread extends Thread {
	public DigitThread(String name) {
		super(name);
	}

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

class AlphaThread extends Thread {
	public AlphaThread(String name) {
		super(name);
	}

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

public class ThreadExam01 {

	public static void main(String[] args) {
		System.out.println("******프로그램 시작******");
		DigitThread digit = new DigitThread("digit");
		AlphaThread alpha = new AlphaThread("alpha");

		digit.start();
		alpha.start();
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
