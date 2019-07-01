package com.github.gyunstorys.treeofsavior.mapper;

import com.sun.jna.platform.WindowUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest({"user.x=1130","user.y=27","user.width=300","user.height=31","user.tessdata=\"c:/Users/gyun/ideaprojects/tos-mapper/tessdata\""})
public class TosMapperApplicationTests {

    @Test
    public void contextLoads() {
        WindowUtils.getAllWindows(true).stream().filter(e->e.getTitle().contains("Tree Of Savior")).forEach(e-> System.out.println(e.getLocAndSize()));
    }


}
