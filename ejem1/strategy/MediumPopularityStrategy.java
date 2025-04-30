package strategy;

public class MediumPopularityStrategy implements PopularityStrategy {
    @Override
    public void applyPromotion() {
        System.out.println("Popularidad media: aplicar 10% de descuento.");
    }
}