package application;

public class DataStorage {
    
	private String code;
    private String name;
    private int price;
    private int stock;

    public DataStorage(String code,String name, Integer price, Integer stock) {
        this.code = code;
    	this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}