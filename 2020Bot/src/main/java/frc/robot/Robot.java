/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;



//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command _teleopCommand;
  private Command _testCommand;

  private RobotContainer m_robotContainer;

  private NetworkTableEntry _targetEncoderCount;
  private NetworkTableEntry _gyroPGain;
  private Preferences _preferences;

  //WPI_TalonSRX motor;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    // motor = new WPI_TalonSRX(1);
    _preferences = Preferences.getInstance();
    NetworkTableSetup();
    CameraServer.getInstance().startAutomaticCapture();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    //motor.stopMotor();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    _teleopCommand = m_robotContainer.getTeleOpCommand();
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();  
    }

    if (_teleopCommand != null) {
      _teleopCommand.schedule();
    }
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    _testCommand = m_robotContainer.getTestCommand();
    if (_testCommand != null)
      _testCommand.schedule();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();
  }

  private void NetworkTableSetup() {
    NetworkTableInstance server = NetworkTableInstance.getDefault();
    NetworkTable table = server.getTable("SmartDashboard");

    _targetEncoderCount = table.getEntry("targetEncoderCount");
    _targetEncoderCount.setPersistent();
    _gyroPGain = table.getEntry("kP");

    SmartDashboard.putNumber("targetEncoderCount", 0.0);
    SmartDashboard.putNumber("encoderCount", 0.0);
    SmartDashboard.putNumber("kP", 0.0);
    SmartDashboard.putString("PANEL COLOR", "------");
    SmartDashboard.putNumber("Blue_Rgb", _preferences.getDouble("Blue_Rgb", 0.0));
    SmartDashboard.putNumber("Blue_rGb", _preferences.getDouble("Blue_rGb", 0.0));
    SmartDashboard.putNumber("Blue_rgB", _preferences.getDouble("Blue_rgB", 0.0));
    SmartDashboard.putNumber("Green_Rgb", _preferences.getDouble("Green_Rgb", 0.0));
    SmartDashboard.putNumber("Green_rGb", _preferences.getDouble("Green_rGb", 0.0));
    SmartDashboard.putNumber("Green_rgB", _preferences.getDouble("Green_rgB", 0.0));
    SmartDashboard.putNumber("Red_Rgb", _preferences.getDouble("Red_Rgb", 0.0));
    SmartDashboard.putNumber("Red_rGb", _preferences.getDouble("Red_rGb", 0.0));
    SmartDashboard.putNumber("Red_rgB", _preferences.getDouble("Red_rgB", 0.0));
    SmartDashboard.putNumber("Yellow_Rgb", _preferences.getDouble("Yellow_Rgb", 0.0));
    SmartDashboard.putNumber("Yellow_rGb", _preferences.getDouble("Yellow_rGb", 0.0));
    SmartDashboard.putNumber("Yellow_rgB", _preferences.getDouble("Yellow_rgB", 0.0));
    SmartDashboard.putNumber("Proximity", _preferences.getDouble("Proximity", 0.0));
  }

  
  
}
