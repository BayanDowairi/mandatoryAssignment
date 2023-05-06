import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        smtpClient client = new smtpClient();
        client.setClientSequence();
        client.sendMail();
    }
}