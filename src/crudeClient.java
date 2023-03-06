import java.io.*;
import java.net.*;
import java.util.*;


public class crudeClient {
    String[] msgSequence = new String[6];

    public void setMsgSequence(String sender, String receiver, String message) {
        this.msgSequence[0] = "HELO datacomm.bhsi.xyz";
        this.msgSequence[1] = "mail from: " + "<" + sender + ">";
        this.msgSequence[2] = "rcpt to: " + "<" + receiver + ">";
        this.msgSequence[3] = "DATA";
        this.msgSequence[4] = message + "\r\n.";
        this.msgSequence[5] = "QUIT";
    }

    public void sendMail() {

        try{
            Socket socketClient= new Socket("datacomm.bhsi.xyz",2552);
            System.out.println("Client: "+"Connection Established");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            BufferedWriter writer=
                    new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            String s,serverMsg;



            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            for(int i = 0 ; i < 4 ; i++) {
                s = msgSequence[i];
                System.out.println("Client: " + s);
                writer.write(s+"\r\n");
                writer.flush();

                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }

        }catch(Exception e){e.printStackTrace();}
    }

    public void sendImage() {
        try{
            Socket socketClient= new Socket("datacomm.bhsi.xyz",2552);
            System.out.println("Client: "+"Connection Established");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            BufferedWriter writer=
                    new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            String s,serverMsg;

            Scanner in = new Scanner(System.in);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg); // 220 Welcome to...

            s = "EHLO datacomm.bhsi.xyz";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            serverMsg = reader.readLine();
            System.out.println("Server last one: " + serverMsg);

           /* while((serverMsg = reader.readLine()) != null) {

                System.out.println("Server: " + serverMsg); // 250-comit.dev
            }*/


            s =  "mail from: <info@comit.dev>";
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            s = "rcpt to: <s224311@dtu.dk>";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            s = "DATA";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            while(!s.equals(".")) {
                s = in.nextLine();
                writer.write(s+"\r\n");
            }

            //String p = in.nextLine();
            //writer.write(s+"\r\n"+p+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            s = "QUIT";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();


            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

/*

            for(int i = 0 ; i < 4 ; i++) {
                s = msgSequence[i];
                System.out.println("Client: " + s);
                writer.write(s+"\r\n");
                writer.flush();

                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }
*/
        }catch(Exception e){e.printStackTrace();}

    }

}