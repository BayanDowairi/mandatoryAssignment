import java.io.*;
import java.net.*;
import java.util.*;

public class crudeClient {

    public static void main(String argv[])
    {
        Scanner in = new Scanner(System.in);

        try{
            Socket socketClient= new Socket("datacomm.bhsi.xyz",2552);
            System.out.println("Client: "+"Connection Established");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            BufferedWriter writer=
                    new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            String s,serverMsg;

            String[] emailFromTA = {
                    "HELO datacomm.bhsi.xyz",
                    "mail from: <info@comit.dev>",
                    "rcpt to: <s224311@dtu.dk>",
                    "DATA",
                    "friends 5ever\r\n.",
                    "QUIT"
            };

            String[] replyToTA = {
                    "HELO datacomm.bhsi.xyz",
                    "mail from: <s224311@dtu.dk>",
                    "rcpt to: <info@comit.dev>",
                    "DATA",
                    "taylor swift for president\r\n.",
                    "QUIT"
            };

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

            for(int i = 0 ; i < emailFromTA.length ; i++) {
                s = emailFromTA[i];
                System.out.println("Client: " + s);
                writer.write(s+"\r\n");
                writer.flush();

                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }

        }catch(Exception e){e.printStackTrace();}
    }

}