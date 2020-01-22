/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ColorChannel;
import frc.robot.ColorTarget;
import frc.robot.subsystems.ControlPanel;

public class ControlPanelCalibration extends CommandBase {
  
  private ControlPanel _controlPanel;
  private double[] _redCalibrationValues;
  private double[] _greenCalibrationValues;
  private double[] _blueCalibrationValues;
  private int[] _proimityCalibrationValues;
  
  private int _numCalibrationValues;
  private int _index;
  private ColorTarget _targetColor;
  private Preferences _preferences;

  public ControlPanelCalibration(ControlPanel panel, ColorTarget target) {
    addRequirements(panel);
    _controlPanel = panel;
    _numCalibrationValues = 100;
    _redCalibrationValues = new double[_numCalibrationValues];
    _greenCalibrationValues = new double[_numCalibrationValues];
    _blueCalibrationValues = new double[_numCalibrationValues];
    _proimityCalibrationValues = new int[_numCalibrationValues];
    _index = 0;
    _targetColor = target;
    _preferences = Preferences.getInstance();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _index = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _redCalibrationValues[_index] = _controlPanel.getCalibrationValue(ColorChannel.RED);
    _greenCalibrationValues[_index] = _controlPanel.getCalibrationValue(ColorChannel.GREEN);
    _blueCalibrationValues[_index] = _controlPanel.getCalibrationValue(ColorChannel.BLUE);
    _proimityCalibrationValues[_index] = _controlPanel.GetProximity();
    _index++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    double redSum = 0.0;
    double greenSum = 0.0;
    double blueSum = 0.0;
    int proxSum = 0;
    double redAverage = 0.0;
    double greenAverage = 0.0;
    double blueAverage = 0.0;
    double proxAverage = 0.0;

    for (int i = 0; i < _numCalibrationValues; i++) {
      redSum += _redCalibrationValues[i];
      greenSum += _greenCalibrationValues[i];
      blueSum += _blueCalibrationValues[i];
      proxSum += _proimityCalibrationValues[i];
    }
    redAverage = redSum / _numCalibrationValues;
    greenAverage = greenSum / _numCalibrationValues;
    blueAverage = blueSum / _numCalibrationValues;
    proxAverage = proxSum / _numCalibrationValues;
    
    _preferences.putDouble("Proximity", proxAverage); 
    switch (_targetColor) {
      case RED:
      _preferences.putDouble("Red_Rgb", redAverage);
      _preferences.putDouble("Red_rGb", greenAverage);
      _preferences.putDouble("Red_rgB", blueAverage);
      break;

      case GREEN:
      _preferences.putDouble("Green_Rgb", redAverage);
      _preferences.putDouble("Green_rGb", greenAverage);
      _preferences.putDouble("Green_rgB", blueAverage);
      break;

      case BLUE:
      _preferences.putDouble("Blue_Rgb", redAverage);
      _preferences.putDouble("Blue_rGb", greenAverage);
      _preferences.putDouble("Blue_rgB", blueAverage);
      break;

      case YELLOW:
      _preferences.putDouble("Yellow_Rgb", redAverage);
      _preferences.putDouble("Yellow_rGb", greenAverage);
      _preferences.putDouble("Yellow_rgB", blueAverage);
      break;

      default:
    }
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_index < _numCalibrationValues)
      return false;
    else
      return true;
  }
}
