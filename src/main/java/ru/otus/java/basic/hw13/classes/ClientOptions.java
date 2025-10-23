package ru.otus.java.basic.hw13.classes;

import java.io.*;

public class ClientOptions implements AutoCloseable {
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientOptions(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new DataInputStream(inputStream);
        this.outputStream = new DataOutputStream(outputStream);
    }

    public void send(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
        try {
            String result = inputStream.readUTF();
            if (("Bye").equalsIgnoreCase(result)) {
                System.out.println("\nВы вышли из приложения. До свидания! :)");
                return;
            }
            System.out.println(result);
        } catch (EOFException e) {
            System.out.println("\nОшибка! Сервер не отвечает... ;(");
        }
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
        outputStream.close();
    }
}
