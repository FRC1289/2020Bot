/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.GameDataManager;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class ControlPanelPosition extends CommandBase {

  private ControlPanel _controlPanel;
  private GameDataManager _dataManager;
  private DriveTrain _driveTrain;

  public ControlPanelPosition(ControlPanel panel, DriveTrain drivetrain, GameDataManager dataManager) {
    addRequirements(panel);
    addRequirements(drivetrain);
    _controlPanel = panel;
    _dataManager = dataManager;
    _driveTrain = drivetrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _controlPanel.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _controlPanel.setMotorSpeed(SmartDashboard.getNumber(Constants.PARAM_panelRotateSpeed, Constants.DEFAULT_panelRotateSpeed));
    _driveTrain.drive(SmartDashboard.getNumber(Constants.PARAM_panelThrust, Constants.DEFAULT_panelThrust));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _controlPanel.setMotorSpeed(0.0);
    _driveTrain.reset();
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
