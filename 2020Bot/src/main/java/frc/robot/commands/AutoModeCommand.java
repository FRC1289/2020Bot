/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class AutoModeCommand extends CommandBase {
  DriveTrain _driveTrain;
  double _targetEncoderCount;
  double _speed;

  public AutoModeCommand(DriveTrain driveTrain, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    _driveTrain = driveTrain;
    _speed = speed;
    addRequirements(_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _driveTrain.reset();
    _targetEncoderCount = SmartDashboard.getNumber(Constants.PARAM_targetEncoderCount, 0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _driveTrain.drive(_speed);
    SmartDashboard.putNumber(Constants.PARAM_encoderCount, _driveTrain.EncoderCount());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_targetEncoderCount > _driveTrain.EncoderCount()) 
      return false;
    else 
      return true;
  }
}
