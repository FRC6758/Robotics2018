package org.usfirst.frc.team6758.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.opencv.core.Rect;

public class OperationVision {
    
    OperationVision(){

    }
    public static final int mist = 5;
    private static final double WIDTH = 50.8;

    public static Rect[] checkRect(Rect[] rts){
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
        return null; //TODO Im scared
    }

    public static double checkDistance(Rect[] rts){
        //In millimeters
        //The width of the tape is: 2in?
        int width = rts[0].width;

        return 0; //TODO More zeros???
    }

    public static double MMToIn(double millimeter){
       return millimeter * .039370078740158;
    }
    public static double InToMM(double inch){
        return inch * 25.4;
    }


}
