/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GameDataManager;
import frc.robot.ColorTarget;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class TeleopCommand extends CommandBase {
  DriveTrain _driveTrain;
  GenericHID _stick;
  GameDataManager _dataManager;

  public TeleopCommand(DriveTrain drivetrain, GenericHID stick, GameDataManager datamanager) {
    addRequirements(drivetrain);
    _driveTrain = drivetrain;  
    _stick = stick;
    _dataManager = datamanager;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _driveTrain.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _driveTrain.drive(_stick.getY(), _stick.getX(), _stick.getRawButton(Constants.JS_Trigger));
    if (_dataManager.targetColor() != ColorTarget.UNKNOWN)
      SmartDashboard.putString("PANEL COLOR", "KNOWN");

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
}
