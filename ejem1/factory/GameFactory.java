package factory;

import model.VideoGame;

public abstract class GameFactory {
    public abstract void configureGame(VideoGame videoGame, GameType type);
}