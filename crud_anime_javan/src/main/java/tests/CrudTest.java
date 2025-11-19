package tests;

import Repository.ProducerRepository;
import services.ProducerServices;

import java.util.Scanner;

public class CrudTest {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int o;
        while (true) {
            menu();
            o = Integer.parseInt(scanner.nextLine());
            if (o == 0) break;
            ProducerServices.makeMenu(o);

        }
    }

    private static void menu() {
        System.out.println("Type the number of your operation");
        System.out.println("1. Search for Producer");
        System.out.println("0. For Exit");
    }
}
