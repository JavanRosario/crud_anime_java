package Repository;

import conn.ConnectionDataBase;
import domain.Anime;
import domain.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimeRepository {
    public static final Logger logger = LoggerFactory.getLogger(AnimeRepository.class);

    public static List<Anime> findByName(String s) {
        logger.info("Finding all from Animes by name... ");

        String sql = """
                SELECT
                    a.id,
                    a.name,
                    a.episodes,
                    a.producer_id,
                    p.name AS producer_name
                FROM anime_store.anime a
                INNER JOIN anime_store.producer p
                    ON a.producer_id = p.id
                WHERE a.name LIKE ?;
                """;

        List<Anime> animes = new ArrayList<>();

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, sql, s);
             ResultSet resultSet = statement.executeQuery();) {

            if (!resultSet.next()) {
                logger.warn("Name not find...");
                return new ArrayList<>();
            }

            while (resultSet.next()) {

                Producer producer = Producer.builder()
                        .name(resultSet.getString("producer_name"))
                        .id(resultSet.getInt("producer_id"))
                        .build();

                Anime anime = Anime
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .episodes(resultSet.getInt("episodes"))
                        .producer(producer)
                        .build();
                animes.add(anime);
            }

        } catch (SQLException | IOException e) {
            logger.warn("Connection failed, check your MySQL client");
        }
        return animes;

    }

    public static PreparedStatement createPreparedStatement(Connection connection, String sql, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        return preparedStatement;
    }


    public static void delete(int id) {
        try (Connection connection = ConnectionDataBase.getConnection()) {
            PreparedStatement ps = createPreparedStatementDelete(connection, id);
            ps.execute();
            logger.info("Delete successfully! ");
            System.out.println();
        } catch (SQLException | IOException e) {
            logger.warn("DELETE ERROR");
        }
    }

    public static PreparedStatement createPreparedStatementDelete(Connection connection, Integer id) throws SQLException {
        String sql = "DELETE FROM anime_store.anime WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }


    public static PreparedStatement createPreparedStatementIdToShow(Connection connection, Integer id) throws SQLException {
        String sql = """
                SELECT
                    a.id,
                    a.name,
                    a.episodes,
                    a.producer_id,
                    p.name AS producer_name
                FROM anime_store.anime a
                INNER JOIN anime_store.producer p
                    ON a.producer_id = p.id
                WHERE a.id = ?;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    public static boolean findByIdToShow(Integer id) {

        List<Anime> animes = new ArrayList<>();

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementIdToShow(connection, id);
             ResultSet resultSet = statement.executeQuery();) {


            if (!resultSet.next()) {
                return false;
            }

            Producer producer = Producer.builder()
                    .name(resultSet.getString("producer_name"))
                    .id(resultSet.getInt("producer_id"))
                    .build();

            Anime anime = Anime
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .episodes(resultSet.getInt("episodes"))
                    .producer(producer)
                    .build();
            animes.add(anime);

            System.out.println(anime);


            return true;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    public static PreparedStatement createPreparedStatementId(Connection connection, Integer id) throws SQLException {
        String sql = """
                SELECT
                    a.id,
                    a.name,
                    a.episodes,
                    a.producer_id,
                    p.name AS producer_name
                FROM anime_store.anime a
                INNER JOIN anime_store.producer p
                    ON a.producer_id = p.id
                WHERE a.id = ?;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    public static void insert(Anime anime) {
        logger.info("Adding to table..." + anime);
        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementInsert(connection, anime)) {
            statement.execute();
        } catch (SQLException | IOException e) {
            logger.warn("INSERT ERROR");
        }
    }

    public static PreparedStatement createPreparedStatementInsert(Connection connection, Anime anime) throws SQLException {
        String sql = "INSERT INTO anime_store.anime (name,episodes,producer_id) VALUES (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, anime.getName());
        preparedStatement.setInt(2, anime.getEpisodes());
        preparedStatement.setInt(3, anime.getProducer().getId());
        return preparedStatement;
    }


    public static void update(Anime anime) {

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementUpdate(connection, anime)) {
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.warn("UPDATE ERROR");
        }
    }

    public static PreparedStatement createPreparedStatementUpdate(Connection connection, Anime anime) throws SQLException {
        String sql = "UPDATE anime_store.anime SET name = ?, episodes = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, anime.getName());
        preparedStatement.setInt(2, anime.getEpisodes());
        preparedStatement.setInt(3, anime.getId());
        return preparedStatement;
    }

    public static Optional<Anime> findByIdToUpdate(Integer id) {

        List<Anime> animes = new ArrayList<>();
        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementId(connection, id);
             ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.next()) return Optional.empty();
            Producer producer = Producer.builder()
                    .name(resultSet.getString("producer_name"))
                    .id(resultSet.getInt("producer_id"))
                    .build();

            Anime anime = Anime
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .episodes(resultSet.getInt("episodes"))
                    .producer(producer)
                    .build();
            System.out.println("####");
            System.out.println("You ID name is: "+anime.getName());
            return Optional.of(anime);


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}







