package dao;

import model.VideoGame;
import java.util.List;

public interface VideoGameDAO {
    void save(VideoGame videoGame);
    List<VideoGame> getAll();
    VideoGame getById(int id);
    void deleteById(int id);
    void update(VideoGame videoGame);
}