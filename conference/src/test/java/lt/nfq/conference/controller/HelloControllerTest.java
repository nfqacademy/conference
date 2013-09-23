package lt.nfq.conference.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.ModelMap;

public class HelloControllerTest {

	@Test
	public void testHello() {
		HelloController controller = new HelloController();
		
		ModelMap model = new ModelMap();
		String view  = controller.hello(model);
		
		assertEquals("hello", view);
		assertEquals(1, model.size());
		assertEquals("Spring MVC", model.get("title"));
	}

}
