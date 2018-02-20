package org.usfirst.frc.team6758.robot.commands;

import org.usfirst.frc.team6758.robot.OI;
import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperationElevator extends Command {

	protected int pov;
	
    public OperationElevator() {
        requires(Robot.elevator);
    }

    protected void initialize() {  }

    protected void execute() {
		pov = OI.controller.getPOV();
		
		System.out.println(pov);
		
		if(pov > 85 && pov < 95 && Robot.elevator.topLimit.get()) Robot.elevator.elevatorMotor.set(RobotMap.elevatorSpeed);
		else if(pov > 265 && pov < 275 && Robot.elevator.bottomLimit.get()) Robot.elevator.elevatorMotor.set(-RobotMap.elevatorSpeed);
		else if(OI.stick.getRawButton(6)) Robot.elevator.elevatorMotor.set(RobotMap.elevatorSpeed);
		else if(OI.stick.getRawButton(4)) Robot.elevator.elevatorMotor.set(-RobotMap.elevatorSpeed);
		else Robot.elevator.stop();
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() { }

    protected void interrupted() {
    	System.out.println("FATAL ERROR - OperationElevator.java \nHELP ME!");
    }
}
