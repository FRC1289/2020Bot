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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Servo;

public class ShooterManager extends SubsystemBase {
  
  Talon _leftShooterMotor;
  Talon _rightShooterMotor;
  Talon _feederMotor;
  Servo _lowerBallGate;
  Servo _highBallGate;
  
  public ShooterManager() {
    _leftShooterMotor = new Talon(Constants.PWM_ShooterLeftMotor);
    _rightShooterMotor = new Talon(Constants.PWM_ShooterRightMotor);
    _feederMotor = new Talon(Constants.PWM_ShooterFeederMotor);
    _lowerBallGate = new Servo(Constants.PWM_LowerBallFeederGate);
    _highBallGate = new Servo(Constants.PWM_HighBallFeederGate);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Shoot() {
    double speed = SmartDashboard.getNumber(Constants.PARAM_shooterMotorSpeed, 0.0);
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
    _lowerBallGate.set(SmartDashboard.getNumber(Constants.PARAM_lowerGateServoOpened, 0.5));
  }

  public void CloseLowerGate(){
    _lowerBallGate.set(SmartDashboard.getNumber(Constants.PARAM_lowerGateServoOpened, 0.0));
  }

  public void OpenHighGate() {
    _highBallGate.set(SmartDashboard.getNumber(Constants.PARAM_highGateServoOpened, 0.5));
  }

  public void CloseHighGate(){
    _highBallGate.set(SmartDashboard.getNumber(Constants.PARAM_highGateServoClosed, 0.0));
  }
}
