package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.OI;
import org.usfirst.frc.team6758.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperationPneumatics extends Command {

    public OperationPneumatics() {
    	requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.stick.getRawButton(2) || OI.controller.getAButton()) Robot.pneumatics.kick();
		else Robot.pneumatics.retract();
		
		if(OI.stick.getTrigger() || OI.controller.getBumper(GenericHID.Hand.kRight)) Robot.pneumatics.releaseBox();
		else Robot.pneumatics.clampBox();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	System.out.println("FATAL ERROR - OperationPneumatics.java \nHELP ME!");
    }
}
