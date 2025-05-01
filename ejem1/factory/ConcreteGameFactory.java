package factory;

import model.VideoGame;

public class ConcreteGameFactory extends GameFactory {
    @Override
    public void configureGame(VideoGame videoGame, GameType type) {
        switch (type) {
            case ACTION -> {
                // Configuración específica para juegos de acción
                videoGame.setSpecialFeature("Modo historia inmersivo");
                System.out.println("Juego de acción configurado.");
            }
            case SPORTS -> {
                // Configuración específica para juegos de deportes
                videoGame.setSpecialFeature("Modo multijugador en línea");
                System.out.println("Juego de deportes configurado.");
            }
            case ADVENTURE -> {
                // Configuración específica para juegos de aventura
                videoGame.setSpecialFeature("Mundo abierto exploratorio");
                System.out.println("Juego de aventura configurado.");
            }
        }
    }
}