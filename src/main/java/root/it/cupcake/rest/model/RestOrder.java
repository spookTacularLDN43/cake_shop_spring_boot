package root.it.cupcake.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestOrder {
    private int id;
    private String user;
    private List<String> positions=new ArrayList<>();
    private double price;
    private root.it.cupcake.model.Order.Status status;
}
