package root.it.cupcake.model;

public class Cake {
    private String name;
    private String flavor;
    private int price;
    private Type type;
    private int pieces;

    public Cake(String name, String flavor, int price, Type type, int pieces) {
        this.name = name;
        this.flavor = flavor;
        this.price = price;
        this.type = type;
        this.pieces = pieces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public enum Type {
        MUFFIN,
        CAKE,
        ICE_CREAM,
        PANCAKES
    }
}
