package org.example.data.jpa.service;

import org.example.data.jpa.config.readandwrite.annotation.TargetDataSource;
import org.example.data.jpa.entity.App;
import org.example.data.jpa.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyq on 2020/12/13 14:41.
 */
@Service
public class AppService {
    @Autowired
    private AppRepository appRepository;

    @TargetDataSource(dataSource = "writeDruidDataSource")
//    @Transactional(value = "writeTransactionManager")
    public List<App> getFromMaster() {
        return appRepository.findAll();
    }

    @TargetDataSource(dataSource = "readDruidDataSource")
//    @Transactional(value = "readTransactionManager")
    public List<App> getFromSlave() {
        return appRepository.findAll();
    }

}
