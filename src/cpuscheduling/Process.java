/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpuscheduling;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Process {

    String name;
    private int AT;
    public int BT;
    private int prio;
    private int WT;
    private int tat;
    public int elapsedTime;
    public int RT;
    public int FT;

    public Process(String name, int at, int bt, int prio) {
        this.AT = at;
        this.BT = bt;
        this.prio = prio;
        this.WT = 0;
        this.tat = 0;
        this.name = name;
        this.elapsedTime = 0;
        this.RT = bt;
        this.FT = 0;

    }

    public Process() {
    }

    public Process(String name, int at, int bt) {
        this.AT = at;
        this.BT = bt;
        this.WT = 0;
        this.tat = 0;
        this.name = name;
        this.elapsedTime = 0;
        this.RT = bt;
        this.FT = 0;

    }

    public String getName() {
        return this.name;
    }

    public int getWT() {
        return this.WT;
    }

    public int getBT() {
        return this.BT;
    }

    public int getPrio() {
        return this.prio;
    }

    public int getAT() {
        return this.AT;
    }

    public int getTAT() {
        return this.tat;
    }

    public void setWT(int w) {
        this.WT = w;
    }

    public void setBT(int b) {
        this.BT = b;
    }

    public void setTAT(int t) {
        this.tat = t;
    }

}
