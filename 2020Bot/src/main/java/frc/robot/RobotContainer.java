/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutoModeCommand;
import frc.robot.commands.CompoundAutoCommand;
import frc.robot.commands.ControlPanelCommand;
import frc.robot.commands.TeleopCommand;
import frc.robot.subsystems.ControlPanel;
//import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  // The robot's subsystems and commands are defined here...
 // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem(Constants.CAN_Testmotor);
  private final DriveTrain _driveTrain = new DriveTrain(Constants.CAN_LeftMotor,
                                                        Constants.CAN_LeftFollower,
                                                        Constants.CAN_RightMotor,
                                                        Constants.CAN_RightFollower,
                                                        Constants.DIO_leftEncoder,
                                                        Constants.DIO_rightEncoder);

  private final ControlPanel _controlPanel = new ControlPanel();

 // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final AutoModeCommand _autoCommand = new AutoModeCommand(_driveTrain, 0.4);
  private final CompoundAutoCommand _compoundAutoCommand = new CompoundAutoCommand(_driveTrain);
  private final ControlPanelCommand _controlPanelCommand = new ControlPanelCommand(_controlPanel);
  
  private final Joystick _stick = new Joystick(Constants.JS_port);
  private final TeleopCommand _teleOpCommand = new TeleopCommand(_driveTrain, _stick);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return _controlPanelCommand;
  }

  public Command getTeleOpCommand() {
    return _teleOpCommand;  
  }

  public GenericHID getStick() {
    return _stick;
  }

  
}