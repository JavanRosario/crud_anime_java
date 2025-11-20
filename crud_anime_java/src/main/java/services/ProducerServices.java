package services;

import Repository.ProducerRepository;
import domain.Producer;

import java.util.List;
import java.util.Scanner;

public class ProducerServices {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void choicesMenu(int num) {
        switch (num) {
            case 1:
                findByName();
                break;
            case 2:
                delete();
                break;
            default:
                throw new IllegalArgumentException("Invalid option");
        }
    }

    private static void findByName() {
        System.out.println("Type the name or press ENTER to show all:");
        String name = SCANNER.nextLine();
        List<Producer> producers = ProducerRepository.findByName(name);
        producers.forEach(p -> System.out.printf("[%d] - %s%n",p.getId(),p.getName()));
    }

    private static void delete() {
        System.out.println("Type one of the ID bellow to delete or 0 to back");
        String input = SCANNER.nextLine();

        if (!isNum(input)) {
            System.out.println("Invalid value, enter only numbers");
            return;
        }

        int id = Integer.parseInt(input);
        if (id == 0) return;

        boolean exists = ProducerRepository.findById(id);

        if (!exists) {
            System.out.println("Invalid id or not exists. Returning to menu...");
            return;
        }

        System.out.println("Are you sure? y/n");
        String userChoice = SCANNER.nextLine();

        if ("y".equalsIgnoreCase(userChoice)) {
            ProducerRepository.delete(id);
        } else {
            return;
        }

    }

    public static void insert() {
        System.out.println("Name of the Producer: ");
        String name = SCANNER.nextLine();
        Producer build = Producer.builder().name(name).build();
        ProducerRepository.insert(build);
    }


}
