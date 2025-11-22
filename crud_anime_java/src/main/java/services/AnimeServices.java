package services;

import Repository.AnimeRepository;
import Repository.ProducerRepository;
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
        String inputEpisodes = SCANNER.nextLine();
        if (!isNum(inputEpisodes)) {
            logger.warn("Invalid value, enter only numbers.");
            return;
        }
        int intEpisodes = Integer.parseInt(inputEpisodes);

        System.out.println("Type the id of the producer");
        String inputId = SCANNER.nextLine();
        if (!isNum(inputId)) {
            logger.warn("Invalid value, enter only numbers.");
            return;
        }


        int intId = Integer.parseInt(inputId);

        int intProducer = Integer.parseInt(inputId);

        Optional<Producer> producerOptional = ProducerRepository.findByIdToUpdate(intProducer);

        if (producerOptional.isEmpty()) {
            logger.warn("Producer ID not found: " + intProducer);
            return;
        }

        Producer producer = producerOptional.get();

        Anime build = Anime
                .builder()
                .id(intId)
                .name(name)
                .episodes(intEpisodes)
                .producer(producer)
                .build();
        AnimeRepository.insert(build);
    }


    private static void update() {
        System.out.println("====================================");
        System.out.println("   Update Anime    ");
        System.out.println("====================================");

        System.out.println("Type ID of object you want to update: ");
        String idInput = SCANNER.nextLine();
        if (!isNum(idInput)) {
            logger.warn("Invalid ID. Enter only numbers!");
            return;
        }

        int id = Integer.parseInt(idInput);

        Optional<Anime> optionalAnime = AnimeRepository.findByIdToUpdate(id);
        if (optionalAnime.isEmpty()) {
            logger.warn("ID not found");
            return;
        }
        Anime anime = optionalAnime.get();

        System.out.println("Type the new name or press Enter to keep the same:");
        String nameInput = SCANNER.nextLine();
        String finalName = nameInput.isEmpty() ? anime.getName() : nameInput;

        System.out.println("Type the new number of episodes or press Enter to keep the same:");
        String epsInput = SCANNER.nextLine();
        int finalEpisodes;

        if (epsInput.isEmpty()) {
            finalEpisodes = anime.getEpisodes();
        } else {
            if (!isNum(epsInput)) {
                logger.warn("Invalid episodes value. Enter only numbers.");
                return;
            }
            finalEpisodes = Integer.parseInt(epsInput);
        }

        Anime updated = Anime.builder()
                .id(anime.getId())
                .name(finalName)
                .episodes(finalEpisodes)
                .producer(anime.getProducer())
                .build();

        AnimeRepository.update(updated);
        logger.info("Your new value is => |" + updated.getName() + "| with episodes => " + updated.getEpisodes());
    }

}
