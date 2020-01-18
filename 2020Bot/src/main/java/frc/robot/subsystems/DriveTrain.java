/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveTrain extends SubsystemBase {
  
  private WPI_TalonSRX _leftMotor;
  private WPI_TalonSRX _leftFollower;
  private WPI_TalonSRX _rightMotor;
  private WPI_TalonSRX _rightFollower;
  private DifferentialDrive _driveTrain;
  private Counter _leftEncoder;
  private Counter _rightEncoder;
  private ADXRS450_Gyro _gyro;

  public DriveTrain(int leftMotorID, int leftFollowerID, int rightMotorID, int rightFollowerID,
                    int leftEncoderID, int rightEncoderID) {
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

     _leftEncoder = new Counter(leftEncoderID);
     _rightEncoder = new Counter(rightEncoderID);

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
    _driveTrain.curvatureDrive(throttle, rotation, triggerState);
  }

  public void drive(double throttle) {
    double kp = SmartDashboard.getNumber("kP", 0.0);
    double turn = - _gyro.getRate();
    System.out.println(turn);
    _driveTrain.arcadeDrive(throttle + turn * kp , - turn * kp );
    //_driveTrain.arcadeDrive(throttle, 0.0);
  }

  public double EncoderCount() {
    return (_leftEncoder.get() + _rightEncoder.get()) / 2.0;
  }
}
