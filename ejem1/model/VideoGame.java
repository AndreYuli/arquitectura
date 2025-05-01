package model;

public class VideoGame {
    private int id;
    private String name;
    private String genre;
    private String developer;
    private double price;
    private String specialFeature; // Nuevo campo para características especiales según el tipo

    // Constructor vacío
    public VideoGame() {
    }

    // Constructor con parámetros
    public VideoGame(int id, String name, String genre, String developer, double price) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.developer = developer;
        this.price = price;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getSpecialFeature() {
        return specialFeature;
    }
    
    public void setSpecialFeature(String specialFeature) {
        this.specialFeature = specialFeature;
    }

    // Método para mostrar información del videojuego
    @Override
    public String toString() {
        return "VideoGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", developer='" + developer + '\'' +
                ", price=" + price +
                ", specialFeature='" + specialFeature + '\'' +
                '}';
    }
}