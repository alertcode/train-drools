package top.alertcode.example.traindrools.drools;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.alertcode.example.traindrools.TrainDroolsApplicationTests;
import top.alertcode.example.traindrools.drools.entity.Product;

import static org.junit.Assert.*;

public class MyShopServiceTest extends TrainDroolsApplicationTests {
    @Autowired
    private MyShopService myShopService;

    @Test
    public void getProductDiscount() {
        Product product = new Product();
        product.setName("apple");
        Product productDiscount = myShopService.getProductDiscount(product);
        System.out.println(productDiscount.toString());
        product.setName("orange");
        Product productDiscount1 = myShopService.getProductDiscount(product);
        System.out.println(productDiscount1.toString());
    }
}