/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpuscheduling;

import java.util.Comparator;

/**
 *
 * @author HP
 */
public class SJFComp implements Comparator<Process> {

    @Override
    public int compare(Process a, Process b) {

        if (a.getBT() >= b.getBT()) {
            return 1;
        } else {
            return -1;
        }
    }


}
