package top.alertcode.example.traindrools.drools;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.alertcode.example.traindrools.TrainDroolsApplicationTests;
import top.alertcode.example.traindrools.drools.entity.Product;

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

    @Test
    public void getRule() {
        Product product = new Product();
        product.setName("apple");
        Product product1 = myShopService.getDBProduct(1, product);
        System.out.println(product1.toString());
        Assert.assertEquals(45678, product1.getDiscount());
    }
}