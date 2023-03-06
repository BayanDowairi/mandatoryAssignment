public class Main {
    public static void main(String[] args) {
        crudeClient client = new crudeClient();
        client.setMsgSequence("info@comit.dev", "s224311@dtu.dk", "hallihallo");
        client.sendMail();
    }
}