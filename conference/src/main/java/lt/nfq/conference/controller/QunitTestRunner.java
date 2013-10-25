package lt.nfq.conference.controller;

import java.io.File;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/qunit")
public class QunitTestRunner {
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String runAllTests(ModelMap model, HttpServletRequest request) {
        model.addAttribute("testSuites", getTestFiles(request));
        return "qunit/index";
    }
    
    private LinkedList<String> getTestFiles(HttpServletRequest request) {
        
        String basePath = request.getServletContext().getRealPath("/resources/js/qunit/tests");
        LinkedList<String> results = new LinkedList<String>();
        File[] files = new File(basePath).listFiles();
        
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        
        return results;
    }
}

