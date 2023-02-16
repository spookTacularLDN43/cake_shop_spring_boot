package root.it.cupcake.database.impl;

import ij.IJ;
import org.springframework.stereotype.Component;
import root.it.cupcake.database.ICakeRepository;
import root.it.cupcake.model.Cake;

import java.util.ArrayList;
import java.util.List;

@Component
public class CakeRepositoryImpl implements ICakeRepository {
    private List<Cake> cakeList = new ArrayList<>();

    public CakeRepositoryImpl() {
        cakeList.add(new Cake("Chocolate pancakes", "chocolate", 20, Cake.Type.PANCAKES, 20, "pancakes-g956766721_1920.jpg"));
        cakeList.add(new Cake("Pancakes with fruits", "vanilla", 22, Cake.Type.PANCAKES, 30, "restaurant-g7fbc3c967_1920.jpg"));
        cakeList.add(new Cake("Cake with blueberries", "blueberry", 12, Cake.Type.CAKE, 20, "blueberries-g8442efcc8_1920.jpg"));
        cakeList.add(new Cake("Vanilla cupcake", "vanilla", 10, Cake.Type.MUFFIN, 30, "baked-goods-g0eb66d1cb_1920.jpg"));
        cakeList.add(new Cake("Chocolate ice-cream", "chocolate", 12, Cake.Type.ICE_CREAM, 20, "dessert-g815a4a021_1920.jpg"));
        cakeList.add(new Cake("Cheesecake", "cheese", 15, Cake.Type.CAKE, 30, "baked-goods-gc2b7d7cdf_1920.jpg"));
        cakeList.add(new Cake("Red Velvet cake", "cocoa", 17, Cake.Type.CAKE,20, "red-velvet-cake-gdcc1cc2b1_1920.jpg"));
        cakeList.add(new Cake("Strawberry muffin", "strawberry", 8, Cake.Type.MUFFIN,30, "cakes-g2363673ee_1920.jpg"));
    }

    @Override
    public List<Cake> getAllCakes() {
        return this.cakeList;
    }
}
