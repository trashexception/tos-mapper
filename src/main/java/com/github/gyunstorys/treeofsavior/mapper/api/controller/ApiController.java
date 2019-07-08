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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

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
    @RequestMapping(value = "/map/information")
    public ResponseVo getCurrentMapInformationForPosition(UserConfig userConfig) throws TesseractException, IOException {
        return service.getMapInformation(userConfig.getX(),userConfig.getY(),userConfig.getWidth(),userConfig.getHeight());
    }
    @RequestMapping(value="/application/position")
    public ResponseVo getApplicationWindowPosition(){
        ResponseVo responseVo = new ResponseVo();
        Map<String,Double> data = service.getWindowPosition();
        if (data!=null){
            responseVo.setCode(0);
            responseVo.setMessage("정상처리");
            responseVo.setData(data);
            return responseVo;
        }else{
            responseVo.setCode(1);
            responseVo.setMessage("트오세를 실행하지 않았습니다.");
            return responseVo;
        }
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
