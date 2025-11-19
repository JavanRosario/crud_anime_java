package services;

import Repository.ProducerRepository;
import domain.Producer;

import java.util.List;
import java.util.Scanner;

public class ProducerServices {
    private static Scanner scanner = new Scanner(System.in);

    public static void makeMenu(int num) {
        switch (num) {
            case 1:
                findByName();
                break;
            default:
                throw new IllegalArgumentException("Invalid option");
        }
    }

    private static void findByName() {
        System.out.println("Type the name:");
        String name = scanner.nextLine();
        List<Producer> producers = ProducerRepository.findByName(name);
        for (int i = 0; i < producers.size(); i++) {
            System.out.printf("[%d] - %s%n", i, producers.get(i).getName());
        }
    }
}
