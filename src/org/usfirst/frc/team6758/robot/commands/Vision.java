package org.usfirst.frc.team6758.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.opencv.core.Rect;
import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.subsystems.OperationVision;


public class Vision extends Command {
    public Vision() {
        // Use requires() here to declare subsystem dependencies
    }

    @Override
    protected void initialize() {
        //TODO: DISTANCE - Find distance by backing robot up and watching width feed
        //TODO: OFFSET - Split dim of camera to find middle, use that as reference
        //TODO: ANGLE - Compare right and left tape - which ever is bigger is what side we are on

    }

    @Override
    protected void execute() {
        Rect[] rts = Robot.rts;
        System.out.print(rts.length);
        System.out.print("I think I found it....");
        Rect[] target = OperationVision.checkRect(rts); //THIS
        if(target != null){
            System.out.print("NO NO NO NO NO NO.....");
        }
        else{
            System.out.print("Found it");
            double distance = OperationVision.checkDistance(target);
            while(distance > 50){
                Robot.driveTrain.driveForward(.9);
            }

        }
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
