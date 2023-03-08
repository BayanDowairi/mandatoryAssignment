import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        crudeClient client = new crudeClient();
        client.setMsgSequence("info@comit.dev", "s224311@dtu.dk", "test");
        client.attachImage("src/clippy.jpg");
        //client.sendMail();
        client.sendImage();
    }
}