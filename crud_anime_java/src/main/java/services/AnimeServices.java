package services;

import Repository.AnimeRepository;
import domain.Anime;
import domain.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Scanner;

public class AnimeServices {
    public static final Logger logger = LoggerFactory.getLogger(AnimeRepository.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void choicesMenu(int num) {
        switch (num) {
            case 1 -> findByName();
            case 2 -> delete();
            case 3 -> insert();
            case 4 -> update();
        }
    }

    private static void findByName() {
        System.out.println("====================================");
        System.out.println("   Search Anime (ENTER = All)    ");
        System.out.println("====================================");

        System.out.println("Type the name or press ENTER to show all:");
        String name = SCANNER.nextLine();

        AnimeRepository.findByName(name)
                .forEach(p -> System.out.printf("• [%d]  %s %d %s%n", p.getId(), p.getName(), p.getEpisodes(), p.getProducer().getName()));


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

        boolean exists = AnimeRepository.findByIdToShow(id);

        if (!exists) {
            System.out.println("Invalid id or not exists. Returning to menu...");
            return;
        }

        System.out.println("Are you sure? y/n");
        String userChoice = SCANNER.nextLine();

        if ("y".equalsIgnoreCase(userChoice)) {
            AnimeRepository.delete(id);
        } else {
            return;
        }

    }

    public static void insert() {
        System.out.println("Name of the Anime: ");
        String name = SCANNER.nextLine();

        System.out.println("Type the number of episodes");
        int episodes = Integer.parseInt(SCANNER.nextLine());

        System.out.println("Type the id of the producer");
        Integer id = Integer.parseInt(SCANNER.nextLine());

        Anime build = Anime
                .builder()
                .episodes(episodes)
                .name(name)
                .producer(Producer.builder().id(id).build())
                .build();
        AnimeRepository.insert(build);
    }


    private static void update() {
        System.out.println("====================================");
        System.out.println("   Update Anime    ");
        System.out.println("====================================");

        System.out.println("Type ID of object you want to update: ");
        Optional<Anime> optionalProducer = AnimeRepository.findByIdToUpdate(Integer.parseInt(SCANNER.nextLine()));

        if (optionalProducer.isEmpty()) {
            logger.warn("ID not found!");
            return;
        }

        Anime anime = optionalProducer.get();

        System.out.println("Type de new name or enter to keep the same");
        String name = SCANNER.nextLine();

        System.out.println("Type the new number of episodes or press Enter to keep the same:");
        String epsInput = SCANNER.nextLine();

        if (name.isEmpty()) {
            logger.info("No changes, back to menu...");
            name = anime.getName();
        }
        int eps;

        if (epsInput.isEmpty()) {
            logger.info("No changes, back to menu...");
            eps = anime.getEpisodes();
        } else {
            eps = Integer.parseInt(epsInput);
        }
        Anime updated = Anime.builder()
                .id(anime.getId())
                .name(name)
                .episodes(eps)
                .build();

        AnimeRepository.update(updated);
        logger.info("You new value is => |" + updated.getName() + "|");
    }

}
