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
            o = readInt();

            if (o == 0) break;

            switch (o) {
                case 1 -> {
                    producerMenu();
                    o = readInt();
                    ProducerServices.choicesMenu(o);
                }
                case 2 -> {
                    animeMenu();
                    o = readInt();
                    AnimeServices.choicesMenu(o);
                }
            }
        }
    }

    public static int readInt() {
        while (true){
            String input = SCANNER.nextLine();
            try {
                int i = Integer.parseInt(input);
                return i;
            }catch (NumberFormatException e){
                System.out.println("Typing only numbers");
            }
        }
    }

    private static void menu() {
        System.out.println("====================================");
        System.out.println("        MAIN MENU - SYSTEM");
        System.out.println("====================================");
        System.out.println("1. Producer Menu");
        System.out.println("2. Anime Menu");
        System.out.println("0. Exit");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
    }


    private static void producerMenu() {
        System.out.println("====================================");
        System.out.println("          PRODUCER MENU");
        System.out.println("====================================");
        System.out.println("1. Search Producer | Show all");
        System.out.println("2. Delete Producer");
        System.out.println("3. Insert New Producer");
        System.out.println("4. Update Producer");
        System.out.println("9. Go back to Main Menu");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
    }


    private static void animeMenu() {
        System.out.println("====================================");
        System.out.println("            ANIME MENU");
        System.out.println("====================================");
        System.out.println("1. Search Anime | Show all");
        System.out.println("2. Delete Anime");
        System.out.println("3. Insert New Anime");
        System.out.println("4. Update Anime");
        System.out.println("9. Go back to Main Menu");
        System.out.println("====================================");
        System.out.print("Choose an option: ");
    }


}
