package in.maxc.kea;

import java.io.IOException;
import java.net.ServerSocket;

public class MaxServer {
    private ServerSocket serverSocket;
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                System.out.println("con");
                Thread t = new MaxServerSocketHandler(serverSocket.accept());
                t.start();
            }
        }catch (IOException e){
            System.out.println("cant bind to socket");
        }
    }
}
