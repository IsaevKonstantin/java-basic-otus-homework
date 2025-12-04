package ru.otus.java.basic.hw20.main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File myFile;
        try {
            myFile = getFileByStr(scanner);
        } catch (IllegalStateException e) {
            System.out.println("\n" + e.getMessage());
            return;
        }

        System.out.println("\nВведите искомую строку: ");
        String target = scanner.nextLine();

        if (target.isEmpty()) {
            System.out.println("\nИскомая строка не может быть пустой");
            return;
        }

        int total = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(myFile), StandardCharsets.UTF_8))) {
            String prevTail = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String combined = prevTail + line;
                total += countOccurrences(combined, target);
                if (combined.length() >= target.length() - 1) {
                    prevTail = combined.substring(combined.length() - (target.length() - 1));
                } else {
                    prevTail = combined;
                }
            }
            System.out.println("\nКоличество вхождений: " + total);
        } catch (IOException e) {
            System.out.println("\nОшибка чтения файла: " + e.getMessage());
        }
    }

    public static File getFileByStr(Scanner scanner) throws IllegalStateException {
        File file;
        while (true) {
            System.out.println("\nВведите имя файла: ");
            String fileName = scanner.nextLine();
            file = new File(fileName);

            if (!file.exists() || !file.isFile()) {
                System.out.println("\nФайл \"" + fileName + "\" не найден ;(");
                File[] files = getFiles();
                if (files == null || files.length == 0) {
                    throw new IllegalStateException("Список доступных файлов - пуст ;(");
                }

                System.out.println("Список доступных файлов:");
                printFileNames(files);
                continue;
            }
            break;
        }
        return file;
    }

    private static File[] getFiles() {
        String rootPath = System.getProperty("user.dir");
        File rootDir = new File(rootPath);
        return rootDir.listFiles(((dir, name) -> name.toLowerCase().endsWith(".txt")));
    }

    private static void printFileNames(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }
    }

    private static int countOccurrences(String text, String target) {
        int count = 0;
        for (int i = 0; i <= text.length() - target.length(); i++) {
            if (text.startsWith(target, i)) {
                count ++;
            }
        }
        return count;
    }
}
