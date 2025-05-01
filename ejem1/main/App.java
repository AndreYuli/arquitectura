import factory.*;
import strategy.*;

public class App {
    public static void main(String[] args) {
        GameFactory factory = new ConcreteGameFactory();

        
        VideoGame juego1 = factory.createGame("accion");
        VideoGame juego2 = factory.createGame("deportes");

        juego1.showInfo();
        juego2.showInfo();

        
        PopularityStrategy estrategia;

        int popularidad = 65; 

        if (popularidad > 80) {
            estrategia = new HighPopularityStrategy();
        } else if (popularidad > 50) {
            estrategia = new MediumPopularityStrategy();
        } else {
            estrategia = new LowPopularityStrategy();
        }

        estrategia.applyPromotion();
    }
}