package ru.otus.java.basic.hw24.application;

import com.google.gson.Gson;
import ru.otus.java.basic.hw24.HttpRequest;
import ru.otus.java.basic.hw24.exceptions_handling.BadRequestException;
import ru.otus.java.basic.hw24.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteItemProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!request.containsParameter("id")) throw new BadRequestException("Параметр ID не был передан");
        long id;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Не верный формат ID");
        }
        Gson gson = new Gson();
        Item targetItem = ItemsStorage.getItemById(id);
        if (targetItem == null) throw new BadRequestException("Элемент с id=" + id + " не найден");
        String response = "" +
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "\r\n" +
                    gson.toJson(targetItem);
        ItemsStorage.deleteItem(id);
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
