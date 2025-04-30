package UI;

import factory.ConcreteGameFactory;
import factory.GameFactory;
import factory.VideoGame;
import repository.VideoGameRepository;

import java.util.Scanner;

public class GameFactoryUI {
    private static final GameFactory factory = new ConcreteGameFactory();
    private static final VideoGameRepository repository = new VideoGameRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Menú de Videojuegos ===");
            System.out.println("1. Crear videojuego");
            System.out.println("2. Mostrar todos los videojuegos");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    createVideoGame(scanner);
                    break;
                case 2:
                    showAllVideoGames();
                    break;
                case 3:
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    private static void createVideoGame(Scanner scanner) {
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

    private static void showAllVideoGames() {
        System.out.println("=== Lista de Videojuegos ===");
        for (VideoGame videoGame : repository.getAll()) {
            System.out.println(videoGame);
        }
    }
}
