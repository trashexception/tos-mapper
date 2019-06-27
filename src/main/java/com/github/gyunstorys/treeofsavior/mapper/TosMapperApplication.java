package com.github.gyunstorys.treeofsavior.mapper;

import com.github.gyunstorys.treeofsavior.mapper.api.vo.UserConfig;
import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TosMapperApplication {

    public static void main(String[] args) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SpringApplication application = new SpringApplication(TosMapperApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @Bean
    public Tesseract getTesseract(@Autowired UserConfig userConfig){
        Tesseract tesseract = new Tesseract();
        tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_LSTM_ONLY);
        tesseract.setLanguage("kor");
        System.out.println("tess data path : " + userConfig.getTessdata());
        tesseract.setDatapath(userConfig.getTessdata());
        return tesseract;
    }

}
