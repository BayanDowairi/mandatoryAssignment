import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;
import java.util.*;

public class crudeClient {
    String[] msgSequence = new String[8];

    public void setMsgSequence(String sender, String receiver, String message) {
        this.msgSequence[0] = "HELO smtp.gmail.com";
        this.msgSequence[1] = "STARTTLS";
        this.msgSequence[2] = "mail from: " + "<" + sender + ">";
        this.msgSequence[3] = "rcpt to: " + "<" + receiver + ">";
        this.msgSequence[4] = "DATA";
        this.msgSequence[5] = message + "\r\n.";
        this.msgSequence[6] = "QUIT";
    }

    public void sendMail() {

        try {
            Socket createSocket = new Socket("smtp.gmail.com", 587);
            System.out.println("Client: " + "Connection Established");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(createSocket.getInputStream()));

            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(createSocket.getOutputStream()));
            String s, serverMsg;

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            for (int i = 0; i < msgSequence.length; i++) {
                s = msgSequence[i];
                System.out.println("Client: " + s);
                writer.write(s + "\r\n");
                writer.flush();

                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);

                if (s.equals("STARTTLS")) {
                    createSocket = new Socket("smtp.gmail.com", 587);
                    reader = new BufferedReader(new InputStreamReader(createSocket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(createSocket.getOutputStream()));
                    serverMsg = reader.readLine();
                    System.out.println("Server: " + serverMsg);
                    writer.write(s + "\r\n");
                    writer.flush();

                    SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                    SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(createSocket, "smtp.gmail.com", 587, true);

                    reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

                    serverMsg = reader.readLine();
                    System.out.println("Server: " + serverMsg);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
