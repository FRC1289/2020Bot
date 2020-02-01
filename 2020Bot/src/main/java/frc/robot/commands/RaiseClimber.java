/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberManager;

public class RaiseClimber extends CommandBase {
  ClimberManager _climberManager;

  public RaiseClimber(ClimberManager manager) {
    _climberManager = manager;
    addRequirements(_climberManager);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _climberManager.Reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _climberManager.RaiseClimber();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _climberManager.Reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_climberManager.ElevateSwitchState())
      return true;
    else
      return false;
  }
}
