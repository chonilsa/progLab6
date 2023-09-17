package server;

import commands.CommandRequest;
import commands.CommandResponse;
import managers.CollectionManager;
import managers.Executor;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Server {
    private final CollectionManager collectionManager;
    private final Executor executor;
    public Selector selector;
    private int port;
    private Socket socket;
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel channel;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private InputStream stream;

    public Server(CollectionManager collectionManager) {
        this.port = 5555;
        boolean isRunning = false;
        while (!isRunning) {
            try {
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(port));
                serverSocketChannel.configureBlocking(false);

                this.selector = Selector.open();
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                isRunning = true;
                System.out.println("Server is running on port " + port);
            } catch (IOException e) {
                port++;
            }
        }

        this.stream = System.in;
        this.collectionManager = collectionManager;
        this.executor = new Executor(collectionManager);
    }


    public void run() {
        try {
            while (true) {
                if (selector.select(5000) == 0) {
                    continue;
                }
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        SocketChannel channel = serverSocketChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println("New connection");
                    } else if (key.isReadable()) {
                        handleRead(key, selector);
                    } else if (key.isWritable()) {
                        //handleWrite(key);
                        key.channel().register(selector, SelectionKey.OP_READ);
                    }
                    selector.selectedKeys().remove(key);
                }
//                if (System.in.available() > 0) {
//                    Scanner scanner = new Scanner(System.in);
//                    if (scanner.nextLine().equals("save")){
//
//                    }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void handleWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        byte[] data = (byte[]) (key.attachment());
        ByteBuffer buffer = ByteBuffer.wrap(data);
        channel.write(buffer);
//        channel.close();
    }


    public void handleRead(SelectionKey key, Selector selector) throws Exception {

        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(65536);
        try {
            int readBytes = channel.read(buffer);
            if (readBytes == -1) {
                channel.close();
                return;
            }
            byte[] data = buffer.array();
            buffer.clear();
            Object receivedObject = deserialize(data);
            // Обработка
            CommandResponse response = new CommandResponse("Command not found", null);

            if (receivedObject instanceof CommandRequest) {
                response = runCommand((CommandRequest) receivedObject);
            }
            sendResponse(channel, response);

            channel.register(selector, SelectionKey.OP_WRITE, serialize(response));
        } catch (SocketException e) {
            System.out.println("Client disconnected");
            channel.close();
        }
    }


    private void sendResponse(SocketChannel socketChannel, Object response) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(65536);

        // Сериализация объекта в массив байтов
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();

        byte[] bytes = byteArrayOutputStream.toByteArray();
        buffer.put(bytes);

        buffer.flip(); // Переключение на режим записи
        socketChannel.write(buffer);

        objectOutputStream.close();
        byteArrayOutputStream.close();
    }


    public CommandResponse runCommand(CommandRequest userCommand) {
        return executor.executeCommand(userCommand);
    }

    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
