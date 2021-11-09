package com.example.demo.controller;

import com.example.demo.entity.ReturnMessage;
import com.example.demo.entity.Rule;
import com.example.demo.service.RuleService;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/configure_rule")
public class ConfigureController {
    @Resource
    private RuleService ruleService;

    @PostMapping
    public ReturnMessage insert(@RequestBody @Validated Rule rule){
        boolean isOk = ruleService.configureRule(rule);
        if(isOk) {
            return ReturnMessage.success();
        } else {
            return ReturnMessage.fail(400).setMessage("Failed. Please check your data!");
        }
    }
}
