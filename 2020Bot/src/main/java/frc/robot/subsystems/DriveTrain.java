/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  
  private WPI_TalonSRX _leftMotor;
  private WPI_TalonSRX _leftFollower;
  private WPI_TalonSRX _rightMotor;
  private WPI_TalonSRX _rightFollower;
  private DifferentialDrive _driveTrain;
  private Counter _leftEncoder;
  private Counter _rightEncoder;
  private ADXRS450_Gyro _gyro;
 

  public DriveTrain() {
    _leftMotor = new WPI_TalonSRX(Constants.CAN_LeftMotor);
    _leftFollower = new WPI_TalonSRX(Constants.CAN_LeftFollower);
    _rightMotor = new WPI_TalonSRX(Constants.CAN_RightMotor);
    _rightFollower = new WPI_TalonSRX(Constants.CAN_RightFollower);

    initDriveTrain();
    _driveTrain = new DifferentialDrive(_leftMotor, _rightMotor);
    /*
     * WPI drivetrain classes defaultly assume left and right are opposite. call
     * this so we can apply + to both sides when moving forward. DO NOT CHANGE
     */
     _driveTrain.setRightSideInverted(false);

     _leftEncoder = new Counter(Constants.DIO_leftEncoder);
     _rightEncoder = new Counter(Constants.DIO_rightEncoder);

     _gyro = new ADXRS450_Gyro();     
     _gyro.calibrate();
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
    _driveTrain.feedWatchdog();
  }

  public void reset() {
    _driveTrain.stopMotor();
    _leftEncoder.reset();
    _rightEncoder.reset();
    _gyro.reset();
  }

  public void drive(double throttle, double rotation, boolean triggerState) {
    if (triggerState)
      throttle = throttle / 2.0;
    _driveTrain.curvatureDrive(throttle, rotation, triggerState);
  }

  public void drive(double throttle) {
    // double kp = SmartDashboard.getNumber(Constants.PARAM_kP, Constants.DEFAULT_kP);
    // double turn = - _gyro.getRate();
    // System.out.println(turn);
    // _driveTrain.arcadeDrive(throttle + turn * kp , - turn * kp );
    _driveTrain.arcadeDrive(throttle, 0.0);
  }

  public int EncoderCount() {
    return (int) ((_leftEncoder.get() + _rightEncoder.get()) / 2.0);
  }
}
