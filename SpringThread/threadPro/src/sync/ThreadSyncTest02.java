package sync;

public class ThreadSyncTest02 {
	public static void main(String[] args) {
		// 1. 공유객체 만들기
		SharedObj obj = new SharedObj();
		obj.acc1 = new Account("111-222-3333", 5000, "현빈");
		obj.acc2 = new Account("333-444-5555", 5000, "김서연");

		// 2. 쓰레드 생성
		SumPrintThread th1 = new SumPrintThread(obj);
		Thread th2 = new Thread(new TransferThread(obj));

		// 3. 생성한 쓰레드 start 시키기
		th1.start();
		th2.start();
	}

}
