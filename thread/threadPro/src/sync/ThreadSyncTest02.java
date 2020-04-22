package sync;

public class ThreadSyncTest02 {
	public static void main(String[] args) {
		// 1. ������ü �����
		SharedObj obj = new SharedObj();
		obj.acc1 = new Account("111-222-3333", 5000, "����");
		obj.acc2 = new Account("333-444-5555", 5000, "�輭��");

		// 2. ������ ����
		SumPrintThread th1 = new SumPrintThread(obj);
		Thread th2 = new Thread(new TransferThread(obj));

		// 3. ������ ������ start ��Ű��
		th1.start();
		th2.start();
	}

}
