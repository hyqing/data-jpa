package org.example.data.jpa.controller;

import org.example.data.jpa.config.readandwrite.annotation.TargetDataSource;
import org.example.data.jpa.config.readandwrite.config.DynamicDataSourceHolder;
import org.example.data.jpa.entity.App;
import org.example.data.jpa.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyq on 2020/12/13 14:46.
 */
@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    private AppService appService;

    @RequestMapping("/master")
    @Transactional
    public List<App> getFromMaster() {
        return appService.getFromMaster();
    }

    @RequestMapping("/slave")
    public List<App> getFromSlave() {
        return appService.getFromSlave();
    }
}
