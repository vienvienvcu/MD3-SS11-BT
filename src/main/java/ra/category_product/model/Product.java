package ra.category_product.model;

public class Product {
    private int id;
    private String name;
    private String image;
    private Categories categories;
    private double price;
    private String description;
    private String producer;
    private int stock;
    private boolean status;
    public Product() {
    }
    public Product(int id, String name, String image, Categories categories, double price, String description, String producer,int stock, boolean status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.categories = categories;
        this.price = price;
        this.description = description;
        this.producer = producer;
        this.stock = stock;
        this.status = true;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
