import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TriviaServerManager {

	public static void main(String[] args) {
		try {

			ServerSocket serverSocket = new ServerSocket(3333);

			while(true) {
				Socket socket = serverSocket.accept();
				TriviaServerThread thread = new TriviaServerThread(socket);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

