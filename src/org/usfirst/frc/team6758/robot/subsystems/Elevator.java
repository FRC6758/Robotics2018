package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.commands.StopLift;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(4);
	
	public DigitalInput topLimit = new DigitalInput(4);
	public DigitalInput bottomLimit = new DigitalInput(5);
	
	public Elevator() {
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new StopLift());
	}

	public void stop() {
		elevatorMotor.set(0);
	}
	
}
