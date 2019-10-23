package in.maxc.kea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MaxServerSocketHandler extends Thread  {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public MaxServerSocketHandler(Socket s) throws IOException {
        this.socket = s;
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }
    @Override
    public void run(){
        try {
            dataOutputStream.writeUTF("accept "+dataInputStream.readUTF());
            if(!dataInputStream.readUTF().equals("accept")){
                throw new InvalidMessage();
            }
            dataOutputStream.writeUTF("I am server");
            if(!dataInputStream.readUTF().equals("hello, I am new user")){
                throw new InvalidMessage();
            }
            dataOutputStream.writeUTF("I am server");
            if(!dataInputStream.readUTF().equals("Ok, good to know")){
                throw new InvalidMessage();
            }
            dataOutputStream.writeUTF("I am server");
            while (true){
                dataOutputStream.writeUTF(dataInputStream.readUTF());
            }
        }catch (IOException e){
            System.out.println("error with socket IO");
        }
        catch (InvalidMessage e){
            System.out.println("invalid message");
        }
        // close socket
        try {
            socket.close();
        }catch (IOException e){

        }
    }
}