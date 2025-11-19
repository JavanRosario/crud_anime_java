package Repository;

import conn.ConnectionDataBase;
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

public class ProducerRepository {
    public static final Logger logger = LoggerFactory.getLogger(ProducerRepository.class);

    public static List<Producer> findByName(String s) {
        logger.info("Finding all from Producers by name: ");

        String sql = "SELECT * FROM anime_store.producer WHERE name LIKE ?;";
        List<Producer> producers = new ArrayList<>();

        try (Connection connection = ConnectionDataBase.getConnection();
             PreparedStatement statement = createPreparedStatement(connection, sql, s);
             ResultSet resultSet = statement.executeQuery();) {


            while (resultSet.next()) {
                Producer producer = Producer
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                producers.add(producer);
            }
        } catch (SQLException | IOException e) {
            logger.warn("CODE ERROR");
        }
        return producers;
    }

    public static PreparedStatement createPreparedStatement(Connection connection, String sql, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        return preparedStatement;
    }
}
