package servlet.ch02.bean;

public class Customer {
    private int id = -1;
    private String name = null;
    private String city = null;

    public Customer(){

    }

    public Customer(int id,String name,String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        if (id == -1 && name == null && city == null){
            return "{}";
        }
        return "{id:" + id + ",name:" + name + ",city:" + city + "}";
    }
}
