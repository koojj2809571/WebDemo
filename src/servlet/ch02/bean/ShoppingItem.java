package servlet.ch02.bean;

public class ShoppingItem {
    private Product product;
    private int quantity;

    public ShoppingItem(){}

    public ShoppingItem(Product product,int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
