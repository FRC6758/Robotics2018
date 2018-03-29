package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedMiddle extends CommandGroup{
	
	public boolean isValid = false;
	char switchPosition = '-';
	
	public TimedMiddle() {
		System.out.println("STARTING - TimedMiddle.java");
		Thread th = new Thread(new Runnable(){

			@Override
			public void run() {
				while(!isValid) {
					try {
						Thread.sleep(0);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						switchPosition = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
						isValid = true;
						contInit();
					} catch(Exception e) {
						System.out.println("Bossman - Got that game data for me?");
					}
				}
			}	
		});
		th.start();
	}
	
	public void contInit() {
		System.out.println("Found GSM: " + switchPosition);
		// These are basic and should be changed to be 99.99% accurate.
		if(switchPosition == 'L') {
			System.out.println("Executing Left Switch Position Auton - TimedMiddle.java");
			addSequential(new DriveForward(2));
			addSequential(new TurnCounter(1));
			addParallel(new DriveForward(4.5));
			addSequential(new LiftArm());
			addSequential(new ArmRelease());
		}
		else if(switchPosition == 'R') {
			System.out.println("Executing Right Switch Position Auton - TimedMiddle.java");
			addSequential(new DriveForward(2));
			addSequential(new TurnClock(1));
			addParallel(new DriveForward(4.5));
			addSequential(new LiftArm());
			addSequential(new ArmRelease());
		} else {
			System.out.print("ERROR - TimedMiddle.java");
		}
	}
}
