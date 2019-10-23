package in.maxc.kea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        if(args[0].equals("server")){
            MaxServer s = new MaxServer();
            s.start(6969);
        }else if(args[0].equals("client")){
            Client client = new Client();
            client.start("127.0.0.1",6969);
            System.out.println(client);
        }else{
            System.out.println(args[0]);
        }
    }
}
