/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *  INFORMATION
 * 
 *  This class is the glue that binds the controls on the physical operator
 *  interface to the commands and command groups that allow control of the robot.
 * 
 *  Last updated on 1/10/2018 by Quinn
 *  Zipse - Version 0.9.0
 */
public class OI {
	//// CREATING BUTTONS
	// Defining a new joystick on port 0
	public static Joystick stick = new Joystick(0);
	
	// Defining a new Button button (Button 0) on Joystick stick
	Button button = new JoystickButton(stick, 0);
}
