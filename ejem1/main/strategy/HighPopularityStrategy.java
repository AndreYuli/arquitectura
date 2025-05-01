package strategy;

public class HighPopularityStrategy implements PopularityStrategy {
    @Override
    public void applyPromotion() {
        System.out.println("Alta popularidad: sin descuento, se vende solo.");
    }
}