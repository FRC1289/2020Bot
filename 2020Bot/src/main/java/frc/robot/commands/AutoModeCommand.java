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
  int _targetEncoderCount;

  public AutoModeCommand(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    _driveTrain = driveTrain;
    addRequirements(_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.printf("auto cmd init\n");
    _driveTrain.reset();
    _targetEncoderCount = (int) SmartDashboard.getNumber(Constants.PARAM_targetEncoderCount, 0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   // System.out.printf("auto cmd exec\n");
    _driveTrain.drive(SmartDashboard.getNumber(Constants.PARAM_autoModeSpeed, 0.4));
    SmartDashboard.putNumber(Constants.PARAM_encoderCount, _driveTrain.EncoderCount());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // System.out.printf("auto cmd end %s\n", interrupted ? "T" : "F");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.printf("auto cmd isfin\n");
    int currentCount = _driveTrain.EncoderCount();
    //System.out.printf("%d %d\n", _targetEncoderCount, currentCount);
    if (currentCount <= _targetEncoderCount) 
    {
     // System.out.printf("Not Done %d %d\n", _targetEncoderCount, currentCount);
      return false;
    }
    else
    { 
      //System.out.printf("Done %d %d\n", _targetEncoderCount, currentCount);
      return true;
    }
  }
}
