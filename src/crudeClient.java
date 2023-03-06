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

            //while((serverMsg = reader.readLine()) != null){
            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);

           /* serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);*/
            //s = in.nextLine();
            s = "HELO datacomm.bhsi.xyz";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            //s = in.nextLine();
            s = "mail from: <info@comit.dev>";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();


            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            //s = in.nextLine();
            s = "rcpt to: <s224311@dtu.dk>";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            //s = in.nextLine();
            s = "DATA";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            //s = in.nextLine();
            s = "det skal nok gaa";
            System.out.println("Client: " + s);
            //String p = in.nextLine();
            writer.write(s+"\r\n");
            writer.write("."+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            //s = in.nextLine();
            s = "QUIT";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Server: " + serverMsg);
            //}

        }catch(Exception e){e.printStackTrace();}
    }
}