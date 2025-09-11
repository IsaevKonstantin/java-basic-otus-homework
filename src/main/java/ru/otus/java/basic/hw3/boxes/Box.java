package ru.otus.java.basic.hw3.boxes;

public class Box {
    private final int length;
    private final int width;
    private final int height;
    private String color;
    private String item;
    private boolean isOpen;

    public String getColor() {
        return  color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Box(int length, int width, int height, String color) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.item = null;
        this.isOpen = false;
    }

    public Box(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = "Белая";
        this.item = null;
        this.isOpen = false;
    }

    public void printBoxInfo() {
        System.out.println("ИНФОРМАЦИЯ О КОРОБКЕ");
        System.out.println("Размеры коробки: " + length + "x" + width + "x" + height + ".");
        System.out.println("Цвет коробки: " + color + ".");
        System.out.println(item != null ? "В коробке есть предмет: " + item + "." : "В коробке пусто.");
        System.out.println(isOpen ? "Коробка открыта." : "Коробка закрыта.");
    }
}
