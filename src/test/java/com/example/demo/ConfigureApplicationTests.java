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
//		insert into userinformation (id, username, password, permission) values (1, "admin", "hashHex", 1);
//		insert into userinformation (id, username, password, permission) values (2, "com", "hashHex", 0);
		for (int i = 1; i <= 100; i++) {
			Rule rule = new Rule(null, i, "iOS", "http://www.baidu.com",
					"8.1.3.01", "md1145141919810", null,
					"gfgfgf", "dfddf",
					13434, 354, "32", "dfdf",
					"update_title", "update_tips", 0,0);
			configureController.insert(rule, "11", "hashHex，11");
		}
	}

	@Test
	void selectRules() {
		System.out.println(configureController.selectRulesByPage(1, 10));
	}

	@Test
	void getRulesNum() {
		System.out.println(configureController.getRulesNum());
	}

	@Test
	void updateStatus(){
		System.out.println(configureController.updateRule(40, 1, "admin", "wrongPassWord"));
		System.out.println(configureController.updateRule(40, 1, "admin", "hashHex"));
		System.out.println(configureController.updateRule(40, 0, "admin", "hashHex"));
		System.out.println(configureController.updateRule(40, 2, "admin", "hashHex"));
		System.out.println(configureController.updateRule(40, 0, "admin", "hashHex"));
	}

	@Test
	void tokenIdentify(){
		System.out.println(configureController.getRulesNum());
		Rule rule = new Rule(null, 1, "iOS", "http://www.baidu.com",
				"8.1.3.01", "md1145141919810", null,
				"gfgfgf", "dfddf",
				13434, 354, "32", "dfdf",
				"update_title", "update_tips", 0,0);
		System.out.println(configureController.insert(rule, "com", "wrongPassWord"));
		System.out.println(configureController.insert(rule, "com", "hashHex"));
		System.out.println(configureController.insert(rule, "admin", "hashHex"));
		System.out.println(configureController.getRulesNum());
	}
}
