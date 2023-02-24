package root.it.cupcake.services;

public interface ICartService {

    void addToBasket(int cakeId);

    double calculateBill();

    void removeFromCart(int id);
}
