package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebServer {
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while(true) {
			Socket connection = socket.accept();
			handleRequest(connection);
		}
	}

	private static void handleRequest(Socket connection) throws IOException {
		
		OutputStream os;
		os = connection.getOutputStream();
		os.write("oak".getBytes());
		os.flush();
		os.close();
	}
}
