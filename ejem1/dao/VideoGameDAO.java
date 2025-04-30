package dao;

import factory.VideoGame;
import java.util.List;

public interface VideoGameDAO {
    void save(VideoGame videoGame);
    List<VideoGame> getAll();
    VideoGame getByIndex(int index);
    void deleteByIndex(int index);
}
