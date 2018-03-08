package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.OI;
import org.usfirst.frc.team6758.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperationClimb extends Command {

    public OperationClimb() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.climbArm.set(OI.controller.getX(GenericHID.Hand.kLeft));
    	Robot.climber.climbWinch.set(OI.controller.getY(GenericHID.Hand.kLeft));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.climbArm.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("FATAL ERROR, OPERATIONCLIMB.java");
    }
}
