package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.commands.Hammer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ThorsHammer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	double angle = 0;
	boolean active = false;
	
	public static WPI_TalonSRX thorsHammer = new WPI_TalonSRX(6);
	public static DigitalInput limitSwitch0, limitSwitch180;
	//public static Encoder thorsEnc = new Encoder(null, null);

    public void initDefaultCommand() {
        //setDefaultCommand(new Hammer());
    }
    
//    public void degree0() {
//    	while(active){
//    		if(angle > 0 && limitSwitch0.get() == false) {
//    			thorsHammer.set(1);
//    		}
//    	}
//    }
//    
//    public void degree45() {
//    	while(active) {
//    		if(angle > 46) {
//    			thorsHammer.set(1);
//    		}
//    		else if(angle < 44) {
//    			thorsHammer.set(-1);
//    		}
//    	}
//    }
//    
//    public void degree90() {
//    	while(active) {
//    		if(angle > 91) {
//    			thorsHammer.set(1);
//    		}
//    		else if(angle < 89) {
//    			thorsHammer.set(-1);
//    		}
//    	}
//    }
//    
//    public void degree135() {
//    	while(active) {
//    		if(angle > 136) {
//    			thorsHammer.set(1);
//    		}
//    		else if(angle < 134) {
//    			thorsHammer.set(-1);
//    		}
//    	}
//    }
//    public void degree180() {
//    	while(active) {
//    		if(angle < 180 && limitSwitch180.get() == false) {
//    			thorsHammer.set(-1);
//    		}
//    	}
//    }
}

