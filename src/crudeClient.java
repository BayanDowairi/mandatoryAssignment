import java.io.*;
import java.net.*;
import java.util.*;


public class crudeClient {
    String[] msgSequence = new String[6];

    public crudeClient() {
        /*this.msgSequence[0] = "HELO datacomm.bhsi.xyz";
        this.msgSequence[1] = "mail from: " + "<" + sender + ">";
        this.msgSequence[2] = "rcpt to: " + "<" + receiver + ">";
        this.msgSequence[3] = "DATA";
        this.msgSequence[4] = message+"\r\n.";
        this.msgSequence[5] = "QUIT";

        if(msgSequence.equals("emailFromTA")) {
            this.msgSequence[1] = "mail from: <info@comit.dev>";
            this.msgSequence[2] = "rcpt to: <s224311@dtu.dk>";
            this.msgSequence[4] = "Bitch, you look goodt, with a T at the end\r\n.";

        } else {
            this.msgSequence[1] = "mail from: <s224311@dtu.dk>";
            this.msgSequence[2] = "rcpt to: <info@comit.dev>";
            this.msgSequence[4] = "Ima hype her every time, that my mothеrfuckin friend\r\n.";

        }

 */

    }

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

            for(int i = 0 ; i < msgSequence.length ; i++) {
                s = msgSequence[i];
                System.out.println("Client: " + s);
                writer.write(s+"\r\n");
                writer.flush();

                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }

        }catch(Exception e){e.printStackTrace();}
    }

}