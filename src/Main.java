import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        crudeClient client = new crudeClient();
        //client.attachImage("src/clippy.jpg");
        client.setClientSequence("info@comit.dev", "s224311@dtu.dk", "ÅhÅh");
        client.sendMail();
    }
}