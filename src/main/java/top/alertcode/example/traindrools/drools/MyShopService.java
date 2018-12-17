package top.alertcode.example.traindrools.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import top.alertcode.example.traindrools.drools.entity.Product;
import top.alertcode.example.traindrools.drools.entity.RuleEntity;
import top.alertcode.example.traindrools.drools.mapper.RulesMapper;

@Service
public class MyShopService {

    /**
     * KieContainer： KieContainer就是一个KieBase的容器，可以根据kmodule.xml 里描述的KieBase信息来获取具体的KieSession
     */
    @Autowired
    private KieContainer kieContainer;
  @Autowired
  private RulesMapper rulesMapper;

    public Product getProductDiscount(Product product) {
        KieSession kieSession = kieContainer.newKieSession("productSession");
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
        return product;
    }


  public Product getDBProduct(int id, Product product) {
    RuleEntity rule = rulesMapper.getRule(id);
    Assert.notNull(rule, "没有找到数据");
    return (Product) getDatabaseObject(rule, product);
  }

  private Object getDatabaseObject(RuleEntity rule, Object object) {
    KieServices kieServices = KieServices.Factory.get();
    KieFileSystem kfs = kieServices.newKieFileSystem();
    kfs.write("src/main/resources/rules/rules" + System.currentTimeMillis() + ".drl",
        rule.getRule().getBytes());
    KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
    Results results = kieBuilder.getResults();
    if (results.hasMessages(Level.ERROR)) {
      throw new IllegalStateException("### errors ###");
    }
    KieContainer kieContainer = kieServices
        .newKieContainer(kieServices.getRepository().getDefaultReleaseId());
    KieSession kieSession = kieContainer.newKieSession();
    kieSession.insert(object);
    kieSession.fireAllRules();
    kieSession.dispose();
    return object;
  }
}