package sync;

public class TransferThread implements Runnable {
	SharedObj obj;

	public TransferThread(SharedObj obj) {
		super();
		this.obj = obj;
	}

	@Override
	public void run() {
		// 공유 객체가 갖고있는 메소드를 호출
		for (int i = 1; i <= 20; i++) {
			synchronized (obj) {
				obj.acc1.withdraw(100);
				System.out.println("100만원을 출금했습니다.");
				obj.acc2.deposit(100);
				System.out.println("100만원을 입금했습니다.");
			}
		}
	}
}
