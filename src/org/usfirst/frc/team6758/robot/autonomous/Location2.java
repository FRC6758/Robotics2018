package org.usfirst.frc.team6758.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
	
public class Location2 extends CommandGroup {

	char switchPosition = 'T';
	
    public Location2() {
    	Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while(switchPosition == 'T') {
		    		try {
						Thread.sleep(0);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		try{
		    			switchPosition = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		    		}
		    		catch(Exception e) {
		    			System.out.println("Match Hasn't Started or Game Data not Recieved");
		    			return;
		    		}
		    		if(switchPosition == 'L') {
		        		addSequential(new EncDriveForward(300));
		        		addSequential(new EncTurnCounter(/* TODO: Put in a number *cough* Quinn *cough* */500));
		        		addSequential(new EncDriveForward(500));
		        		addSequential(new EncTurnClock(500));
		        		addSequential(new EncDriveForward(500));
		        	}
		        	else if(switchPosition == 'R') {
		        		addSequential(new EncDriveForward(300));
		        		addSequential(new EncTurnClock(/* TODO: Put in a number *cough* Quinn *cough* */500));
		        		addSequential(new EncDriveForward(500));
		        		addSequential(new EncTurnCounter(500));
		        		addSequential(new EncDriveForward(500));
		        	}
		        	else {
		        		System.out.println("ERROR AUTON AT LOCATION2.java \n PLZ SEND HELP");
		        	}
		    		break;
		    	}
			}
    		
    	});
    	t.start();
    }
}
