
public class Thred_main {

	public static void main(String[] args) {

		Thread_pool ser = new Thread_pool(9000);
		new Thread("ser").start();

		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			System.out.println("::::" + e);
		}
		System.out.println("Stopping Server");
		ser.stop();

	}

}
