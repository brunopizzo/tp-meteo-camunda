package kt.bpm.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import kt.bpm.services.CamundaRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private CamundaRestService camundaRestService;

    @GetMapping("/")
    public ModelAndView index() throws UnirestException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        String pid = (String) camundaRestService.startProcess("meteo").getBody().getObject().get("id");
        modelAndView.addObject("pid",pid);
        return modelAndView;
    }
}
