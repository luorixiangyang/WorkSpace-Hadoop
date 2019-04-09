package com.dongnaoedu.forkjoin.vo;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/12
 * 创建时间: 16:01
 *
 * 蟠桃的实体类
 */
public class PanTao {

    private final Color color;
    private final Size size;
    private final int Year;

    public PanTao(Color color, Size size, int year) {
        this.color = color;
        this.size = size;
        Year = year;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getYear() {
        return Year;
    }

    @Override
    public String toString() {
        return "PanTao{" +
                "color=" + color +
                ", size=" + size +
                ", Year=" + Year +
                '}';
    }
}
