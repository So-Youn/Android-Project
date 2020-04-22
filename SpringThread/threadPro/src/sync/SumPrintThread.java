package sync;

public class SumPrintThread extends Thread{
	SharedObj obj;
	
	public SumPrintThread(SharedObj obj) {
		super();
		this.obj = obj;
	}
	public void run() {
		for(int i=1;i<=10;i++) {
			synchronized (obj) { // 코드 블럭만 lock을 적용하겠다는 의미
				long total = obj.acc1.getBalance()+obj.acc2.getBalance();
				System.out.println("두 계좌의 잔액은 =>>>"+ total);
			}
		}
	}

}
