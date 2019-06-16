package com.github.gyunstorys.treeofsavior.mapper.api.controller;

import com.github.gyunstorys.treeofsavior.mapper.api.service.ApiService;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.RequestVo;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.ResponseVo;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.UserConfig;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ApiService service;
    @Autowired
    private UserConfig userConfig;
    @RequestMapping(value = "/name")
    public ResponseVo getCurrentMapName(RequestVo requestVo) throws TesseractException, IOException {
        return service.getMapInformation(requestVo.getX(),requestVo.getY(),requestVo.getWidth(),requestVo.getHeight());
    }

    @RequestMapping(value = "/map/information")
    public ResponseVo getCurrentMapInformation() throws TesseractException, IOException {
        return service.getMapInformation(userConfig.getX(),userConfig.getY(),userConfig.getWidth(),userConfig.getHeight());
    }
}
