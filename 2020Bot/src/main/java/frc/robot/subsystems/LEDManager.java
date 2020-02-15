/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;


import frc.robot.Constants;

public class LEDManager extends SubsystemBase {
  AddressableLED _ledStrip;
  AddressableLEDBuffer _ledBuffer;

  public LEDManager() {
    _ledStrip = new AddressableLED(Constants.PWM_LEDStrip);
    _ledBuffer = new AddressableLEDBuffer(144);
    _ledStrip.setLength(_ledBuffer.getLength());
    _ledStrip.setData(_ledBuffer);
    _ledStrip.start();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    for (var i = 1; i < _ledBuffer.getLength(); i++) {
      _ledBuffer.setRGB(i, 255, 0, 0);
      _ledStrip.setData(_ledBuffer);
    }
  }
}
