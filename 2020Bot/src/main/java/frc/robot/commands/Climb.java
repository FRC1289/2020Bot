/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class Climb extends CommandBase {
  ClimberManager _climber;

  public Climb(ClimberManager manager) {
    _climber = manager;
    addRequirements(_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _climber.Reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (_climber.IsRaised())
      _climber.Climb();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _climber.Reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (_climber.ClimbSwitchState() || ! _climber.IsRaised())
      return true;
    else
      return false;
  }
}
