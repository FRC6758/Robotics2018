package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.OI;
import org.usfirst.frc.team6758.robot.subsystems.ThorsHammer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Hammer extends Command {

    public Hammer() {
        // Use requires() here to declare subsystem dependencies
        requires(new ThorsHammer());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ThorsHammer.thorsHammer.set(OI.stick.getX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
