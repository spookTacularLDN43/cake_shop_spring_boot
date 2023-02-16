package root.it.cupcake.model;

import ij.ImagePlus;

public class Cake {
    private String name;
    private String flavor;
    private int price;
    private Type type;
    private int pieces;
    private String link;

    public Cake(String name, String flavor, int price, Type type, int pieces, String link) {
        this.name = name;
        this.flavor = flavor;
        this.price = price;
        this.type = type;
        this.pieces = pieces;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public enum Type {
        MUFFIN,
        CAKE,
        ICE_CREAM,
        PANCAKES
    }
}
