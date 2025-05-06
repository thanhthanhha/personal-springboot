package com.realchat.store;

import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private int Cash = 3;
	
	@RequestMapping("/")
	public String index() {
        logger.trace("Trace message");
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error fuck head message");
        logger.error("Error fuck head message");
		return "index";
	}
	
    @RequestMapping("/cash")
    public String cash(Model model) {
        logger.info("Accessing cash page with value: " + Cash);
        model.addAttribute("cashValue", Cash);
        return "home/cash";
    }
}
