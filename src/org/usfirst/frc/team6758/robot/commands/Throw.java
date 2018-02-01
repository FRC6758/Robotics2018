package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.OI;
import org.usfirst.frc.team6758.robot.subsystems.Flywheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Throw extends Command {

    public Throw() {
        // Use requires() here to declare subsystem dependencies
        requires(new Flywheels());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Flywheels.forward();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(OI.stick.getThrottle() > 0) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Flywheels.off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Flywheels.off();
    }
}
