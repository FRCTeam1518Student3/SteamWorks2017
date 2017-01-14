// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1518.CommandBasedTest;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1518.CommandBasedTest.commands.*;

import org.usfirst.frc1518.CommandBasedTest.commands.*;
import org.usfirst.frc1518.CommandBasedTest.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autoComm1;
	Command autoComm2;
	Command autoComm3;
	Command autoMode;
	public static boolean isReversed;
	public static boolean camSelect2 = false;
	public static double leftButtCheek;
	public static double rightButtCheek;
	public static double buttChasm;
	public static double buttAverage;
	SendableChooser autoModeSelect;
	SmartDashboard dashboard;
	

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Launcher launcher;
    public static DriveTrain driveTrain;
    public static Fangs fangs;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        launcher = new Launcher();
        driveTrain = new DriveTrain();
        fangs = new Fangs();
        double servoAngle = Fangs.rightServo.getAngle();
		if (servoAngle > Fangs.rightHomePosition){
			Fangs.rightServo.setAngle(Fangs.rightHomePosition);
			}
		double servoAngle2 = Fangs.leftServo.getAngle();
		if (servoAngle2 < Fangs.leftHomePosition) {
			Fangs.leftServo.setAngle(Fangs.leftHomePosition);
		}
		isReversed = false;
		DriveTrain.drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		DriveTrain.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		DriveTrain.drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		DriveTrain.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		autoModeSelect = new SendableChooser();
		autoModeSelect.addDefault("Rough Terrain", new AutoMode1());
		autoModeSelect.addObject("Do Nothing", new AutoMode0());
		autoModeSelect.addObject("Touch", new AutoMode3());
		autoModeSelect.addObject("Spy Shoot", new AutoMode4());
		SmartDashboard.putData("Autonomous Mode Selection", autoModeSelect);
		SmartDashboard.putBoolean("Forward", false);
		
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
		autoComm1 = new AutoMode1();
		autoComm2 = new AutoMode2();
//		autoComm3 = new AutoMode3();
		//CameraServer server = CameraServer.getInstance();
		//server.startAutomaticCapture(RobotMap.webCam1);
		//server.setQuality(25);
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        //DriveTrain.drive.tankDrive(OI.leftJoystick, OI.rightJoystick);
    	//Robot.isReversed = false;
    }

    public void autonomousInit() {
        // Add selection logic to determine which auto mode to run    
    	autoMode = (Command) autoModeSelect.getSelected();
    	autoMode.start();
    	}
    

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if(autoMode == null) {
    		return;
    	}
    	else {
    		autoMode.cancel();
    	}
    	
    	
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}