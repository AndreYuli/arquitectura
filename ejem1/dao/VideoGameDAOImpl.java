package dao;

import model.VideoGame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoGameDAOImpl implements VideoGameDAO {
    // Usamos un Map para simular una base de datos con acceso por ID
    private Map<Integer, VideoGame> videoGames = new HashMap<>();

    @Override
    public void save(VideoGame videoGame) {
        videoGames.put(videoGame.getId(), videoGame);
        System.out.println("Videojuego guardado en DAO: " + videoGame.getName());
    }

    @Override
    public List<VideoGame> getAll() {
        return new ArrayList<>(videoGames.values());
    }

    @Override
    public VideoGame getById(int id) {
        return videoGames.get(id);
    }

    @Override
    public void deleteById(int id) {
        if (videoGames.containsKey(id)) {
            videoGames.remove(id);
            System.out.println("Videojuego eliminado en DAO con ID: " + id);
        }
    }
    
    @Override
    public void update(VideoGame videoGame) {
        if (videoGames.containsKey(videoGame.getId())) {
            videoGames.put(videoGame.getId(), videoGame);
            System.out.println("Videojuego actualizado en DAO: " + videoGame.getName());
        }
    }
}