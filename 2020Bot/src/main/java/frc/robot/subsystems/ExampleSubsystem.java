/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj.Talon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ExampleSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private TalonSRX _motor;

  public ExampleSubsystem(int deviceId) {
    _motor = new TalonSRX(deviceId);
    _motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    
  }

  public void reset() {
    _motor.set(ControlMode.PercentOutput, 0.0);
  }

  public void run(double speed) {
    _motor.set(ControlMode.PercentOutput, speed);
  }
}
