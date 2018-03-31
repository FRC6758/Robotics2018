package org.usfirst.frc.team6758.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveStraight extends CommandGroup {

    public DriveStraight() {
        addSequential(new DriveForward(5));
    }
}
