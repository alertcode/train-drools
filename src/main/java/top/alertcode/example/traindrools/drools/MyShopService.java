package top.alertcode.example.traindrools.drools;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alertcode.example.traindrools.drools.entity.Product;

@Service
public class MyShopService {

    /**
     * KieContainer： KieContainer就是一个KieBase的容器，可以根据kmodule.xml 里描述的KieBase信息来获取具体的KieSession
     */
    @Autowired
    private KieContainer kieContainer;

    public Product getProductDiscount(Product product) {
        KieSession kieSession = kieContainer.newKieSession("productSession");
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
        return product;
    }
}