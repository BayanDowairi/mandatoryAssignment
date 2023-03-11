import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        crudeClient client = new crudeClient();
        client.setClientSequence();
        client.sendMail();
    }
}