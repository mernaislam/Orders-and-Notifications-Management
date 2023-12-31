package app.controllers;

import app.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class StatisticsController {
    private final StatisticsService statisticsService;
    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    // return  most notified template - statistics
    @GetMapping(path="/getTemplateStatistics")
    public String getTemplateStatistics() {
        return statisticsService.getTemplateStatistics();
    }
    // return most notified channel - statistics
    @GetMapping (path="/getChannelStatistics")
    public String getChannelStatistics() {
        return statisticsService.getChannelStatistics();
    }
    // return most notified customer - statistics
    @GetMapping (path="/getCustomerStatistics")
    public String getCustomerStatistics() {
        return statisticsService.getCustomerStatistics();
    }
}
