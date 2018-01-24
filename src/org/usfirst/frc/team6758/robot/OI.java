/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {
	//// CREATING BUTTONS
	// Defining a new joystick on port 0
	public static Joystick stick = new Joystick(0);
	
	// Defining a new Button button (Button 0) on Joystick stick
	Button button = new JoystickButton(stick, 0);
	Button grabButton = new JoystickButton(stick, 2);
	
	public OI() {
		
		if(stick.getTrigger()) {
			Pneumatics.pushBlock();
		}
		else {
			Pneumatics.retract();
		}
		
		
		
	}
}
