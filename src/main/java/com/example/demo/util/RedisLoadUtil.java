package com.example.demo.util;

import com.example.demo.entity.Rule;
import com.example.demo.mapper.RuleMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *  启动项目后, 加载数据库公共配置数据到redis中
 * */
@Component
public class RedisLoadUtil {

    @Resource
    private RedisTemplate<String, HashSet<String>> redisTemplate;
    @Resource
    private RuleMapper ruleMapper;

    @PostConstruct // 是java注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
    public void reload() {
        int dataNum = ruleMapper.getRulesNum();
        int beginId = 0;
        for (int i = 0; i < dataNum; i += 10000) {
            List<Rule> rules = ruleMapper.getPartOfDeviceIdList(beginId, 10000);
            ValueOperations<String, HashSet<String>> operations = redisTemplate.opsForValue();
            for (Rule r:rules) {
                boolean hasKey = redisTemplate.hasKey(r.getId().toString());
                if (!hasKey) {
                    String[] temp = r.getDevice_id_list().split(" ");
                    operations.set(r.getId().toString(), new HashSet<>(Arrays.asList(temp)));
                }
                beginId = Math.max(beginId, r.getId());
            }
        }
    }
}

