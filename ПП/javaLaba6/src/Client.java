public class Client {
    private ITarget client;

    public Client(ITarget client) {
        this.client = client;
    }
    void Show(){
        client.ClientChar('g');
        client.ClientInt(78);
        client.ClientDouble(75.8d);
    }

    public static void main(String[] args) {
        Adapter adapter=new Adapter();
        Client client=new Client(adapter);
        client.Show();
    }
}
