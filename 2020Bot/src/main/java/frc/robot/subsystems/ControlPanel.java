/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import frc.robot.ColorChannel;
import frc.robot.ColorTarget;


public class ControlPanel extends SubsystemBase {
  private ColorSensorV3 _colorSensor;
  private ColorMatch _colorMatcher;
  private Color _blue, _green, _red, _yellow;
  private Preferences _preferences;
  private Talon _motor;

  public ControlPanel(int PWMport) {
   _motor = new Talon(PWMport);
    _colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
   _colorMatcher = new ColorMatch();
   _preferences = Preferences.getInstance();
  //  _blue = ColorMatch.makeColor(0.145, 0.440, 0.400);
  //  _green = ColorMatch.makeColor(0.190, 0.566, 0.245);
  //  _red = ColorMatch.makeColor(0.504, 0.361, 0.137);
  //  _yellow = ColorMatch.makeColor(0.316, 0.552, 0.132);
  _blue = ColorMatch.makeColor(_preferences.getDouble("Blue_Rgb", 0.0),
                               _preferences.getDouble("Blue_rGb", 0.0),
                               _preferences.getDouble("Blue_rgB", 0.0) );
  _green = ColorMatch.makeColor(_preferences.getDouble("Green_Rgb", 0.0),
                               _preferences.getDouble("Green_rGb", 0.0),
                               _preferences.getDouble("Green_rgB", 0.0) );
  _red = ColorMatch.makeColor(_preferences.getDouble("Red_Rgb", 0.0),
                               _preferences.getDouble("Red_rGb", 0.0),
                               _preferences.getDouble("Red_rgB", 0.0) );
  _yellow = ColorMatch.makeColor(_preferences.getDouble("Yellow_Rgb", 0.0),
                               _preferences.getDouble("Yellow_rGb", 0.0),
                               _preferences.getDouble("Yellow_rgB", 0.0) );
  _colorMatcher.addColorMatch(_blue);
  _colorMatcher.addColorMatch(_green);
  _colorMatcher.addColorMatch(_red);
  _colorMatcher.addColorMatch(_yellow);
  }

  public double getCalibrationValue(ColorChannel channel) {
    Color detectedColor = _colorSensor.getColor();
    double colorValue = 0.0;

    switch (channel) {
      case RED:
        colorValue = detectedColor.red;
        break;

        case GREEN:
        colorValue = detectedColor.green;
        break;

        case BLUE:
        colorValue = detectedColor.blue;
        break;

        default:
        colorValue = 0.0;
    }

    return colorValue;
  }

  @Override
  public void periodic() {
    
  }
  public int GetProximity() {
    return _colorSensor.getProximity(); 
  }

  public ColorTarget DetectedColor() {
    Color detectedColor = _colorSensor.getColor();
    ColorTarget target = ColorTarget.UNKNOWN;
    ColorMatchResult match = _colorMatcher.matchClosestColor(detectedColor);

    if (GetProximity() < (int) (SmartDashboard.getNumber("Proximity", 0.0) + 10))
      return target; // UNKNOWN
    
    if (match.color == _blue) {
      target = ColorTarget.BLUE;
    } else if (match.color == _red) {
      target = ColorTarget.RED;
    } else if (match.color == _green) {
      target = ColorTarget.GREEN;
    } else if (match.color == _yellow) {
      target = ColorTarget.YELLOW;
    } else {
      target = ColorTarget.UNKNOWN;
    }

    System.out.printf("%s\n", target.toString());
    return target;
  }

  public void setMotorSpeed(double speed) {
    _motor.set(speed);
  }

  public void reset() {
    _motor.set(0.0);
  }
}
