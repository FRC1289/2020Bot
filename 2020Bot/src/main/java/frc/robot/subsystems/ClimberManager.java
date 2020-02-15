/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class ClimberManager extends SubsystemBase {
  DigitalInput _climbLimit;
  DigitalInput _elevateLimit;
  Talon _climbMotor;
  Talon _elevateMotor;
  boolean _raised;
  
  public ClimberManager() {
    _climbLimit = new DigitalInput(Constants.DIO_ClimberClimbLimitSwitch);
    _elevateLimit = new DigitalInput(Constants.DIO_ClimberElevateLimitSwitch);
    _climbMotor = new Talon(Constants.PWM_ClimberClimbMotor);
    _elevateMotor = new Talon(Constants.PWM_ClimberElevateMotor);
    _raised = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean ClimbSwitchState() {
    return _climbLimit.get();
  }

  public boolean ElevateSwitchState() {
    return _elevateLimit.get();
  }

  public void RaiseClimber() {
    _elevateMotor.set(SmartDashboard.getNumber(Constants.PARAM_elevateSpeed, 
                                                Constants.DEFAULT_elevateSpeed));
  }

  public void Climb() {
      _climbMotor.set(SmartDashboard.getNumber(Constants.PARAM_climbSpeed,
                                              Constants.DEFAULT_climbSpeed));
  }

  public void Reset() {
    _climbMotor.set(0.0);
    _elevateMotor.set(0.0);
  }

  public void SetRaiseState(boolean state) {
    _raised = state;
  }

  public boolean IsRaised() {
    return _raised;
  }
}
