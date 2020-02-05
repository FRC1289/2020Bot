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
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NetworkDataManager;
import frc.robot.Constants;

public class ControlPanelCommand extends CommandBase {
  
  private ControlPanel _controlPanel;
  private DriveTrain _driveTrain;
  private NetworkDataManager _dataManager;
  
  public ControlPanelCommand(ControlPanel panel, DriveTrain drivetrain, NetworkDataManager datamanager) {
    addRequirements(panel);
    addRequirements(drivetrain);
    _controlPanel = panel;
    _driveTrain = drivetrain;
    _dataManager = datamanager;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _controlPanel.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _controlPanel.setMotorSpeed(SmartDashboard.getNumber(Constants.PARAM_panelRotateSpeed, 0.4));
    _driveTrain.drive(SmartDashboard.getNumber(Constants.PARAM_panelThrust, 0.1));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _controlPanel.reset();
    _driveTrain.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_controlPanel.GetEncoderCount() > (int) SmartDashboard.getNumber(Constants.PARAM_panelRotateCount, 200))
      return true;
    else
      return false;
  }
}
