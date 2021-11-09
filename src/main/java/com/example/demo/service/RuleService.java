package com.example.demo.service;

import com.example.demo.entity.Rule;
import com.example.demo.mapper.RuleMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class RuleService {
    @Resource
    private RuleMapper ruleMapper;
    @Resource
    private RedisTemplate<String, HashSet<String>> redisTemplate;
    public boolean configureRule(Rule rule){
        ValueOperations<String, HashSet<String>> operations = redisTemplate.opsForValue();
        int result = ruleMapper.insert(rule);
        if(result==0)
            return false;
        if(!rule.getPlatform().equals("IOS") && (rule.getMax_os_api()==null || rule.getMin_os_api()==null))
            return false;
        String[] whiteList=rule.getDevice_id_list().split(" ");
        HashSet<String> set = new HashSet<>(Arrays.asList(whiteList));
        operations.set(rule.getId().toString(),set);
        return true;
    }
}