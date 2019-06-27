package com.github.gyunstorys.treeofsavior.mapper.api.controller;

import com.github.gyunstorys.treeofsavior.mapper.api.service.ApiService;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.RequestVo;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.ResponseVo;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.UserConfig;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class ApiController implements ApplicationContextAware {
    @Autowired
    private ApiService service;
    @Autowired
    private UserConfig userConfig;
    private ApplicationContext context;
    @RequestMapping(value = "/name")
    public ResponseVo getCurrentMapName(RequestVo requestVo) throws TesseractException, IOException {
        return service.getMapInformation(requestVo.getX(),requestVo.getY(),requestVo.getWidth(),requestVo.getHeight());
    }

    @RequestMapping(value = "/map/information")
    public ResponseVo getCurrentMapInformation() throws TesseractException, IOException {
        return service.getMapInformation(userConfig.getX(),userConfig.getY(),userConfig.getWidth(),userConfig.getHeight());
    }
    @RequestMapping(value="/test")
    public void test(){
        service.getTextPosition();

    }
    @RequestMapping(value = "/shutdown")
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
