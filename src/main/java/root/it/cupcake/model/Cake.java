package root.it.cupcake.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cake {
    private String name;
    private String flavor;
    private int price;
    private Type type;
    private int pieces;
    private String link;


    public enum Type {
        MUFFIN,
        CAKE,
        ICE_CREAM,
        PANCAKES
    }
}
