/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpuscheduling;

import java.awt.Color;

/**
 *
 * @author HP
 */
public class MyRectangle {

    private int x;
    private int y;
    private int w;
    private int l;
    private Color color;
    private String label;
    private int xLabel;
    private int yLabel;

    public MyRectangle(int x, int y, int w, int l, Color c, String s, int xl, int yl) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.l = l;
        this.color = c;
        this.label = s;
        this.xLabel = xl;
        this.yLabel = yl;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getL() {
        return this.l;
    }

    public Color getCol() {
        return this.color;
    }

    public int getXLabel() {
        return this.xLabel;
    }

    public int getYLabel() {
        return this.yLabel;
    }

    public String getLabel() {
        return this.label;
    }
}
