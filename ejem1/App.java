import factory.*;
import strategy.*;
import repository.VideoGameRepository;
import model.VideoGame;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        GameFactory factory = new ConcreteGameFactory();
        VideoGameRepository repository = new VideoGameRepository();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Crear videojuego");
            System.out.println("2. Mostrar todos los videojuegos");
            System.out.println("3. Aplicar estrategia de promoción");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    createVideoGame(factory, repository, scanner);
                    break;
                case 2:
                    showAllVideoGames(repository);
                    break;
                case 3:
                    applyPromotionStrategy(scanner);
                    break;
                case 4:
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    private static void createVideoGame(GameFactory factory, VideoGameRepository repository, Scanner scanner) {
        System.out.println("=== Crear Videojuego ===");
        System.out.print("Ingresa el tipo de videojuego (accion, deportes, aventura): ");
        String type = scanner.nextLine();

        VideoGame videoGame = factory.createGame(type);
        if (videoGame != null) {
            System.out.print("Ingresa el ID del videojuego: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Ingresa el nombre del videojuego: ");
            String name = scanner.nextLine();

            System.out.print("Ingresa el género del videojuego: ");
            String genre = scanner.nextLine();

            System.out.print("Ingresa el desarrollador del videojuego: ");
            String developer = scanner.nextLine();

            System.out.print("Ingresa el precio del videojuego: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer

            videoGame.setId(id);
            videoGame.setName(name);
            videoGame.setGenre(genre);
            videoGame.setDeveloper(developer);
            videoGame.setPrice(price);

            repository.save(videoGame);
            System.out.println("Videojuego creado y guardado exitosamente.");
        } else {
            System.out.println("Tipo de videojuego no válido.");
        }
    }

    private static void showAllVideoGames(VideoGameRepository repository) {
        System.out.println("=== Lista de Videojuegos ===");
        for (VideoGame videoGame : repository.getAll()) {
            System.out.println(videoGame);
        }
    }

    private static void applyPromotionStrategy(Scanner scanner) {
        System.out.println("=== Aplicar Estrategia de Promoción ===");
        System.out.print("Ingresa el nivel de popularidad del videojuego (0-100): ");
        int popularidad = scanner.nextInt();

        PopularityStrategy estrategia;

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