package tests;

import services.AnimeServices;
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
            switch (o) {
                case 1 -> {
                    producerMenu();
                    o = Integer.parseInt(SCANNER.nextLine());
                    ProducerServices.choicesMenu(o);
                }
                case 2 -> {
                    animeMenu();
                    o = Integer.parseInt(SCANNER.nextLine());
                    AnimeServices.choicesMenu(o);
                }
            }
        }
    }

    private static void menu() {
        System.out.println("====================================");
        System.out.println("Type the number of your operation");
        System.out.println("1. Producer");
        System.out.println("2. Anime");
        System.out.println("0. Exit");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
    }

    private static void producerMenu() {
        System.out.println("====================================");
        System.out.println("Type the number of your operation");
        System.out.println("1. Search for Producer | Show all");
        System.out.println("2. Delete Producer");
        System.out.println("3. Insert New Producer");
        System.out.println("4. Update Producer");
        System.out.println("9. Go back");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
    }

    private static void animeMenu() {
        System.out.println("====================================");
        System.out.println("Type the number of your operation");
        System.out.println("1. Search for Anime | Show all");
        System.out.println("2. Delete Anime");
        System.out.println("3. Insert New Anime");
        System.out.println("4. Update Anime");
        System.out.println("9. Go back");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
    }

}
