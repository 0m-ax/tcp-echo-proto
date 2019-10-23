package in.maxc.kea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataInputStream  dataInputStream;
    private DataOutputStream dataOutputStream;
    public void start(String ip, int port){
        Scanner keyboard = new Scanner(System.in);
        try {
            socket = new Socket("127.0.0.1", 6969);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String myIP = socket.getLocalSocketAddress().toString();
            dataOutputStream.writeUTF(myIP);
            if(!dataInputStream.readUTF().equals("accept "+myIP)){
                throw new InvalidMessage();
            }
            dataOutputStream.writeUTF("accept");
            if(!dataInputStream.readUTF().equals("I am server")){
                throw new InvalidMessage();
            }
            dataOutputStream.writeUTF("hello, I am new user");
            if(!dataInputStream.readUTF().equals("I am server")){
                throw new InvalidMessage();
            }
            dataOutputStream.writeUTF("Ok, good to know");
            if(!dataInputStream.readUTF().equals("I am server")){
                throw new InvalidMessage();
            }
            while (true){
                dataOutputStream.writeUTF(keyboard.nextLine());
                System.out.println(dataInputStream.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InvalidMessage e){
            System.out.println("rec invalid message");
        }
    }
}
