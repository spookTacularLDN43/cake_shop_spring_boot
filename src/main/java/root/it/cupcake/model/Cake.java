package root.it.cupcake.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tcake")
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String flavor;
    private int price;
    @Enumerated(EnumType.STRING)
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
