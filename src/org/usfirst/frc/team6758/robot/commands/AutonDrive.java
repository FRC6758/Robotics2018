package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Encoders;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDrive extends Command {
	
	boolean flag;
	
    public AutonDrive() {
        // Use requires() here to declare subsystem dependencies
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//enc0Distance = enc0.getRaw();
    	
    	new Auton().start();
    	if() {
    		new AutoDriveLeft().start();
    		System.out.println("DONE! AUTONDRIVE!");
        	flag = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//NEEDS VARIABLES
    	return flag;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("AutonDrive Interrupted!");
    }
}
