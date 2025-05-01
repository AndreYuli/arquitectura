package strategy;

public class LowPopularityStrategy implements PopularityStrategy {
    @Override
    public void applyPromotion() {
        System.out.println("Baja popularidad: aplicar 30% de descuento.");
    }
}