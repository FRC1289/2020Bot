/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Talon;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

public class ShooterManager extends SubsystemBase {
  
  WPI_VictorSPX _leftShooterMotor;
  WPI_VictorSPX _rightShooterMotor;
  Talon _feederMotor;
  Servo _lowerBallGate;
  Servo _highBallGate;
  
  public ShooterManager() {
    _leftShooterMotor = new WPI_VictorSPX(Constants.CAN_ShooterLeftMotor);
    _rightShooterMotor = new WPI_VictorSPX(Constants.CAN_ShooterRightMotor);
    _feederMotor = new Talon(Constants.PWM_ShooterFeederMotor);
    _lowerBallGate = new Servo(Constants.PWM_LowerBallFeederGate);
    _highBallGate = new Servo(Constants.PWM_HighBallFeederGate);

    _leftShooterMotor.set(ControlMode.PercentOutput, 0);
    _rightShooterMotor.set(ControlMode.PercentOutput, 0);
    _leftShooterMotor.configFactoryDefault();
    _rightShooterMotor.configFactoryDefault();
    _rightShooterMotor.setInverted(true);
    _leftShooterMotor.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Shoot() {
    double speed = SmartDashboard.getNumber(Constants.PARAM_shooterMotorSpeed, Constants.DEFAULT_shooterMotorSpeed);
    _leftShooterMotor.set(speed);
    _rightShooterMotor.set(- speed);
    _feederMotor.set(speed);
  }

  public void reset() {
    _leftShooterMotor.set(0.0);
    _rightShooterMotor.set(0.0);
    _feederMotor.set(0.0);
  }

  public void OpenLowerGate() {
    _lowerBallGate.set(SmartDashboard.getNumber(Constants.PARAM_lowerGateServoOpened, 
                                                Constants.DEFAULT_lowerGateServoOpened));
  }

  public void CloseLowerGate(){
    _lowerBallGate.set(SmartDashboard.getNumber(Constants.PARAM_lowerGateServoOpened, 
                                                Constants.DEFAULT_lowerGateServoOpened));
  }

  public void OpenHighGate() {
    _highBallGate.set(SmartDashboard.getNumber(Constants.PARAM_highGateServoOpened, 
                                               Constants.DEFAULT_highGateServoOpened));
  }

  public void CloseHighGate(){
    _highBallGate.set(SmartDashboard.getNumber(Constants.PARAM_highGateServoClosed, 
                                               Constants.DEFAULT_highGateServoClosed));
  }
}
