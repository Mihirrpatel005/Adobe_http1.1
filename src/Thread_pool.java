import java.net.*;
import java.util.concurrent.*;

public class Thread_pool {

	int sp = 8080;
	ServerSocket sc = null;
	boolean is_Stopped = false;
	Thread runt = null;
	ExecutorService threadPool = Executors.newFixedThreadPool(10);

	public Thread_pool(int port) {
		this.sp = port;
	}

	public void run() {
		synchronized (this) {
			this.runt = Thread.currentThread();
		}
		openServerSocket();
		while (!isStopped()) {
			Socket cs = null;
			try {
				cs = this.sc.accept();
			} catch (Exception e) {
				if (isStopped()) {
					System.out.println("Server Stop error::::::");
					break;
				}
				throw new RuntimeException("Error client connection:::::::::::", e);
			}
			this.threadPool.execute(new file_input(cs, "Thread Pooled Server"));
		}
		this.threadPool.shutdown();
		System.out.println("Server Stopped.");
	}

	private synchronized boolean isStopped() {
		return this.is_Stopped;
	}

	public synchronized void stop() {
		this.is_Stopped = true;
		try {
			this.sc.close();
		} catch (Exception e) {
		}
	}

	private void openServerSocket() {
		try {
			this.sc = new ServerSocket(this.sp);
		} catch (Exception e) {
		}
	}
}
