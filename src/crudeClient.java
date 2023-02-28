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
            s = in.nextLine();
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Client: " + serverMsg);
            s = in.nextLine();
            writer.write(s+"\r\n");
            writer.flush();


            serverMsg = reader.readLine();
            System.out.println("Client: " + serverMsg);
            s = in.nextLine();

            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Client: " + serverMsg);
            s = in.nextLine();
            writer.write(s+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Client: " + serverMsg);
            s = in.nextLine();
            String p = in.nextLine();
            writer.write(s+"\r\n");
            writer.write(p+"\r\n");
            writer.flush();

            serverMsg = reader.readLine();
            System.out.println("Client: " + serverMsg);
            s = in.nextLine();
            writer.write(s+"\r\n");
            writer.flush();

            //}

        }catch(Exception e){e.printStackTrace();}
    }
}