package ru.otus.java.basic.hw12.main;

import ru.otus.java.basic.hw12.dataException.DataException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "textList.txt";
        File file = new File(filePath);
        try {
            List<String> fileNames = readingFileByPath(file);

            if (fileNames.isEmpty()) {
                throw new DataException("В файле \"" + file.getName() + "\" - нет данных!");
            }

            printFile("Выберете файл для работы:", fileNames, true);

            File choiceFile = new File(inputChoiceFile(fileNames) + ".txt");

            List<String> fileData = readingFileByPath(choiceFile);

            printFile("Содержимое файла " + choiceFile.getName() + ":", fileData, false);

            String inputData = inputValue();

            boolean isWriting = writingDataByFileName(choiceFile, inputData);

            if (isWriting) {
                System.out.println("В файл \"" + choiceFile.getName() + "\" успешно записанны данные: " + inputData + ".");
            } else {
                System.out.println("Не удалось вннести данные в файл \"" + choiceFile.getName() + "\" данные: " + inputData + ".");
            }

        } catch (DataException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("\nКонец программы!");
    }

    private static List<String> readingFileByPath(File file) throws DataException {
        if (!file.exists()) throw new DataException("Файл \"" + file.getName() + "\" - не существует!");
        List<String> fileNames = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    fileNames.add(line);
                }
            }
        } catch (IOException e) {
            throw new DataException("Ошибка при чтении файла: " + file.getName() + "!");
        }
        return fileNames;
    }

    private static void printFile(String title, List<String> data, boolean isMenu) {
        System.out.println("\n" + title);
        if (data.isEmpty()) {
            System.out.println("Данные не найденны...");
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            System.out.println(isMenu ? (i + 1) + ". " + data.get(i) : data.get(i));
        }
    }

    private static String inputChoiceFile(List<String> fileNames) throws DataException {
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Введите номер:");
            try {
                choice = input.nextInt();
                if (choice >= 1 && choice <= fileNames.size()) {
                    break;
                } else {
                    System.out.println("Неверный номер, попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                throw new DataException("Ошибка при выборе файла!");
            }
        }
        return fileNames.get(choice - 1);
    }

    private static String inputValue() throws DataException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите данные:");
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            throw new DataException("Ошибка! Неверные данные для записи в файл!");
        }
        return line;
    }

    private static boolean writingDataByFileName(File file, String data) throws DataException {
        if (!file.exists()) throw new DataException("Файл \"" + file.getName() + "\" - не существует!");
        try(FileOutputStream out = new FileOutputStream(file)) {
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            for (byte byteData : bytes) {
                out.write(byteData);
            }
        } catch (IOException e) {
            throw new DataException("Не удалось внести данные в файл \"" + file.getName() + "\" данные: " + data + ".");
        }
        return true;
    }
}
