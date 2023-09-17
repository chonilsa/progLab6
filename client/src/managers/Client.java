package managers;

import commands.CommandDescription;
import commands.CommandRequest;
import commands.CommandResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client implements ClientInterface {
    private String host;
    private int port;
    private Socket socket;
    private SocketChannel client;
    private Serializer serializer;
    private Deserializer deserializer;
    private ByteBuffer buffer;
    private OutputStream outputStream;
    private InputStream inputStream;

    private CommandDescription[] commandDescriptions;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        serializer = new Serializer();
        deserializer = new Deserializer();

        this.buffer = ByteBuffer.allocate(100000);
        try {
            while (true) {
                try {
                    client = SocketChannel.open();
                    client.connect(new InetSocketAddress("localhost", port));
                    break;
                } catch (IOException e) {
                    System.out.println("Reconnecting to Server");
                    Thread.sleep(1000);
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }


    @Override
    public CommandResponse run(CommandRequest request) {
        try {
            ByteBuffer response = ByteBuffer.allocate(1000000);
            response.clear();
            buffer = serializer.serialize(request);
            client.write(buffer);
            client.read(response);
            //byte[] bytes = response.array();
            CommandResponse response1 = (CommandResponse) deserializer.deserialize(response);
            return response1;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Server is not available");
        }
        return new CommandResponse("Something went wrong", null);
    }

}
