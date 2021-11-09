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

    @PostMapping("/update_ruleStatus")
    public ReturnMessage updateRule(@RequestParam Integer id, @RequestParam Integer toStatus){
        if(ruleService.getRuleStatusById(id)==2){
            return ReturnMessage.fail(400).setMessage("Failed. Rule has been offline");
        }
        boolean isOk = ruleService.updateRuleStatus(id, toStatus);
        if(isOk){
            return ReturnMessage.success();
        }else{
            return ReturnMessage.fail(400).setMessage("Failed.");
        }
    }

    @GetMapping("/get_rules")
    public ReturnMessage selectRulesByPage(@RequestParam int pageNum, @RequestParam int pageCount) {
        List<Rule> rules = ruleService.selectRulesByPage(pageNum, pageCount);
        return ReturnMessage.success().setParam("rules", rules);
    }

    @GetMapping("/get_num")
    public ReturnMessage getRulesNum() {
        return ReturnMessage.success().setParam("num", ruleService.getRulesNum());
    }
}
