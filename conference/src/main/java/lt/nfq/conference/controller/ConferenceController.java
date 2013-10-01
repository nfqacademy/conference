package lt.nfq.conference.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import lt.nfq.conference.domain.Conference;
import lt.nfq.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {

        SimpleDateFormat simpleDateFormat = getDateFormat();
        long timeNow = new Date().getTime();

        String startDate = simpleDateFormat.format(timeNow);
        String endDate = simpleDateFormat.format(timeNow + 1000 * 60 * 60 * 24 * 10); // + 10d
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("dateFormat", simpleDateFormat);

        try {
            model.addAttribute("conferenceList", conferenceService.getConferencesList(simpleDateFormat.parse(startDate), simpleDateFormat.parse(endDate)));
        } catch (ParseException e) {

        }

        return "conference/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String filterList(ModelMap model,
                             @RequestParam(value = "start") Date start,
                             @RequestParam(value = "end") Date end) {

        model.addAttribute("conferenceList", conferenceService.getConferencesList(start, end));
        model.addAttribute("dateFormat", getDateFormat());

        return "conference/items";
    }

    @RequestMapping(value = "/create")
    public String create(ModelMap model) {
        model.addAttribute("conference", new Conference());
        return "conference/form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(ModelMap model, @RequestParam(value = "id") int id) {
        model.addAttribute("conference", conferenceService.getConferences(id));
        model.addAttribute("dateFormat", getDateFormat());
        return "conference/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    HashMap<String, String> save(@ModelAttribute("conference") Conference conference) {
        HashMap<String, String> response = new HashMap<String, String>();
        if (conferenceService.updateConference(conference)) {
            response.put("success", "saved");
        } else {
            response.put("error", "error with saving");
        }
        response.put("status", "ok");
        return response;
    }


    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


}
