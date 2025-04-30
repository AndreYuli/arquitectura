package dao;

import factory.VideoGame;
import java.util.ArrayList;
import java.util.List;

public class VideoGameDAOImpl implements VideoGameDAO {
    private List<VideoGame> videoGames = new ArrayList<>();

    @Override
    public void save(VideoGame videoGame) {
        videoGames.add(videoGame);
        System.out.println("Videojuego guardado: " + videoGame.getClass().getSimpleName());
    }

    @Override
    public List<VideoGame> getAll() {
        return videoGames;
    }

    @Override
    public VideoGame getByIndex(int index) {
        if (index >= 0 && index < videoGames.size()) {
            return videoGames.get(index);
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango.");
    }

    @Override
    public void deleteByIndex(int index) {
        if (index >= 0 && index < videoGames.size()) {
            videoGames.remove(index);
            System.out.println("Videojuego eliminado en el índice: " + index);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }
}
