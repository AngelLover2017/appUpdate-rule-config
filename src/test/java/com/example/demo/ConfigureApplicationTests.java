package com.example.demo;

import com.example.demo.controller.ConfigureController;
import com.example.demo.entity.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigureApplicationTests {
	@Autowired
	private ConfigureController configureController;

	@Test
	void addRule() {
		Rule rule = new Rule(null, 1, "iOS", "http://www.baidu.com",
				"8.1.3.01", "md1145141919810", null,
				"gfgfgf", "dfddf",
				13434, 354, "32", "dfdf",
				"update_title", "update_tips", 0);
		configureController.insert(rule);
	}

	@Test
	void selectRules() {
		System.out.println(configureController.selectRulesByPage(1, 10));
	}

	@Test
	void getRulesNum() {
		System.out.println(configureController.getRulesNum());
	}
}
