package ru.otus.java.basic.hw11.main;

import ru.otus.java.basic.hw11.classes.Employee;
import ru.otus.java.basic.hw11.classes.EmployeeSearchTree;
import ru.otus.java.basic.hw11.classes.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> humans = List.of(
                new Employee("Андрей", "Исаев", 20, 555),
                new Employee("Сергей", "Имайкин", 21, 111),
                new Employee("Валерий", "Бондарчук", 30, 222),
                new Employee("Ольга", "Имайкина", 34, 333),
                new Employee("Мария", "Исаева", 18, 444),
                new Employee("Константин", "Исаев", 42, 777),
                new Employee("Дарина", "Исаева", 18, 999)
        );

        System.out.println();
        System.out.println("Изначальный список:");
        print(humans, true);

        System.out.println();
        System.out.println("Отсортированный список:");
        List<Employee> sortedList = new ArrayList<>(humans.size());
        sortedList.addAll(humans);
        sortedList.sort(Comparator.comparing(Employee::getId));
        print(sortedList, false);

        EmployeeSearchTree searchTree = new EmployeeSearchTree();
        searchTree.generateTree(sortedList);

        System.out.println();
        System.out.println("Поиск сотрудника:");
        Employee findEmployee1 = new Employee("Константин", "Исаев", 42, 777);
        Employee findEmployee2 = new Employee("Константин", "Исаев", 42, 111);
        Employee found1 = searchTree.find(findEmployee1);
        Employee found2 = searchTree.find(findEmployee2);
        System.out.println(found1);
        System.out.println(found2);

        System.out.println();
        System.out.println("Отсортированный список из дерева:");
        List<Employee> newList = searchTree.getSortedList();
        print(newList, false);

        System.out.println();
        System.out.println("Отрисовка дерева:");
        Tree root = searchTree.getRoot();
        printTree(root, 0);
    }

    private static void print(List<Employee> list, boolean isTalk) {
        for (Employee employee : list) {
            System.out.println(isTalk ? employee.talk() + " ID: " + employee.getId() + "." : employee);
        }
    }

    private static void printTree(Tree node, int level) {
        if (node == null) return;
        printTree(node.getLeft(), level + 1);
        System.out.println(" ".repeat(level * 4) + node.getValue() + ".");
        printTree(node.getRight(), level + 1);
    }
}
