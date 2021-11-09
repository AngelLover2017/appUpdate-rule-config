package com.example.demo.controller;

import com.example.demo.entity.Rule;
import com.example.demo.service.RuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/configure_rule")
public class ConfigureController {
    @Resource
    private RuleService ruleService;
    @PostMapping
    public String insert(@RequestBody Rule rule){
        boolean isOk =  ruleService.configureRule(rule);
        if(isOk)
            return "success!";
        return "failed,please check your data!";
    }
}
