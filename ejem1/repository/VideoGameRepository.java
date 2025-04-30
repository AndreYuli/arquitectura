package repository;

import db.DatabaseConnection;
import model.VideoGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoGameRepository {

    // Método para guardar un videojuego en la base de datos
    public void save(VideoGame videoGame) {
        String query = "INSERT INTO VideoGames (id, name, genre, developer, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, videoGame.getId());
            statement.setString(2, videoGame.getName());
            statement.setString(3, videoGame.getGenre());
            statement.setString(4, videoGame.getDeveloper());
            statement.setDouble(5, videoGame.getPrice());

            statement.executeUpdate();
            System.out.println("Videojuego guardado en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar el videojuego: " + e.getMessage());
        }
    }

    // Método para obtener todos los videojuegos de la base de datos
    public List<VideoGame> getAll() {
        List<VideoGame> videoGames = new ArrayList<>();
        String query = "SELECT * FROM VideoGames";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                VideoGame videoGame = new VideoGame(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getString("developer"),
                        resultSet.getDouble("price")
                );
                videoGames.add(videoGame);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los videojuegos: " + e.getMessage());
        }

        return videoGames;
    }

    // Método para obtener un videojuego por su ID
    public VideoGame getById(int id) {
        String query = "SELECT * FROM VideoGames WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new VideoGame(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getString("developer"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el videojuego: " + e.getMessage());
        }
        return null;
    }

    // Método para eliminar un videojuego por su ID
    public void deleteById(int id) {
        String query = "DELETE FROM VideoGames WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Videojuego eliminado de la base de datos.");
            } else {
                System.out.println("No se encontró un videojuego con el ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el videojuego: " + e.getMessage());
        }
    }
}