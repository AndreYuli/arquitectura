import model.VideoGame;
import factory.*;
import strategy.*;
import repository.VideoGameRepository;
import dao.VideoGameDAOImpl;
import dao.VideoGameDAO;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final String MAIN_MENU = "\n=== Menú Principal ===\n1. Crear videojuego\n2. Mostrar todos los videojuegos\n3. Buscar videojuego por ID\n4. Eliminar videojuego por ID\n5. Aplicar estrategia de promoción\n6. Salir\nSelecciona una opción: ";
    private static final String INVALID_INPUT_MESSAGE = "Por favor, ingresa un número válido.";
    private static final String INVALID_ID_MESSAGE = "ID inválido.";
    private static final String INVALID_PRICE_MESSAGE = "Precio inválido. Creación cancelada.";

    public static void main(String[] args) {
        GameFactory factory = new ConcreteGameFactory();
        VideoGameDAO videoGameDAO = new VideoGameDAOImpl();
        VideoGameRepository repository = new VideoGameRepository(videoGameDAO);
        Scanner scanner = new Scanner(System.in);

        displayWelcomeMessage();
        boolean running = true;
        while (running) {
            System.out.print(MAIN_MENU);
            int option = validateIntInput(scanner, INVALID_INPUT_MESSAGE);
            switch (option) {
                case 1 -> createVideoGame(factory, repository, scanner);
                case 2 -> showAllVideoGames(repository);
                case 3 -> findAndDisplayGameById(repository, scanner);
                case 4 -> deleteVideoGameById(repository, scanner);
                case 5 -> applyPromotionStrategy(repository, scanner);
                case 6 -> {
                    running = false;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        scanner.close();
        db.DatabaseConnection.closeConnection();
    }

    private static void displayWelcomeMessage() {
        System.out.println("=== Sistema de Gestión de Videojuegos ===");
        System.out.println("Este sistema utiliza los patrones:");
        System.out.println("- Factory Method para crear diferentes tipos de juegos");
        System.out.println("- Strategy para aplicar estrategias de promoción");
        System.out.println("- DAO para el acceso a datos");
        System.out.println("- Repository para la lógica de negocio");
        System.out.println("------------------------------------------");
    }

    private static int validateIntInput(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // Clear buffer
                System.out.println(errorMessage);
            }
        }
    }

    private static double validateDoubleInput(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (Exception e) {
                scanner.nextLine(); // Clear buffer
                System.out.println(errorMessage);
            }
        }
    }

    private static void createVideoGame(GameFactory factory, VideoGameRepository repository, Scanner scanner) {
        System.out.println("\n=== Crear Videojuego ===");
        System.out.print("Ingresa el tipo de videojuego (accion, deportes, aventura): ");
        scanner.nextLine(); // Limpiar buffer
        String gameType = scanner.nextLine();

        // Crear el objeto base usando el factory
        GameType type = null;
        switch (gameType.toLowerCase()) {
            case "accion" -> type = GameType.ACTION;
            case "deportes" -> type = GameType.SPORTS;
            case "aventura" -> type = GameType.ADVENTURE;
            default -> {
                System.out.println("Tipo de videojuego no válido.");
                return;
            }
        }
        
        // Crear el objeto VideoGame con los datos básicos
        VideoGame videoGame = new VideoGame();
        populateVideoGameDetails(videoGame, scanner);
        
        // Usar el factory para establecer características específicas del tipo
        factory.configureGame(videoGame, type);
        
        // Guardar en el repositorio
        repository.save(videoGame);

        System.out.println("Videojuego creado y guardado exitosamente.");
        System.out.println(videoGame);
    }

    private static void populateVideoGameDetails(VideoGame videoGame, Scanner scanner) {
        System.out.print("Ingresa el ID del videojuego: ");
        videoGame.setId(validateIntInput(scanner, INVALID_ID_MESSAGE));
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingresa el nombre del videojuego: ");
        videoGame.setName(scanner.nextLine());

        System.out.print("Ingresa el género del videojuego: ");
        videoGame.setGenre(scanner.nextLine());

        System.out.print("Ingresa el desarrollador del videojuego: ");
        videoGame.setDeveloper(scanner.nextLine());

        System.out.print("Ingresa el precio del videojuego: ");
        videoGame.setPrice(validateDoubleInput(scanner, INVALID_PRICE_MESSAGE));
    }

    private static void showAllVideoGames(VideoGameRepository repository) {
        System.out.println("\n=== Lista de Videojuegos ===");
        List<VideoGame> games = repository.getAll();
        if (games.isEmpty()) {
            System.out.println("No hay videojuegos registrados.");
        } else {
            games.forEach(System.out::println);
            System.out.println("Total de videojuegos: " + games.size());
        }
    }

    private static void findAndDisplayGameById(VideoGameRepository repository, Scanner scanner) {
        System.out.println("\n=== Buscar Videojuego por ID ===");
        System.out.print("Ingresa el ID del videojuego: ");
        int id = validateIntInput(scanner, INVALID_ID_MESSAGE);

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
        int id = validateIntInput(scanner, INVALID_ID_MESSAGE);

        VideoGame game = repository.getById(id);
        if (game != null) {
            System.out.println("Se eliminará el siguiente videojuego:");
            System.out.println(game);
            System.out.print("¿Estás seguro? (s/n): ");
            String confirm = scanner.next();
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
        System.out.print("Ingresa el ID del videojuego: ");
        int id = validateIntInput(scanner, INVALID_ID_MESSAGE);

        VideoGame game = repository.getById(id);
        if (game == null) {
            System.out.println("No se encontró un videojuego con el ID: " + id);
            return;
        }

        System.out.println("Videojuego seleccionado: " + game.getName());
        System.out.println("Selecciona el nivel de popularidad del videojuego:");
        System.out.println("1. Alta popularidad");
        System.out.println("2. Media popularidad");
        System.out.println("3. Baja popularidad");
        System.out.print("Opción: ");
        
        int popularityOption = validateIntInput(scanner, INVALID_INPUT_MESSAGE);
        PopularityStrategy strategy;
        String promotionType;
        double discountPercentage;
        
        switch (popularityOption) {
            case 1 -> {
                strategy = new HighPopularityStrategy();
                promotionType = "Alta Popularidad";
                discountPercentage = 0;
            }
            case 2 -> {
                strategy = new MediumPopularityStrategy();
                promotionType = "Media Popularidad";
                discountPercentage = 10;
            }
            case 3 -> {
                strategy = new LowPopularityStrategy();
                promotionType = "Baja Popularidad";
                discountPercentage = 30;
            }
            default -> {
                System.out.println("Opción no válida. Operación cancelada.");
                return;
            }
        }
        
        // Aplicar la estrategia de promoción
        System.out.println("\nAplicando estrategia de promoción para " + game.getName() + ":");
        strategy.applyPromotion();
        
        // Guardar la promoción en la base de datos
        repository.applyPromotion(id, promotionType, discountPercentage);
        
        // Obtener el juego actualizado para mostrar el nuevo precio
        VideoGame updatedGame = repository.getById(id);
        
        System.out.println("Precio original: $" + game.getPrice());
        System.out.println("Nuevo precio con descuento: $" + updatedGame.getPrice());
        System.out.println("La estrategia de promoción se aplicó exitosamente.");
    }
}