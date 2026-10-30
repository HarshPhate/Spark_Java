package sockt;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Hello server");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String msg = dis.readUTF();
            System.out.println("Server says: " + msg);

            s.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}