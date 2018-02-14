package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveLeft extends Command {

	boolean flag;
	Encoder enc0;
	
    public AutoDriveLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    	this.enc0 = Robot.enc0;
    	enc0.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(enc0.getRaw() < 8000) {
    		DriveTrain.left.set(.4);
    		DriveTrain.right.set(.4);
    	}
    	else {
    		DriveTrain.left.set(0);
    		DriveTrain.right.set(0);
    		flag = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	enc0.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
