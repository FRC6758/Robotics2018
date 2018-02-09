package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	double time;
	
    public DriveForward(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(time > 0) setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.driveTrain.arcadeDrive(.2, 0);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
