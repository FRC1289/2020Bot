/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.ShooterManager;
import frc.robot.Constants;

public class EngageShooter extends CommandBase {
  
  ShooterManager _shooterManager;
  Timer _timer;

  public EngageShooter(ShooterManager manager) {
    _shooterManager = manager;
    addRequirements(_shooterManager);
    _timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _timer.reset();
    _timer.start();
    _shooterManager.Shoot();
    while (_timer.get() < Constants.PARAM_ShooterMotorStartupTime) {}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _shooterManager.OpenHighGate();
    _shooterManager.Shoot();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _shooterManager.CloseHighGate();
    _shooterManager.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
