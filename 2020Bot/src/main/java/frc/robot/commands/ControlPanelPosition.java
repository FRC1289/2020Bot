/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.GameDataManager;

public class ControlPanelPosition extends CommandBase {

  private ControlPanel _controlPanel;
  private GameDataManager _dataManager;

  public ControlPanelPosition(ControlPanel panel, GameDataManager dataManager) {
    addRequirements(panel);
    _controlPanel = panel;
    _dataManager = dataManager;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _controlPanel.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _controlPanel.setMotorSpeed(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _controlPanel.setMotorSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (_controlPanel.DetectedColor() == _dataManager.targetColor())
      return true;
    else
      return false;
  }
}
