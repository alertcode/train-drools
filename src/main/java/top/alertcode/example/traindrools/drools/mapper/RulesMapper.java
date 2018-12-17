package top.alertcode.example.traindrools.drools.mapper;

import org.apache.ibatis.annotations.Select;
import top.alertcode.example.traindrools.drools.entity.RuleEntity;

public interface RulesMapper {

  @Select("select * from drools_rules where id=#{id}")
   RuleEntity getRule(int id);

}