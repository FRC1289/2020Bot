/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveTrain extends SubsystemBase {
  
  private WPI_TalonSRX _leftMotor;
  private WPI_TalonSRX _leftFollower;
  private WPI_TalonSRX _rightMotor;
  private WPI_TalonSRX _rightFollower;
  private DifferentialDrive _driveTrain;

  public DriveTrain(int leftMotorID, int leftFollowerID, int rightMotorID, int rightFollowerID) {
    _leftMotor = new WPI_TalonSRX(leftMotorID);
    _leftFollower = new WPI_TalonSRX(leftFollowerID);
    _rightMotor = new WPI_TalonSRX(rightMotorID);
    _rightFollower = new WPI_TalonSRX(rightFollowerID);

    initDriveTrain();
    _driveTrain = new DifferentialDrive(_leftMotor, _rightMotor);
    /*
     * WPI drivetrain classes defaultly assume left and right are opposite. call
     * this so we can apply + to both sides when moving forward. DO NOT CHANGE
     */
     _driveTrain.setRightSideInverted(false);
  }

  private void initDriveTrain() {
    _leftMotor.set(ControlMode.PercentOutput, 0);
    _rightMotor.set(ControlMode.PercentOutput, 0);
    /* factory default values */
    _rightMotor.configFactoryDefault();
    _rightFollower.configFactoryDefault();
    _leftMotor.configFactoryDefault();
    _leftFollower.configFactoryDefault();
    
    /* set up followers */
    _rightFollower.follow(_rightMotor);
    _leftFollower.follow(_leftMotor);
    
    /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
    _rightMotor.setInverted(true); // !< Update this
    _leftMotor.setInverted(false); // !< Update this
    
    /*
     * set the invert of the followers to match their respective master controllers
     */
    _rightFollower.setInverted(InvertType.FollowMaster);
    _leftFollower.setInverted(InvertType.FollowMaster);
    
    /*
     * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
     */
    _rightMotor.setSensorPhase(true);
    _leftMotor.setSensorPhase(true);    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void reset() {
    _driveTrain.stopMotor();
  }

  public void drive(double throttle, double rotation, boolean triggerState) {
    _driveTrain.curvatureDrive(throttle, rotation, triggerState);
    
  }

}
