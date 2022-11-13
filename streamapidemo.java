package com.sagnik.streamapi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamapidemo {
    static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(
                new Employee("Rick", "william", 100.0, List.of("Project 1", "Project 2"))
        );
        employees.add(
                new Employee("Gerd", "william", 101.0, List.of("Project 1", "Project 4"))
        );
        employees.add(
                new Employee("Bern", "william", 900.0, List.of("Project 3", "Project 2"))
        );
    }

    public static void main(String args[]) {

        employees.stream().forEach(employee -> System.out.println(employee));
        //changing collection to stream
        //map
        //map in stream is used to take one object and will return another object to you
        //collect
        Set<Employee> increasedSal =
                employees.stream().map(employee -> new Employee(employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 2.10,
                                employee.getProjects()
                        ))
                        .collect(Collectors.toSet());
        System.out.print(increasedSal);
        //filter()

        Set<Employee> sal =
                employees.stream().filter(employee -> employee.getSalary() > 500)
                        .map(employee -> new Employee(employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 2.10,
                                employee.getProjects()
                        ))
                        .collect(Collectors.toSet());
        System.out.println(sal);
        System.out.println();
        //flatMap
        String ptojects = employees
                .stream()
                .map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream())
                .collect(Collectors.joining(","));
        System.out.println(ptojects);
        //short circuiting operation
        //it will limit my data
        List<Employee> shortcircuit =
                employees
                        .stream()
                        .skip(1)
                        .limit(1)
                        .collect(Collectors.toList());
        System.out.println(shortcircuit);
        //finite data
        Stream.generate(Math::random)
                .limit(5)
                .forEach(value -> System.out.println(value));
        System.out.println();
        //sorting
        List<Employee> sort =
                employees
                        .stream()
                        .sorted(((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName())))
                        .collect(Collectors.toList());
        System.out.println(sort);
        //min or max salary
        employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
        //reduce
        Double totalsal =
                employees
                        .stream()
                        .map(employee -> employee.getSalary())
                        .reduce(0.0, Double::sum);
        System.out.println(totalsal);

    }
}
