import java.io.*;
import java.net.Socket;

public class file_input implements Runnable {

	Socket sc = null;
	String st = null;

	public file_input(Socket sc, String st) {
		this.sc = sc;
		this.st = st;
	}

	@Override
	public void run() {
		try {
			InputStream input = sc.getInputStream();
			OutputStream output = sc.getOutputStream();
			long time = System.currentTimeMillis();
			output.write(("HTTP/1.1 keep-alive " + this.st + " - " + time + "").getBytes());
			output.close();
			input.close();
			System.out.println("::" + time);
		} catch (Exception e) {
		}
	}

}
