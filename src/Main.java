import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        crudeClient client = new crudeClient();
        client.setClientSequence("bayouna96@gmail.com" , "nr.salmani@gmail.com" ,"Hiiiiiii");
        client.sendMail();
    }
}