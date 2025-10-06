package ru.otus.java.basic.hw9.employee;

import ru.otus.java.basic.hw9.employee.classes.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> listOfEmployee = List.of(
                new Employee("Сергей", 30),
                new Employee("Алена", 18),
                new Employee("Антон", 35),
                new Employee("Екатерина", 12)
        );

        System.out.println("Список сотрудников:");
        List<String> listOfName = getListOfName(listOfEmployee);
        System.out.println(listOfName);
        System.out.println();

        int minAge = 18;
        System.out.println("Список сотрудников старше " + minAge + ":");
        List<String> listOfNameByAge = getListOfNameByAge(listOfEmployee, minAge);
        System.out.println(listOfNameByAge);
        System.out.println();

        int minimumAverageAge = 22;
        System.out.println("Сравнение среднего возраста сотрудников с " + minimumAverageAge + ":");
        boolean isAverageAgeOlder = checkMidlife(listOfEmployee, minimumAverageAge);
        System.out.println("Средний возраст сотрудников " + (isAverageAgeOlder ? "больше " : "меньше ") + minimumAverageAge + ".");
        System.out.println();

        Employee youngestEmployee = findYoungestEmployee(listOfEmployee);
        System.out.println("Самый молодой сотрудник:");
        System.out.println(youngestEmployee);
        System.out.println();

        System.out.println("Программа завершена!");
    }

    private static List<String> getListOfName(List<Employee> list) {
        List<String> listOfName = new ArrayList<>(list.size());
        for (Employee employee : list) {
            listOfName.add(employee.getName());
        }
        return listOfName;
    }

    private static List<String> getListOfNameByAge(List<Employee> list, int minAge) {
        List<String> listOfNameByAge = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.isOlder(minAge)) {
                listOfNameByAge.add(employee.getName());
            }
        }
        return listOfNameByAge;
    }

    public static boolean checkMidlife(List<Employee> list, int minimumAverageAge) {
        int sumEmployeeAge = 0;
        for (Employee employee : list) {
            sumEmployeeAge += employee.getAge();
        }
        return sumEmployeeAge / list.size() > minimumAverageAge;
    }

    public static Employee findYoungestEmployee(List<Employee> list) {
        Employee youngestEmployee = list.get(0);
        for (Employee employee : list) {
            if (employee.getAge() < youngestEmployee.getAge()) {
                youngestEmployee = employee;
            }
        }
        return youngestEmployee;
    }
}
