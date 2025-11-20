package services;

import Repository.ProducerRepository;
import domain.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Scanner;

public class ProducerServices {
    public static final Logger logger = LoggerFactory.getLogger(ProducerRepository.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void choicesMenu(int num) {
        switch (num) {
            case 1 -> findByName();
            case 2 -> delete();
            case 3 -> insert();
            case 4 -> update();
            default -> throw new IllegalArgumentException("Invalid option");
        }
    }

    private static void findByName() {
        System.out.println("====================================");
        System.out.println("   Search Producer (ENTER = All)    ");
        System.out.println("====================================");

        System.out.println("Type the name or press ENTER to show all:");
        String name = SCANNER.nextLine();

        ProducerRepository.findByName(name)
                .forEach(p -> System.out.printf("• [%d] %s%n", p.getId(), p.getName()));


    }

    public static boolean isNum(String s) {
        return s != null && s.matches("\\d+");
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

        boolean exists = ProducerRepository.findByIdToShow(id);

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


    private static void update() {
        System.out.println("====================================");
        System.out.println("   Update Producer    ");
        System.out.println("====================================");

        System.out.println("Type ID of object you want to update: ");
        Optional<Producer> optionalProducer = ProducerRepository.findByIdToUpdate(Integer.parseInt(SCANNER.nextLine()));

        if (optionalProducer.isEmpty()) {
            logger.warn("ID not found!");
            return;
        }

        Producer producer = optionalProducer.get();

        System.out.println("Type de new name or enter to keep the same");
        String name = SCANNER.nextLine();

        if (name.isEmpty()) {
            logger.info("No changes, back to menu...");
            name = producer.getName();
        } else {
            Producer updated = Producer.builder()
                    .id(producer.getId())
                    .name(name)
                    .build();

            ProducerRepository.update(updated);
        }
        logger.info("You new value is => |" + producer.getName() + "|");
    }

}
