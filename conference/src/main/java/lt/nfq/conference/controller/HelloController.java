package lt.nfq.conference.controller;

import lt.nfq.conference.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("title", "Spring MVC");
		return "hello";
	}
	
	@RequestMapping(value = "/hellodb", method = RequestMethod.GET)
	public String helloDB(ModelMap model) {
		model.addAttribute("demo1", demoService.getDemoData(1));
		model.addAttribute("demo2", demoService.getDemoData(2));
		return "helloDB";
	}
}
