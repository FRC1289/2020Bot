/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class ControlPanel extends SubsystemBase {
  private ColorSensorV3 _colorSensor;
  private ColorMatch _colorMatcher;
  private Color _blue, _green, _red, _yellow;

  public ControlPanel() {
   _colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
   _colorMatcher = new ColorMatch();
   _blue = ColorMatch.makeColor(0.145, 0.440, 0.400);
   _green = ColorMatch.makeColor(0.190, 0.566, 0.245);
   _red = ColorMatch.makeColor(0.504, 0.361, 0.137);
   _yellow = ColorMatch.makeColor(0.316, 0.552, 0.132);
    _colorMatcher.addColorMatch(_blue);
    _colorMatcher.addColorMatch(_green);
    _colorMatcher.addColorMatch(_red);
    _colorMatcher.addColorMatch(_yellow);
  }

  @Override
  public void periodic() {
    
  }
  public int GetProximity() {
    return _colorSensor.getProximity(); 
  }

  public String DetectedColor() {
    Color detectedColor = _colorSensor.getColor();
    String colorString;
    ColorMatchResult match = _colorMatcher.matchClosestColor(detectedColor);

    if (match.color == _blue) {
      colorString = "Blue";
    } else if (match.color == _red) {
      colorString = "Red";
    } else if (match.color == _green) {
      colorString = "Green";
    } else if (match.color == _yellow) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    return colorString;
  }

}
