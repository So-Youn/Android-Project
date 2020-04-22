package basic;
/*
 * 멀티쓰레드 프로그래밍
 * 1. Thread 클래스를 상속
 * 2. Run 메소드를 오버라이딩
 * 		=> 쓰레드 프로그래밍으로 작업하고 싶은 내용을 구현 (동시의 실행 흐름으로 표현하고 싶은 내용을 구현)
 * 3. Thread 클래스의 (Thread의 하위 클래스) start 메소드를 호츌
 * 		=> 동시 작업 시작
 * 		=> run을 직접 호출하지 않고 start 메소드를 호출하면 JVM이 실행할 수 있는 상태가 되면
 * 		   자동으로 Thread클래스의 run 메소드를 호출
 * 
 */
class ThreadDemo01 extends Thread{
	public ThreadDemo01(String name) {
		super(name);
	}
	public void run() {
		for(int i=1;i<=20;i++) {
			System.out.print(i+"("+getName()+")");
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
public class ThreadTest01 {
	public static void main(String[] args) {
		System.out.println("******프로그램 시작******");
		ThreadDemo01 t1 = new ThreadDemo01("t1");
		ThreadDemo01 t2 = new ThreadDemo01("t2");
		//t1.run(); -> 단순한 메소드 호출 ( 쓰레드 프로그래밍을 할 수 없다. )
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
