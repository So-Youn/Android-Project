package sync;
// 공유 객체 - 모든 쓰레드가 공유해서 사용하는 객체
// synchronized : 사용하는 동안 lock을 걸겠다는 의미
public class Toilet {
	/*
	synchronized {
		
	} 를 이용해서 부분 코드에 lock 적용 가능
	*/
	public synchronized void open(String user_name) {
		System.out.println(user_name+"가 문을 열고 들어옴");
		for(int i=1;i<=100000000;i++) {
			if(i==10000) {
				System.out.println("끙아...ㅎ");
			}
		}
		System.out.println(user_name+"가 종료 후에 나감");
	}

}
