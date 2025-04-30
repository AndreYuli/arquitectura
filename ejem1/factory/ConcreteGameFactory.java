package factory;

public class ConcreteGameFactory extends GameFactory {
    @Override
    public VideoGame createGame(String type) {
        switch (type.toLowerCase()) {
            case "accion":
                return new ActionGame();
            case "deportes":
                return new SportsGame();
            case "aventura":
                return new AdventureGame();
            default:
                throw new IllegalArgumentException("Tipo de juego no válido: " + type);
        }
    }
}