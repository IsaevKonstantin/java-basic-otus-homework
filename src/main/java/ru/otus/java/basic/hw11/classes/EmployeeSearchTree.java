package ru.otus.java.basic.hw11.classes;

import ru.otus.java.basic.hw11.interfaces.SearchTree;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchTree implements SearchTree<Employee> {
    private Tree root;

    public void generateTree(List<Employee> list) {
        if (list.isEmpty() || list == null) {
            root = null;
        } else {
            root = buildSubTree(new ArrayList<>(list));
        }
    }

    public Tree buildSubTree(List<Employee> list) {
        if (list.isEmpty()) return null;
        int middleIndex = list.size() / 2;
        Employee middleEmployee = list.get(middleIndex);
        Tree node = new Tree(middleEmployee);
        node.setLeft(buildSubTree(new ArrayList<>(list.subList(0, middleIndex))));
        node.setRight(buildSubTree(new ArrayList<>(list.subList(middleIndex + 1, list.size()))));
        return node;
    }

    @Override
    public Employee find(Employee element) {
        if (element == null || root == null) return null;
        return findRecursive(root, element);
    }

    private Employee findRecursive(Tree current, Employee employee) {
        if (current == null) return null;
        Employee currentValue = current.getValue();
        if (employee.equals(currentValue)) return currentValue;
        if (employee.getId() < currentValue.getId()) return findRecursive(current.getLeft(), employee);
        return findRecursive(current.getRight(), employee);
    }

    @Override
    public List<Employee> getSortedList() {
        List<Employee> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Tree node, List<Employee> result) {
        if (node == null) return;
        inOrderTraversal(node.getLeft(), result);
        result.add(node.getValue());
        inOrderTraversal(node.getRight(), result);
    }

    public Tree getRoot() {
        return root;
    }
}
