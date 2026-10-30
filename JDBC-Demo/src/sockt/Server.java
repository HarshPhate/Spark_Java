package sockt;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("Server is waiting...");

            Socket s = ss.accept();
            System.out.println("Client connected");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String msg = dis.readUTF();
            System.out.println("Client says: " + msg);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Hello from server");

            ss.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}