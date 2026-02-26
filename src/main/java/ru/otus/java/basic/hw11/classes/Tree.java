package ru.otus.java.basic.hw11.classes;

public class Tree {
    private final Employee value;
    private Tree left;
    private Tree right;

    public Tree(Employee value) {
        this.value = value;
    }

    public Employee getValue() {
        return value;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }
}
