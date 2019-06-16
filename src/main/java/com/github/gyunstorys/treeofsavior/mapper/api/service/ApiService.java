package com.github.gyunstorys.treeofsavior.mapper.api.service;

import com.github.gyunstorys.treeofsavior.mapper.api.vo.MapInformationVo;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.ResponseVo;
import com.github.gyunstorys.treeofsavior.mapper.api.vo.UserConfig;
import com.github.gyunstorys.treeofsavior.mapper.utils.EditDistance;
import com.github.gyunstorys.treeofsavior.mapper.utils.JasoTokenizer;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Api service.
 */
@Service
public class ApiService {
    @Autowired
    private Tesseract tesseract;
    private final Robot robot;
    private final List<Pair<String, String>> maps;
    @Autowired
    private UserConfig userConfig;
    /**
     * Instantiates a new Api service.
     *
     * @throws AWTException the awt exception
     * @throws IOException  the io exception
     */
    public ApiService() throws AWTException, IOException {
        System.setProperty("java.awt.headless", "false");
        this.robot = new Robot();
        List<Pair<String,String>> maps = IOUtils.readLines(ApiService.class.getResourceAsStream("/map_list.csv"))
                .stream()
                .map(e -> {
                    String[] tempArr = e.split(",");
                    return new Pair<String,String>(tempArr[0],tempArr[1]);
                })
                .collect(Collectors.toList());
        this.maps = maps;
    }

    /**
     * Gets map information.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @return the map information
     * @throws TesseractException the tesseract exception
     */
    public synchronized ResponseVo getMapInformation(int x, int y, int width, int height) throws TesseractException, IOException {
        BufferedImage bufferedImage = getImage(x,y,width,height);
        String text = tesseract.doOCR(bufferedImage);
        if (text.equals("")) {
            ResponseVo responseVo = new ResponseVo();
            responseVo.setCode(1);
            responseVo.setMessage("Image에 OCR Text가 존재하지 않습니다.");
            return responseVo;
        }
        String ocrText = text.trim();
        String ocrJasoText = JasoTokenizer.split(ocrText);


        return new ResponseVo(convertBufferedImageToJpgString(bufferedImage), maps
                .stream()
                .map(e-> new Pair<>(e, JasoTokenizer.split(e.getValue0())))
                .map(e-> new Pair<>(e, EditDistance.calculate(ocrJasoText, e.getValue1(), false)))
                .sorted((e1,e2)->Integer.compare(e1.getValue1(),e2.getValue1()))
                .limit(1)
                .map(e-> new MapInformationVo(ocrText,  e.getValue0().getValue0().getValue0(), e.getValue0().getValue0().getValue1(), e.getValue1()))
                .filter(e->e!=null)
                .collect(Collectors.toList()));
    }

    private String convertBufferedImageToJpgString(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
        return "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    /**
     * Get image buffered image.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @return the buffered image
     */
    public BufferedImage getImage(int x, int y, int width,int height){
        BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
        convertFeatureConvert(bufferedImage);
        return bufferedImage;
    }
    private void convertFeatureConvert(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Color white = new Color(0,0,0);
        Color black = new Color(255,255,255);
        for (int w =0; w< width ; w++){
            for (int h =0; h< height; h++){
                if (new Color(bufferedImage.getRGB(w,h)).getGreen() >= 150 && new Color(bufferedImage.getRGB(w,h)).getRed() >= 150  && new Color(bufferedImage.getRGB(w,h)).getBlue() >= 150){
                    bufferedImage.setRGB(w,h,black.getRGB());
                }else{
                    bufferedImage.setRGB(w,h,white.getRGB());
                }
            }
        }
    }
}
