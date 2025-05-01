package repository;

import db.DatabaseConnection;
import factory.GameType;
import model.VideoGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.VideoGameDAO;

public class VideoGameRepository {

    public VideoGameRepository(VideoGameDAO videoGameDAO) {
        //TODO Auto-generated constructor stub
    }

    // Método para guardar un videojuego en la base de datos
    public void save(VideoGame videoGame) {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement cs = connection.prepareCall("{call sp_InsertVideoGame(?, ?, ?, ?, ?, ?, ?)}")) {

            // Mapear el tipo de juego a su ID
            int typeId = mapGameTypeToId(videoGame);
            
            cs.setInt(1, videoGame.getId());
            cs.setString(2, videoGame.getName());
            cs.setString(3, videoGame.getGenre());
            cs.setString(4, videoGame.getDeveloper());
            cs.setDouble(5, videoGame.getPrice());
            cs.setInt(6, typeId);
            cs.setString(7, videoGame.getSpecialFeature());

            cs.execute();
            System.out.println("Videojuego guardado en la base de datos con ID: " + videoGame.getId());
        } catch (SQLException e) {
            System.err.println("Error al guardar el videojuego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener todos los videojuegos de la base de datos
    public List<VideoGame> getAll() {
        List<VideoGame> videoGames = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement cs = connection.prepareCall("{call sp_GetAllVideoGames}");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                VideoGame videoGame = mapResultSetToVideoGame(rs);
                videoGames.add(videoGame);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los videojuegos: " + e.getMessage());
            e.printStackTrace();
        }

        return videoGames;
    }

    // Método para obtener un videojuego por su ID
    public VideoGame getById(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement cs = connection.prepareCall("{call sp_GetVideoGameByID(?)}")) {

            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToVideoGame(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el videojuego por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Método para eliminar un videojuego por su ID
    public void deleteById(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement cs = connection.prepareCall("{call sp_DeleteVideoGame(?)}")) {

            cs.setInt(1, id);
            int rowsAffected = cs.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Videojuego eliminado con ID: " + id);
            } else {
                System.out.println("No se encontró un videojuego con el ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el videojuego: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para actualizar un videojuego
    public void update(VideoGame videoGame) {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement cs = connection.prepareCall("{call sp_UpdateVideoGame(?, ?, ?, ?, ?, ?)}")) {

            cs.setInt(1, videoGame.getId());
            cs.setString(2, videoGame.getName());
            cs.setString(3, videoGame.getGenre());
            cs.setString(4, videoGame.getDeveloper());
            cs.setDouble(5, videoGame.getPrice());
            cs.setString(6, videoGame.getSpecialFeature());

            int rowsAffected = cs.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Videojuego actualizado con ID: " + videoGame.getId());
            } else {
                System.out.println("No se encontró un videojuego con el ID: " + videoGame.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el videojuego: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Método para aplicar una promoción a un videojuego
    public void applyPromotion(int gameId, String promotionType, double discountPercentage) {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement cs = connection.prepareCall("{call sp_ApplyPromotion(?, ?, ?, ?, ?)}")) {

            cs.setInt(1, gameId);
            cs.setString(2, promotionType);
            cs.setDouble(3, discountPercentage);
            cs.setNull(4, java.sql.Types.TIMESTAMP); // EndDate = null (sin fecha de fin)
            cs.setString(5, "Admin"); // CreatedBy

            cs.execute();
            System.out.println("Promoción aplicada al videojuego con ID: " + gameId);
        } catch (SQLException e) {
            System.err.println("Error al aplicar la promoción: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método auxiliar para mapear un ResultSet a un objeto VideoGame
    private VideoGame mapResultSetToVideoGame(ResultSet rs) throws SQLException {
        VideoGame videoGame = new VideoGame();
        videoGame.setId(rs.getInt("ID"));
        videoGame.setName(rs.getString("Name"));
        videoGame.setGenre(rs.getString("Genre"));
        videoGame.setDeveloper(rs.getString("Developer"));
        videoGame.setPrice(rs.getDouble("Price"));
        videoGame.setSpecialFeature(rs.getString("SpecialFeature"));
        return videoGame;
    }
    
    // Método auxiliar para mapear el tipo de juego a su ID en la base de datos
    private int mapGameTypeToId(VideoGame videoGame) {
        String feature = videoGame.getSpecialFeature();
        if (feature == null) {
            return 1; // Por defecto, juego de acción
        }
        
        if (feature.contains("Modo historia")) {
            return 1; // ACTION
        } else if (feature.contains("multijugador")) {
            return 2; // SPORTS
        } else if (feature.contains("Mundo abierto")) {
            return 3; // ADVENTURE
        }
        
        return 1; // Por defecto, juego de acción
    }
}