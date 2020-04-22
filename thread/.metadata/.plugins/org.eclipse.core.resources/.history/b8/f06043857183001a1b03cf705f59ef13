package basic;
/*
 * 멀티쓰레드 프로그래밍
 * 1. Runnable 인터페이스를 구현하는 클래스를 생성
 * 2. Runnable인터페이스가 갖고 있는 추상메소드인 run메소드를 오버라이딩
 * 		=> 동시 작업하고 싶은 내용을 정의
 * 3. 작성한 Runnable 객체를 이용해서 Thread객체를 생성
 * 		=> Thread객체를 생성하면서 매개변수로 Runnable 객체를 전달
 * 4. 생성한 Thread객체의 start를 호출
 */
class RunnableDemo01 implements Runnable{
	public void run() {
		for(int i=1;i<=20;i++) {
			System.out.print(i+"("+
		Thread.currentThread().getName()+")");
			//currentThread현재 실행중인 Thread객체 리턴
			try {
				Thread.sleep(500);// 실행흐름 멈춘다
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			if(i%5==0) {
				System.out.println();
			}
		}
	}
}
	public class RunnableTest01{
		public static void main(String[] args) {
			System.out.println("******프로그램 시작******");
			RunnableDemo01 r1 = new RunnableDemo01();
			RunnableDemo01 r2 = new RunnableDemo01();
			
			Thread t1 = new Thread(r1);
			Thread t2 = new Thread(r2);
			t1.start();
			t2.start();
			System.out.println("작업중....");
			for(int i=1;i<=10;i++) {
				System.out.println("main");
				try {
					Thread.sleep(500);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("****프로그램 종료 *****");
		}

	}
