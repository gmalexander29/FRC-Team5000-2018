
package org.usfirst.frc.team5000.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    public static TalonSRX driveCimLF, driveCimLR, driveCimRF, driveCimRR;
    public static Talon rightShooter, leftShooter;
    public static Joystick driveJoystick, shooterJoystick;
    public static HHJoystickButtons driveJoystickButtons, shooterJoystickButtons;
    public static RobotDrive driveTrain, shooterTrain;
    
    static final int PICKUP_BALL_BUTTON = 1;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        driveJoystick = new Joystick(1);
        shooterJoystick = new Joystick(2);
        driveJoystickButtons = new HHJoystickButtons( driveJoystick, 10 );
        shooterJoystickButtons = new HHJoystickButtons( shooterJoystick, 10 );
        
        driveCimLF = new TalonSRX(0);
        driveCimLR = new TalonSRX (1);
        driveCimRF = new TalonSRX(2);
        driveCimRR = new TalonSRX(3);
        
        driveCimLF.setInverted(true);
        driveCimRF.setInverted(true);
        
        driveTrain = new RobotDrive(driveCimLF,driveCimLR,driveCimRF,driveCimRR);
        
        rightShooter = new Talon(4);
        leftShooter = new Talon(5);
        
        shooterTrain = new RobotDrive(leftShooter, rightShooter);
    }
    
    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
     * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
     * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
     * below the Gyro
     *
     * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
     * If using the SendableChooser make sure to add them to the chooser code above as well.
     */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	driveJoystickButtons.updateState();
    	shooterJoystickButtons.updateState();
    	driveTrain.arcadeDrive(driveJoystick);

        if( shooterJoystickButtons.getState( PICKUP_BALL_BUTTON ) == HHJoystickButtonState.Pressed ) {
            shooterTrain.drive( -0.7, 0 );
        } else {
            shooterTrain.stopMotor();
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
