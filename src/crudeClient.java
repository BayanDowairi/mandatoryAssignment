import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class crudeClient {
    String[] clientResponse = new String[5];
    String imagePath;
    String fileName;
    String message;
    boolean hasAttachment = false;

    public void attachImage(String imagePath, String fileName) throws IOException {
        hasAttachment = true;
        this.imagePath = imagePath;
        this.fileName = fileName;
        getImageString();
    }
    private String getImageString() throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    public void setClientSequence(String sender, String receiver, String message) throws IOException {
        this.message = message;
        this.clientResponse[0] = "mail from: " + "<" + sender + ">";
        this.clientResponse[1] = "rcpt to: " + "<" + receiver + ">";
        this.clientResponse[2] = "DATA";
        if (hasAttachment){
            this.clientResponse[3] = "TO: " + receiver + "\r\n" + "FROM: " + sender + "\r\n"
                    + "SUBJECT: Mandatory Assignment" + "\r\n"
                    + "MIME-Version: 1.0" + "\r\n" +
                    "Content-Type:multipart/mixed;boundary=\"[mystrongassboundary]\"" + "\r\n" +
                    "--[mystrongassboundary]" + "\r\n" + "\r\n" + message + "\r\n" +
                    "--[mystrongassboundary]" + "\r\n" +
                    "Content-Type:image/jpeg;" + "\r\n" +
                    "Content-Disposition:attachment;filename=\"" + fileName + "\"" + "\r\n" +
                    "Content-Transfer-Encoding:base64" + "\r\n" + "\r\n" +
                    getImageString() +  "--[mystrongassboundary]--"+"\r\n"+".";

        } else {
        this.clientResponse[3] = "FROM: " + sender + "\r\n" + "TO: " + receiver + "\r\n"
                + "SUBJECT: Mandatory Assignment" + "\r\n"
                + message + "\r\n.";
        }
        this.clientResponse[4] = "QUIT";
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
            System.out.println("Server: " + serverMsg); // 220 Welcome to...

            //-------------------------------------------------------------//
            //                      Greeting the server                    //
            //-------------------------------------------------------------//
            s = "EHLO datacomm.bhsi.xyz";
            System.out.println("Client: " + s);
            writer.write(s+"\r\n");
            writer.flush();

            //-------------------------------------------------------------//
            //         Printing out supported service extensions           //
            //-------------------------------------------------------------//
            for (int i = 0 ; i < 11 ; i++) {
                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }

            //------------------------------------------------------------------//
            //  Perform all necessary client/server interactions to send email  //
            //------------------------------------------------------------------//
            for(int i = 0 ; i < clientResponse.length ; i++) {
                s = clientResponse[i];
                System.out.println("Client: " + s);
                writer.write(s+"\r\n");
                writer.flush();

                serverMsg = reader.readLine();
                System.out.println("Server: " + serverMsg);
            }

        }catch(Exception e){e.printStackTrace();}
    }
}