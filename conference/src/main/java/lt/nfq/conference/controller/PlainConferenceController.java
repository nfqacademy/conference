package lt.nfq.conference.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lt.nfq.conference.domain.Conference;
import lt.nfq.conference.service.ConferenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlainConferenceController {

	public static class Form {
		private Conference conference;
	    
	    public Form() {
	    	this(new Conference());
	    }
	    
	    public Form(Conference conference) {
	    	this.conference = conference;
	    }
	    
	    public Conference getCondefence() {
	    	return conference;
	    }

	    public Integer getId() {
	        return conference.getId();
	    }

	    public void setId(Integer id) {
	        conference.setId(id);;
	    }

	    @NotNull
	    @Size(min = 1, max = 100)
	    public String getName () {
	        return conference.getName();
	    }

	    public void setName(String name) {
	        conference.setName(name);
	    }

	    @NotNull
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    public Date getStartDate() {
	        return conference.getStartDate();
	    }

	    public void setStartDate(Date startDate) {
	        conference.setStartDate(startDate);
	    }

	    @NotNull
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	    public Date getEndDate() {
	        return conference.getEndDate();
	    }

	    public void setEndDate(Date endDate) {
	        conference.setEndDate(endDate);
	    }

	}
	
    private final ConferenceService conferenceService;

    @Autowired
    public PlainConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    /**
     * For every request for this controller, this will 
     * create a conference instance for the form.
     */
    @ModelAttribute
    public Form newRequest(@RequestParam(required=false) Integer id) {
        return new Form(id != null ? conferenceService.getConference(id) : new Conference());
    }

    /**
     * <p>Conference form request.</p>
     * 
     * <p>Expected HTTP GET and request '/plain/conference/form'.</p>
     */
    @RequestMapping(value="/plain/conference/form", method=RequestMethod.GET)
    public void form() {
    }
    
    /**
     * <p>Saves a person.</p>
     * 
     * <p>Expected HTTP POST and request '/plain/conference/form'.</p>
     */
    @RequestMapping(value="/plain/conference/form", method=RequestMethod.POST)
    public Form form(@Valid Form form, Model model, BindingResult bindingResult) {

        conferenceService.saveConference(form.getCondefence());
        
        model.addAttribute("statusMessageKey", "person.form.msg.success");
        
        return form;
    }

}
