package tests;

import services.ProducerServices;

import java.util.Scanner;

public class CrudTest {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int o;
        while (true) {
            menu();
            o = Integer.parseInt(SCANNER.nextLine());
            if (o == 0) break;
            ProducerServices.choicesMenu(o);

        }
    }

    private static void menu() {
        System.out.println("====================================");
        System.out.println("Type the number of your operation");
        System.out.println("1. Search for Producer | Show all");
        System.out.println("2. Delete Producer");
        System.out.println("3. Insert Producer");
        System.out.println("0. For Exit");
        System.out.println("====================================");
    }
}
