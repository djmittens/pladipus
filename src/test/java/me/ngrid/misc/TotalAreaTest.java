package me.ngrid.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/rectangle-area/">l33tc0d3</a>
 */
public class TotalAreaTest {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaA, areaB, overLap;
        areaA = Math.abs(C - A) * Math.abs(D - B);
        areaB = Math.abs(G - E) * Math.abs(H - F);
        if(E > C || A > G || E > C || B > H || F > D)
            overLap = 0;
        else
            overLap =  Math.max((Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F)), 0);

        return areaA + areaB - overLap;
    }

    @Test
    public void offBy1() {
       assertEquals(computeArea(-2, -2, 2, 2, 3, 3, 4, 4), 17);
    }
}
