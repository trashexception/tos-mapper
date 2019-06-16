package com.github.gyunstorys.treeofsavior.mapper.api.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The type User config.
 */
@Configuration
public class UserConfig {
    @Value("${user.x}")
    private int x;
    @Value("${user.y}")
    private int y;
    @Value("${user.width}")
    private int width;
    @Value("${user.height}")
    private int height;
    @Value("${user.tessdata}")
    private String tessdata;

    /**
     * Gets tessdata.
     *
     * @return the tessdata
     */
    public String getTessdata() {
        return tessdata;
    }

    /**
     * Sets tessdata.
     *
     * @param tessdata the tessdata
     */
    public void setTessdata(String tessdata) {
        this.tessdata = tessdata;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
