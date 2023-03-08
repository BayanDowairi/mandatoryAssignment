public class Main {
    public static void main(String[] args) {
        crudeClient client = new crudeClient();
        client.setMsgSequence("biden@gov.com", "amira.mm.omar@gmail.com", "Hello, do you mind if I take a BITE outta you ;P!");
        client.sendMail();
    }
}