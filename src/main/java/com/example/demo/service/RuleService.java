package com.example.demo.service;

import com.example.demo.entity.ReturnMessage;
import com.example.demo.entity.Rule;
import com.example.demo.entity.UserInformation;
import com.example.demo.mapper.RuleMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class RuleService {
    @Resource
    private RuleMapper ruleMapper;

    @Resource
    private RedisTemplate<String, HashSet<String>> redisTemplate;

    public boolean checkRule(Rule rule) {
        String[] versionCode = new String[]{rule.getMax_update_version_code(),rule.getMin_update_version_code(),rule.getUpdate_version_code()};
        for(String s:versionCode){
            for(int i=0 ; i<s.length() ; i++){
                char c=s.charAt(i);
                if(!Character.isDigit(c)&&c!='.')
                    return false;
            }
        }
        return true;
    }

    public boolean configureRule(Rule rule){
        if (!checkRule(rule)) {
            return false;
        }
        ValueOperations<String, HashSet<String>> operations = redisTemplate.opsForValue();
        int result = ruleMapper.insert(rule);
        if(result==0)
            return false;
        if(!rule.getPlatform().equals("IOS") && (rule.getMax_os_api()==null || rule.getMin_os_api()==null))
            return false;
        if (rule.getDevice_id_list()!= null) {
            String[] whiteList=rule.getDevice_id_list().split(" ");
            HashSet<String> set = new HashSet<>(Arrays.asList(whiteList));
            operations.set(rule.getId().toString(),set);
        }
        return true;
    }

    public String tokenIdentify(String username, String password){
        UserInformation userInformation = ruleMapper.getUserByName(username);
        if(username.equals("") || password.equals("")){
            return "缺少用户名或密码参数";
        }
        if(userInformation==null || !userInformation.getPassword().equals(password)){
            // 密码错误
            return "密码错误或用户名不存在";
        }
        if(userInformation.getPermission()!=1){
            // 不是管理员
            return "无管理员权限";
        }
        return "success";
    }

    public boolean updateRuleStatus(Integer id, Integer toStatus){
        ValueOperations<String, HashSet<String>> operations = redisTemplate.opsForValue();
        if(toStatus!=0){
            redisTemplate.delete(id.toString());
        }else{
            String[] whiteList = ruleMapper.getDeviceIdListById(id).split(" ");
            HashSet<String> set = new HashSet<>(Arrays.asList(whiteList));
            operations.set(id.toString(),set);
        }
        return ruleMapper.updateStatus(id, toStatus)>0;
    }

    public int getRuleStatusById(Integer id){
        return ruleMapper.getRuleStatus(id);
    }

    public List<Rule> selectRulesByPage(int pageNum, int pageCount) {
        pageNum = (pageNum - 1) * pageCount;
        if (pageNum < 0) {
            pageNum = 0;
        }
        if (pageCount < 0) {
            pageCount = 10;
        }
        return ruleMapper.select(pageNum, pageCount);
    }

    public int getRulesNum() {
        return ruleMapper.getRulesNum();
    }
}
