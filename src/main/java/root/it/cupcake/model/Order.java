package root.it.cupcake.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderPosition> positions = new HashSet<>();
    private double price;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(int id, User user, double price, Status status) {
        this.id = id;
        this.user = user;
        this.price = price;
        this.status = status;
    }

    public enum Status {
        ORDERED,
        ACCEPTED,
        SENT,
        ENDED
    }

}
