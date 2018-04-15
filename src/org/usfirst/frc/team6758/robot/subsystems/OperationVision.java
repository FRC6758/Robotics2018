package org.usfirst.frc.team6758.robot.subsystems;

import org.opencv.core.Rect;

public class OperationVision {
    OperationVision(){

    }
    public static final int mist = 5;

    public static Rect[] checkRectum(Rect[] rts){
        for(int x=0; x < rts.length; x++){
            for(int y=0; y<rts.length; y++){
                int theHeightDifference = Math.abs(rts[x].height - rts[y].height);
                int theWidthDifference = Math.abs(rts[x].width - rts[y].width);
                if(theHeightDifference<=mist && theWidthDifference<=mist){
                    Rect[] target = new Rect[2];
                    target[0] = rts[x];
                    target[1] = rts[y];
                    System.out.print("I have found what you are looking for.");
                    return target;
                }
            }
        }
        return rts;
    }

}
