/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.Constants;

public class LEDManager extends SubsystemBase {
  Spark _ledStrip;

  public LEDManager() {
    _ledStrip = new Spark(Constants.PWM_LEDStrip);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    for (double i = -1.0; i < 1.0; i+=0.01)
      _ledStrip.set(i);
  }
}
