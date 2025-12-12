package ru.otus.java.basic.hw24.application;

import com.google.gson.Gson;
import ru.otus.java.basic.hw24.HttpRequest;
import ru.otus.java.basic.hw24.exceptions_handling.BadRequestException;
import ru.otus.java.basic.hw24.processors.RequestProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        String jsonStr;
        if (request.containsParameter("id")) {
            long id;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Не верный формат ID");
            }
            Item targetItem = ItemsStorage.getItemById(id);
            if (targetItem == null) throw new BadRequestException("Элемент с id=" + id + " не найден");
            jsonStr = gson.toJson(targetItem);
        } else {
            jsonStr = gson.toJson(ItemsStorage.getItems());
        }
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                jsonStr;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
