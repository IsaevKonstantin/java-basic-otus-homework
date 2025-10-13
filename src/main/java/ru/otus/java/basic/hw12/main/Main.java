package ru.otus.java.basic.hw12.main;

import ru.otus.java.basic.hw12.dataException.DataException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File[] files = getMenuFiles();

            printMenuFiles(files);

            File choiceFile = inputChoiceFile(files);

            String stringData = readingChoiceFile(choiceFile);

            printFile(choiceFile, stringData);

            String inputData = inputValue();

            boolean isWriting = writingIntoChoiceFile(choiceFile, inputData);

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

    private static File[] getMenuFiles() throws DataException {
        String rootPath = System.getProperty("user.dir");
        File rootDir = new File(rootPath);
        File[] textFiles = rootDir.listFiles(((dir, name) -> name.toLowerCase().endsWith(".txt")));
        if (textFiles == null || textFiles.length == 0) {
            throw new DataException("Файлы для чтения и записи не найдены...");
        }
        return textFiles;
    }

    private static void printMenuFiles(File[] files) throws DataException {
        System.out.println("\nВыберете файл для работы:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }
    }

    private static String readingChoiceFile(File choiceFile) throws DataException {
        if (!choiceFile.exists()) throw new DataException("Файл \"" + choiceFile.getName() + "\" - не существует!");

        StringBuilder data = new StringBuilder();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(choiceFile), StandardCharsets.UTF_8)) {
            int n = in.read();
            while (n != -1) {
                data.append((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            throw new DataException("Ошибка при чтении файла: " + choiceFile.getName() + "!");
        }
        return data.toString();
    }

    private static void printFile(File choiceFile, String data) {
        System.out.println("\n" + "Содержимое файла " + choiceFile.getName() + ":");
        if (data.isEmpty()) {
            System.out.println("Данные не найдены...");
            return;
        }
        System.out.println(data);
    }

    private static File inputChoiceFile(File[] files) throws DataException {
        Scanner input = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Введите номер:");
            try {
                choice = input.nextInt();
                if (choice >= 1 && choice <= files.length) {
                    break;
                } else {
                    System.out.println("Неверный номер, попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                throw new DataException("Ошибка при выборе файла!");
            }
        }
        return files[choice - 1];
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

    private static boolean writingIntoChoiceFile(File file, String data) throws DataException {
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
