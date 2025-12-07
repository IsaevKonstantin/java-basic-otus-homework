package ru.otus.java.basic.hw22.clasess;

public class Arr {
    public int[] elementsAfterLastOne(int[] arr) throws RuntimeException {
        if (arr.length == 0) throw new RuntimeException("Массив пуст");
        int lastOneIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                lastOneIndex = i;
            }
        }
        if (lastOneIndex == -1) {
            throw new RuntimeException("В массиве нет единиц");
        }
        int newLength = arr.length - lastOneIndex - 1;
        int[] result = new int[newLength];
        System.arraycopy(arr, lastOneIndex + 1, result, 0, newLength);
        return result;
    }

    public boolean elementsFilter(int[] arr) {
        if (arr.length == 0) return false;
        boolean isInsertOne = false;
        boolean isInsertTwo = false;
        for (int n : arr) {
            if (n == 1) {
                isInsertOne = true;
            } else if (n == 2) {
                isInsertTwo = true;
            } else {
                return false;
            }
        }
        return isInsertOne && isInsertTwo;
    }
}
