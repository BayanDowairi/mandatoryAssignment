import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class crudeClient {
    String[] msgSequence = new String[6];
    String imagePath;

    String message;
    boolean hasAttachment = false;
    public void setMsgSequence(String sender, String receiver, String message) {
        this.message = message;
        this.msgSequence[1] = "mail from: " + "<" + sender + ">";
        this.msgSequence[2] = "rcpt to: " + "<" + receiver + ">";
        this.msgSequence[3] = "DATA";
        this.msgSequence[4] = message + "\r\n.";
        this.msgSequence[5] = "QUIT";
    }

    public void attachImage(String imagePath) throws IOException {
        hasAttachment = true;
        this.imagePath = imagePath;
        getImageString();
        
    }

    private String getImageString() throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    public void sendImage() {
        try{
            Socket socketClient= new Socket("datacomm.bhsi.xyz",2552);

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

           for (int i = 0 ; i < 11 ; i++) {
                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }

            s =  "mail from: <info@comit.dev>";
            System.out.println("Client: " + s);
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

           s = "TO: s224311@dtu.dk" + "\r\n" + "FROM: info@comit.dev" + "\r\n" + "MIME-Version: 1.0" + "\r\n" +
                    "Content-Type:multipart/mixed;boundary=\"[mystrongassboundary]\"" + "\r\n" +
                    "--[mystrongassboundary]" + "\r\n" + "\r\n" + message + "\r\n" +
                   "--[mystrongassboundary]" + "\r\n";

            String p =
                            "Content-Type:image/jpeg;" + "\r\n" +
                            "Content-Disposition:attachment;filename=\"clippy.jpeg\"" + "\r\n" +
                            "Content-Transfer-Encoding:base64" + "\r\n" + "\r\n" + getImageString() +  "--[mystrongassboundary]--"+"\r\n"+".";

            writer.write(s+p+"\r\n");
            System.out.println("Client: " + s+p);
            /*while(!s.equals(".")) {
                s = in.nextLine();
                writer.write(s+"\r\n");
            }*/

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


    public void sendMail() {

        try{
            Socket socketClient= new Socket("datacomm.bhsi.xyz",2552);

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


}