/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutoModeCommand;
import frc.robot.commands.CompoundAutoCommand;
import frc.robot.commands.ControlPanelCalibration;
import frc.robot.commands.ControlPanelCommand;
import frc.robot.commands.ControlPanelPosition;
import frc.robot.commands.TeleopCommand;
import frc.robot.subsystems.ControlPanel;
//import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GameDataManager;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.ColorTarget;

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

  private final ControlPanel _controlPanel = new ControlPanel(Constants.PWM_motor);
  private final GameDataManager _gameDataManager = new GameDataManager();

 // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final AutoModeCommand _autoCommand = new AutoModeCommand(_driveTrain, 0.4);
  private final CompoundAutoCommand _compoundAutoCommand = new CompoundAutoCommand(_driveTrain);
  private final ControlPanelCommand _controlPanelCommand = new ControlPanelCommand(_controlPanel);
  private final ControlPanelPosition _controlPanelPositionCommand = new ControlPanelPosition(_controlPanel, _gameDataManager);
  private final ControlPanelCalibration _redCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.RED);
  private final ControlPanelCalibration _greenCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.GREEN);
  private final ControlPanelCalibration _blueCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.BLUE);
  private final ControlPanelCalibration _yellowCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.YELLOW);

  private final SendableChooser<Command> _testChooser = new SendableChooser<>();
  private final SendableChooser<Command> _autoChooser = new SendableChooser<>(); 
  
  private final Joystick _stick = new Joystick(Constants.JS_port);
  private final Button _positionControlPanel = 
    new JoystickButton(_stick, Constants.JS_positionPanel).whenPressed(new ControlPanelPosition(_controlPanel, _gameDataManager));
  private final TeleopCommand _teleOpCommand = new TeleopCommand(_driveTrain, _stick, _gameDataManager);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    _autoChooser.setDefaultOption("CrossLine", _autoCommand);
    _autoChooser.addOption("Dumper", _compoundAutoCommand);

    _testChooser.setDefaultOption("RedCalibration", _redCalibrationCommand);
    _testChooser.addOption("GreenCalibration", _greenCalibrationCommand);
    _testChooser.addOption("BlueCalibration", _blueCalibrationCommand);
    _testChooser.addOption("YellowCalibration", _yellowCalibrationCommand);
    _testChooser.addOption("PanelPositioner", _controlPanelPositionCommand);
    
    SmartDashboard.putData("AutoMode", _autoChooser);
    SmartDashboard.putData("TestMode", _testChooser);
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
    return _autoChooser.getSelected();
  }

  public Command getTeleOpCommand() {
    return _teleOpCommand;  
  }
  
  public Command getTestCommand() {
    return _testChooser.getSelected();
  }
  
  public GenericHID getStick() {
    return _stick;
  }

  
}