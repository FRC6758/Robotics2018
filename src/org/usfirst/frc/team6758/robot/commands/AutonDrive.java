package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Encoders;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDrive extends Command {

	double enc0Distance, enc1Distance;
	
    public AutonDrive() {
        // Use requires() here to declare subsystem dependencies
    	requires(new Encoders());
    	requires(new DriveTrain());
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.driveTrain.arcadeDrive(.2, 0);
    	enc0Distance = Encoders.enc0.getDistance();
    	enc1Distance = Encoders.enc1.getDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if( -10000 > enc0Distance || enc0Distance > 10000) return true;
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.driveTrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	DriveTrain.driveTrain.arcadeDrive(0, 0);
    	System.out.println("AutonDrive Interrupted!");
    }
}
