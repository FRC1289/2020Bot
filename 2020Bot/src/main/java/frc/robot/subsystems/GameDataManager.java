/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorTarget;
import edu.wpi.first.wpilibj.DriverStation;

public class GameDataManager extends SubsystemBase {
  ColorTarget _targetColor;

  public GameDataManager() {
    _targetColor = ColorTarget.UNKNOWN;
  }

  @Override
  public void periodic() {
    _targetColor = GetFieldData();
    // This method will be called once per scheduler run
  }

  private ColorTarget GetFieldData() {
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    ColorTarget target = ColorTarget.UNKNOWN;

    if(gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B' :
        target = ColorTarget.RED;
        break;
      case 'G' :
        target = ColorTarget.YELLOW;
      break;
      case 'R' :
        target = ColorTarget.BLUE;
        break;
      case 'Y' :
        target = ColorTarget.GREEN;
        break;
      default :
        target = ColorTarget.UNKNOWN;
      break;
      }
    } 
    return target;
  }

  public ColorTarget targetColor() {
    return _targetColor;
  }
}
