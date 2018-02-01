package org.usfirst.frc.team6758.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flywheels extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static WPI_TalonSRX flyLeft = new WPI_TalonSRX(5);
	public static WPI_TalonSRX flyRight = new WPI_TalonSRX(4);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void off() {
    	flyLeft.set(0);
    	flyRight.set(0);
    }
    public static void forward() {
    	flyLeft.set(1);
    	flyRight.set(-1);
    }
    public static void backward() {
    	flyLeft.set(1);
    	flyRight.set(-1);
    }
}

