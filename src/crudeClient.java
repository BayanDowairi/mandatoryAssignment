import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.*;
import java.io.*;
import java.util.Base64;

public class crudeClient {
    // Credentials
    public static String user = "bayouna96@gmail.com";
    public static String pass = "qzjiclxjatriavwz";
    private static DataOutputStream dataOutputStream;
    public static BufferedReader br = null;
    private String message;
    String[] clientResponse = new String[10];
    //SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    String username = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
    String password = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
    int delay = 1000;


    //public static void main(String[] args) throws Exception {
        //int delay = 1000;

        //String username = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        //String password = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));

        //SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("smtp.gmail.com", 465);

       // br = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

        //dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());


        /*send("EHLO smtp.gmail.com\r\n",9);
        send("AUTH LOGIN\r\n",1);
        send(username+"\r\n",1);
        send(password+"\r\n",1);
        send("MAIL FROM:<bayouna96@gmail.com>\r\n",1);
        send("RCPT TO:<nr.salmani@gmail.com>\r\n",1);
        send("DATA\r\n",1);
        send("Subject: Email test\r\n",0);
        send("Email Body\r\n",0);
        send(".\r\n",0);
        send("QUIT\r\n",1);*/
    //}

    /*private static void send(String s, int no_of_response_line) throws Exception
    {
        dataOutputStream.writeBytes(s);
        System.out.println("CLIENT: "+s);
        Thread.sleep(1000);

        // Just reading the number of lines the server will respond.
        for (int i = 0; i < no_of_response_line; i++) {
            System.out.println("SERVER : " +br.readLine());
        }
    }*/
    public void setClientSequence(String sender, String recieiver, String message) throws IOException {
        recieiver = Base64.getEncoder().encodeToString(user.getBytes(StandardCharsets.UTF_8));
        this.message = message;
        this.clientResponse[0] = "AUTH LOGIN\r\n";
        this.clientResponse[1] = "YmF5b3VuYTk2QGdtYWlsLmNvbQ==\r\n";
        this.clientResponse[2] = "cXpqaWNseGphdHJpYXZ3eg==\r\n";
        this.clientResponse[3] = "MAIL FROM: " + "<" + sender + ">";
        this.clientResponse[4] = "RCPT TO:" + "<" + recieiver + ">";
        this.clientResponse[5] = "DATA\r\n";
        this.clientResponse[6] = "Subject: Email test\r\n";
        this.clientResponse[7] = "Email Body: " + message;
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
            Thread.sleep(1000);

            serverMsg = reader.readLine(); // read from server
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);


            /*for (int i = 0; i < 10; i++) {
                serverMsg = reader.readLine();
                System.out.println("Server:" + serverMsg);
            }*/
            /*s = "AUTH LOGIN\r\n";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            Thread.sleep(1000);
            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);*/



            for (int i = 0; i < clientResponse.length; i++) {
                s = clientResponse[i];
                System.out.println("Client:" + s);
                dataOutputStream.writeBytes(s);
                Thread.sleep(1000);
                serverMsg = reader.readLine();
                System.out.println("Server:" + serverMsg);
                //Thread.sleep(1000);
                dataOutputStream.flush();
            }
            /*serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);

            /*s = "RCPT TO:<nr.salmani@gmail.com>";
            System.out.println("Client: " + s);
            dataOutputStream.writeBytes(s);
            Thread.sleep(1000);
            serverMsg = reader.readLine();
            System.out.println("Server:" + serverMsg);*/


            } catch (Exception e){e.printStackTrace();}
    }
    }

