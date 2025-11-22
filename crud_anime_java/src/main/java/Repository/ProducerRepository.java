package Repository;

import conn.ConnectionDataBase;
import domain.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerRepository {
    public static final Logger logger = LoggerFactory.getLogger(ProducerRepository.class);

    public static List<Producer> findByName(String s) {
        logger.info("Finding all from Producers by name... ");

        String sql = "SELECT * FROM anime_store.producer WHERE name LIKE ?;";
        List<Producer> producers = new ArrayList<>();

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, sql, s);
             ResultSet resultSet = statement.executeQuery();) {

            if (!resultSet.next()) {
                logger.warn("Name not find...");
                return new ArrayList<>();
            }

            while (resultSet.next()) {
                Producer producer = Producer
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                producers.add(producer);
            }

        } catch (SQLException | IOException e) {
            logger.warn("Connection failed, check your MySQL client");
        }
        return producers;

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
            e.printStackTrace();
        }
    }

    public static PreparedStatement createPreparedStatementDelete(Connection connection, Integer id) throws SQLException {
        String sql = "DELETE FROM anime_store.producer WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    public static boolean findByIdToShow(Integer id) {

        List<Producer> producers = new ArrayList<>();

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementId(connection, id);
             ResultSet resultSet = statement.executeQuery();) {


            if (!resultSet.next()) {
                return false;
            }

            Producer producer = Producer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();
            producers.add(producer);

            System.out.println(producer);
            return true;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static Optional<Producer> findByIdToUpdate(Integer id) {

        List<Producer> producers = new ArrayList<>();
        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementId(connection, id);
             ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.next()) return Optional.empty();

            Optional<Producer> build = Optional.of(Producer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build());
            System.out.println("####");
            System.out.println("You ID Producer is: "+build.get().getName());
            return build;


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static PreparedStatement createPreparedStatementId(Connection connection, Integer id) throws SQLException {
        String sql = "SELECT * FROM anime_store.producer WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    public static void insert(Producer producer) {
        logger.info("Adding to table..." + producer.getName());
        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementInsert(connection, producer)) {
            statement.execute();
        } catch (SQLException | IOException e) {
            logger.warn("INSERT ERROR");
            e.printStackTrace();
        }
    }

    public static PreparedStatement createPreparedStatementInsert(Connection connection, Producer producer) throws SQLException {
        String sql = "INSERT INTO anime_store.producer (name) VALUES (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, producer.getName());
        return preparedStatement;
    }


    public static void update(Producer producer) {

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatementUpdate(connection, producer)) {
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            logger.warn("UPDATE ERROR");
        }
    }

    public static PreparedStatement createPreparedStatementUpdate(Connection connection, Producer producer) throws SQLException {
        String sql = "UPDATE anime_store.producer SET name = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, producer.getName());
        preparedStatement.setInt(2, producer.getId());
        return preparedStatement;
    }


}







