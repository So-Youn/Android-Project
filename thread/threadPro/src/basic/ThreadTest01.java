package basic;
/*
 * ��Ƽ������ ���α׷���
 * 1. Thread Ŭ������ ���
 * 2. Run �޼ҵ带 �������̵�
 * 		=> ������ ���α׷������� �۾��ϰ� ���� ������ ���� (������ ���� �帧���� ǥ���ϰ� ���� ������ ����)
 * 3. Thread Ŭ������ (Thread�� ���� Ŭ����) start �޼ҵ带 ȣ��
 * 		=> ���� �۾� ����
 * 		=> run�� ���� ȣ������ �ʰ� start �޼ҵ带 ȣ���ϸ� JVM�� ������ �� �ִ� ���°� �Ǹ�
 * 		   �ڵ����� ThreadŬ������ run �޼ҵ带 ȣ��
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
				Thread.sleep(500);// �����帧 �����
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
		System.out.println("******���α׷� ����******");
		ThreadDemo01 t1 = new ThreadDemo01("t1");
		ThreadDemo01 t2 = new ThreadDemo01("t2");
		//t1.run(); -> �ܼ��� �޼ҵ� ȣ�� ( ������ ���α׷����� �� �� ����. )
		t1.start();
		t2.start();
		System.out.println("�۾���....");
		for(int i=1;i<=10;i++) {
			System.out.println("main");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("****���α׷� ���� *****");
	}

}
