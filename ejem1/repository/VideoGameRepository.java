package repository;

import dao.VideoGameDAO;
import model.VideoGame;
import java.util.List;

public class VideoGameRepository {
    private VideoGameDAO videoGameDAO;

    // Constructor que recibe el DAO
    public VideoGameRepository(VideoGameDAO videoGameDAO) {
        this.videoGameDAO = videoGameDAO;
    }

    // Método para guardar un videojuego
    public void save(VideoGame videoGame) {
        // Aquí podríamos añadir lógica de negocio antes de guardar
        videoGameDAO.save(videoGame);
    }

    // Método para obtener todos los videojuegos
    public List<VideoGame> getAll() {
        return videoGameDAO.getAll();
    }

    // Método para obtener un videojuego por su ID
    public VideoGame getById(int id) {
        return videoGameDAO.getById(id);
    }

    // Método para eliminar un videojuego por su ID
    public void deleteById(int id) {
        videoGameDAO.deleteById(id);
    }
    
    // Método para actualizar un videojuego
    public void update(VideoGame videoGame) {
        videoGameDAO.update(videoGame);
    }
}