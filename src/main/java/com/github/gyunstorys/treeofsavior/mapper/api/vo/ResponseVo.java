package com.github.gyunstorys.treeofsavior.mapper.api.vo;

import java.util.List;

/**
 * The type Response vo.
 *
 * @param <T> the type parameter
 */
public class ResponseVo <T> {
    private int code=0;
    private String message;
    private String image;
    private T data;
    private List<MapInformationVo> informations;


    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets informations.
     *
     * @return the informations
     */
    public List<MapInformationVo> getInformations() {
        return informations;
    }

    /**
     * Sets informations.
     *
     * @param informations the informations
     */
    public void setInformations(List<MapInformationVo> informations) {
        this.informations = informations;
    }

    /**
     * Instantiates a new Response vo.
     *
     * @param image        the image
     * @param informations the informations
     */
    public ResponseVo(String image, List<MapInformationVo> informations) {
        this.image = image;
        this.informations = informations;
    }

    /**
     * Instantiates a new Response vo.
     */
    public ResponseVo() {
    }

    @Override
    public String toString() {
        return "ResponseVo{" +
                "image='" + image + '\'' +
                ", informations=" + informations +
                '}';
    }
}
