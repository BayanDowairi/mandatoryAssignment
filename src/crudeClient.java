import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.*;
import java.io.*;
import java.util.Base64;

public class crudeClient {
    // Credentials
    public static String user = "bayouna96@gmail.com";
    public static String pass = "qzjiclxjatriavwz";
    private String message;
    String[] clientResponse = new String[10];
    String username = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
    String password = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));



    public void setClientSequence() throws IOException {
        this.clientResponse[0] = "AUTH LOGIN\r\n";
        this.clientResponse[1] = username + "\r\n";
        this.clientResponse[2] = password + "\r\n";
        this.clientResponse[3] = "MAIL FROM:<bayouna96@gmail.com>\r\n";
        this.clientResponse[4] = "RCPT TO:<nr.salmani@gmail.com>\r\n";
        this.clientResponse[5] = "DATA\r\n";
        this.clientResponse[6] = "Subject: Email test\r\n";
        this.clientResponse[7] = "Helloooo, It's group 11. It is a mail from Gmail:)\r\n";
        this.clientResponse[8] = ".\r\n";
        this.clientResponse[9] = "QUIT\r\n";


    }
    public void sendMail(){
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("smtp.gmail.com", 465);
            BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

            DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
            String s , serverMsg;

            s = "EHLO smtp.gmail.com\r\n";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            //Thread.sleep(1000);

            // Read from the server
            /*
            Server:220 smtp.gmail.com ESMTP j3-20020a1709062a0300b008cc920469b5sm3404970eje.18 - gsmtp
            Server:250-smtp.gmail.com at your service, [213.237.90.36]
            Server:250-SIZE 35882577
            Server:250-8BITMIME
            Server:250-AUTH LOGIN PLAIN XOAUTH2 PLAIN-CLIENTTOKEN OAUTHBEARER XOAUTH
            Server:250-ENHANCEDSTATUSCODES
            Server:250-PIPELINING
            Server:250-CHUNKING
             */

            for (int i = 0; i < 8; i++) {
                serverMsg = reader.readLine(); // 250-smtp.gmail.com at your service, [2.109.71.78]
                System.out.println("Server:" + serverMsg);

            }
            /*serverMsg = reader.readLine(); // 220 smtp.gmail.com ESMTP
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-smtp.gmail.com at your service, [2.109.71.78]
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-SIZE 35882577
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-8BITMIME
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-AUTH LOGIN PLAIN ...
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-ENHANCEDSTATUSCODES
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-PIPELINING
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250-CHUNKING
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine(); // 250 SMTPUTF8
            System.out.println("Server:" + serverMsg);*/


            for (int i = 0; i < 6; i++) {
                s = clientResponse[i];
                System.out.println("Client:" + s);
                dataOutputStream.writeBytes(s);
                //Thread.sleep(1000);
                serverMsg = reader.readLine();
                System.out.println("Server:" + serverMsg);

            }


            s = "Subject: Email test\r\n";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            //Thread.sleep(1000);

            s = "Helloooo, It's group 11. It is a mail from Gmail:)\r\n";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            //Thread.sleep(1000);

            s = ".\r\n";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            //Thread.sleep(1000);

            s = "QUIT\r\n";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            //Thread.sleep(1000);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);



            } catch (Exception e){e.printStackTrace();}
    }
    }

