package basic;
/*
 * 쓰레드 종료
 * 1. 임의의 변수를 선언해서 종료하는 방법
 * 	  ------
 * 		flag 변수 
 * 		- 변수에 저장된 값에 따라서 처리할 수 있도록 구현 (실행 or 종료 - boolean)
 * 		- 변수 값 체크 (오래 걸리는 작업이 있는 경우 중간에 이 값을 체크해서 쓰레드를 종료할 수 있다.)
 */
class stopThread01 extends Thread{
	private boolean state = true; // 초깃값은 false 기 때문에
	public void run() {
		while(state) { 
			// 보통 무한 루프 상태에서 Thread를 던진다
			// 종료 후에도 계속 작동한다. 
			System.out.println("쓰레드 실행 중~~");
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		// 무한 루프를 빠져나와야 실행되는 코드
		System.out.println(" 현재 상태 : 종료 상태"); 
	}
	// 쓰레드의 상태를 조정할 수 있는 변수의 값을 변경하는 메소드
	public void stopThread() {
		state = false; 
	}
}
public class StopThreadTest01 {
	public static void main(String[] args) {
		System.out.println("main쓰레드 시작");
		stopThread01 t1 = new stopThread01();
		t1.start();
		try {
			Thread.sleep(3000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.stopThread();
		System.out.println("main 쓰레드 종료");
	}

}
