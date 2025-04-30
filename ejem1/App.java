import factory.*;
import strategy.*;
import repository.VideoGameRepository;
import model.VideoGame;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        GameFactory factory = new ConcreteGameFactory();
        VideoGameRepository repository = new VideoGameRepository();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        System.out.println("=== Sistema de Gestión de Videojuegos ===");
        System.out.println("Este sistema utiliza los patrones:");
        System.out.println("- Factory Method para crear diferentes tipos de juegos");
        System.out.println("- Strategy para aplicar estrategias de promoción");
        System.out.println("- DAO para el acceso a datos");
        System.out.println("- Repository para la lógica de negocio");
        System.out.println("------------------------------------------");

        while (running) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Crear videojuego");
            System.out.println("2. Mostrar todos los videojuegos");
            System.out.println("3. Buscar videojuego por ID");
            System.out.println("4. Eliminar videojuego por ID");
            System.out.println("5. Aplicar estrategia de promoción");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int option = 0;
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                scanner.nextLine(); // Limpiar el buffer en caso de error
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (option) {
                case 1:
                    createVideoGame(factory, repository, scanner);
                    break;
                case 2:
                    showAllVideoGames(repository);
                    break;
                case 3:
                    findVideoGameById(repository, scanner);
                    break;
                case 4:
                    deleteVideoGameById(repository, scanner);
                    break;
                case 5:
                    applyPromotionStrategy(repository, scanner);
                    break;
                case 6:
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
        db.DatabaseConnection.closeConnection();
    }

    private static void createVideoGame(GameFactory factory, VideoGameRepository repository, Scanner scanner) {
        System.out.println("\n=== Crear Videojuego ===");
        System.out.print("Ingresa el tipo de videojuego (accion, deportes, aventura): ");
        String type = scanner.nextLine();

        VideoGame videoGame = factory.createGame(type);
        if (videoGame != null) {
            System.out.print("Ingresa el ID del videojuego: ");
            int id = 0;
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                scanner.nextLine(); // Limpiar el buffer en caso de error
                System.out.println("ID inválido. Creación cancelada.");
                return;
            }

            System.out.print("Ingresa el nombre del videojuego: ");
            String name = scanner.nextLine();

            System.out.print("Ingresa el género del videojuego: ");
            String genre = scanner.nextLine();

            System.out.print("Ingresa el desarrollador del videojuego: ");
            String developer = scanner.nextLine();

            System.out.print("Ingresa el precio del videojuego: ");
            double price = 0;
            try {
                price = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                scanner.nextLine(); // Limpiar el buffer en caso de error
                System.out.println("Precio inválido. Creación cancelada.");
                return;
            }

            videoGame.setId(id);
            videoGame.setName(name);
            videoGame.setGenre(genre);
            videoGame.setDeveloper(developer);
            videoGame.setPrice(price);

            repository.save(videoGame);
            System.out.println("Videojuego creado y guardado exitosamente.");
            videoGame.showInfo();
        } else {
            System.out.println("Tipo de videojuego no válido.");
        }
    }

    private static void showAllVideoGames(VideoGameRepository repository) {
        System.out.println("\n=== Lista de Videojuegos ===");
        List<VideoGame> games = repository.getAll();

        if (games.isEmpty()) {
            System.out.println("No hay videojuegos registrados.");
        } else {
            for (VideoGame game : games) {
                System.out.println(game);
            }
            System.out.println("Total de videojuegos: " + games.size());
        }
    }

    private static void findVideoGameById(VideoGameRepository repository, Scanner scanner) {
        System.out.println("\n=== Buscar Videojuego por ID ===");
        System.out.print("Ingresa el ID del videojuego: ");
        int id = 0;
        try {
            id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar el buffer en caso de error
            System.out.println("ID inválido.");
            return;
        }

        VideoGame game = repository.getById(id);
        if (game != null) {
            System.out.println("Videojuego encontrado:");
            System.out.println(game);
        } else {
            System.out.println("No se encontró un videojuego con el ID: " + id);
        }
    }

    private static void deleteVideoGameById(VideoGameRepository repository, Scanner scanner) {
        System.out.println("\n=== Eliminar Videojuego por ID ===");
        System.out.print("Ingresa el ID del videojuego a eliminar: ");
        int id = 0;
        try {
            id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar el buffer en caso de error
            System.out.println("ID inválido.");
            return;
        }

        VideoGame game = repository.getById(id);
        if (game != null) {
            System.out.println("Se eliminará el siguiente videojuego:");
            System.out.println(game);
            System.out.print("¿Estás seguro? (s/n): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("s")) {
                repository.deleteById(id);
                System.out.println("Videojuego eliminado exitosamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("No se encontró un videojuego con el ID: " + id);
        }
    }

    private static void applyPromotionStrategy(VideoGameRepository repository, Scanner scanner) {
        System.out.println("\n=== Aplicar Estrategia de Promoción ===");

        // Primero mostramos los juegos disponibles
        List<VideoGame> games = repository.getAll();
        if (games.isEmpty()) {
            System.out.println("No hay videojuegos registrados para aplicar promociones.");
            return;
        }

        System.out.println("Videojuegos disponibles:");
        for (VideoGame game : games) {
            System.out.println(game.getId() + " - " + game.getName() + " ($" + game.getPrice() + ")");
        }

        System.out.print("Ingresa el ID del videojuego para aplicar promoción: ");
        int gameId = 0;
        try {
            gameId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar el buffer en caso de error
            System.out.println("ID inválido.");
            return;
        }

        VideoGame selectedGame = repository.getById(gameId);
        if (selectedGame == null) {
            System.out.println("No se encontró un videojuego con el ID: " + gameId);
            return;
        }

        System.out.print("Ingresa el nivel de popularidad del videojuego (0-100): ");
        int popularidad = 0;
        try {
            popularidad = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (popularidad < 0 || popularidad > 100) {
                System.out.println("El nivel de popularidad debe estar entre 0 y 100.");
                return;
            }
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar el buffer en caso de error
            System.out.println("Nivel de popularidad inválido.");
            return;
        }

        PopularityStrategy estrategia;

        if (popularidad > 80) {
            estrategia = new HighPopularityStrategy();
        } else if (popularidad > 50) {
            estrategia = new MediumPopularityStrategy();
        } else {
            estrategia = new LowPopularityStrategy();
        }

        System.out.println("Aplicando estrategia de promoción al juego: " + selectedGame.getName());
        repository.applyPromotionStrategy(gameId, estrategia);
    }
}