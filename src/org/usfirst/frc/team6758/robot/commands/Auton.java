package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auton extends Command {
	
	Encoder enc0;
	static boolean flag;
	
    public Auton() {
        // Use requires() here to declare subsystem dependencies
        //requires(new DriveTrain());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    	this.enc0 = Robot.enc0;
    	enc0.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	System.out.println(enc0.getRaw());
    	
    	if(enc0.getRaw() < 39500) {
    	DriveTrain.driveTrain.arcadeDrive(.45,0);
    	}
    	else {
    		flag = true;
    		DriveTrain.driveTrain.arcadeDrive(0,  0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.enc0.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
