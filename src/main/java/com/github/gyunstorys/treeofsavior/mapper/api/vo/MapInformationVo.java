package com.github.gyunstorys.treeofsavior.mapper.api.vo;

/**
 * The type Response vo.
 */
public class MapInformationVo {
    private String ocrMapName;
    private String realName;
    private String url;
    private int editDistance;

    /**
     * Instantiates a new Map information vo.
     *
     * @param ocrMapName   the ocr map name
     * @param realName     the real name
     * @param url          the url
     * @param editDistance the edit distance
     */
    public MapInformationVo(String ocrMapName, String realName, String url, int editDistance) {
        this.ocrMapName = ocrMapName;
        this.realName = realName;
        this.url = url;
        this.editDistance = editDistance;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets ocr map name.
     *
     * @return the ocr map name
     */
    public String getOcrMapName() {
        return ocrMapName;
    }

    /**
     * Sets ocr map name.
     *
     * @param ocrMapName the ocr map name
     */
    public void setOcrMapName(String ocrMapName) {
        this.ocrMapName = ocrMapName;
    }

    /**
     * Gets real name.
     *
     * @return the real name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Sets real name.
     *
     * @param realName the real name
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Gets edit distance.
     *
     * @return the edit distance
     */
    public int getEditDistance() {
        return editDistance;
    }

    /**
     * Sets edit distance.
     *
     * @param editDistance the edit distance
     */
    public void setEditDistance(int editDistance) {
        this.editDistance = editDistance;
    }
}
