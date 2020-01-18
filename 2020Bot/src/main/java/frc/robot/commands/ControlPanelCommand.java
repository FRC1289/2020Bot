/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class ControlPanelCommand extends CommandBase {
  
  private ControlPanel _controlPanel;
  
  public ControlPanelCommand(ControlPanel panel) {
    addRequirements(panel);
    _controlPanel = panel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int prox = _controlPanel.GetProximity();
    //System.out.println(prox);
    if (prox > 15)
      System.out.println(_controlPanel.DetectedColor());
    else
      System.out.println("Out of Range");
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
