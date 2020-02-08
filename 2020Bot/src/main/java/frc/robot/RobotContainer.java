/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.Constants;
import frc.robot.ColorTarget;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  // The robot's subsystems and commands are defined here...
 // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem(Constants.CAN_Testmotor);
  
  private final NetworkDataManager _dataManager = new NetworkDataManager();
  private final DriveTrain _driveTrain = new DriveTrain();
  private final ControlPanel _controlPanel = new ControlPanel();
  private final GameDataManager _gameDataManager = new GameDataManager();
  private final ShooterManager _shooterManager = new ShooterManager();
  private final ClimberManager _climberManager = new ClimberManager();
  //private final LEDManager _ledManager = new LEDManager();

  // private final ExampleCommand _exampleCommand = new ExampleCommand(1);
  private final AutoModeCommand _autoCommand = new AutoModeCommand(_driveTrain);
  private final CompoundAutoCommand _compoundAutoCommand = new CompoundAutoCommand(_driveTrain);
 // private final ControlPanelCommand _controlPanelCommand = new ControlPanelCommand(_controlPanel, _driveTrain, _dataManager);
 // private final ControlPanelPosition _controlPanelPositionCommand = new ControlPanelPosition(_controlPanel, _driveTrain, _gameDataManager);
  private final ControlPanelCalibration _redCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.RED);
  private final ControlPanelCalibration _greenCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.GREEN);
  private final ControlPanelCalibration _blueCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.BLUE);
  private final ControlPanelCalibration _yellowCalibrationCommand = new ControlPanelCalibration(_controlPanel, ColorTarget.YELLOW);

  private final SendableChooser<Command> _testChooser = new SendableChooser<>();
  private final SendableChooser<Command> _autoChooser = new SendableChooser<>(); 
  
  private final Joystick _driverStick = new Joystick(Constants.JS_DriverPort);
  private final Joystick _copilotStick = new Joystick(Constants.JS_CopilotPort);

  // Driver Buttons
  private final JoystickButton _raiseClimberButton = new JoystickButton(_driverStick, Constants.JS_RaiseClimber);
  private final JoystickButton _climberButton = new JoystickButton(_driverStick, Constants.JS_Climb);

  // Copilot Buttons
  private final JoystickButton _openGateButton = new JoystickButton(_copilotStick, Constants.JS_OpenGateButton);
 // private final JoystickButton _closeGateButton = new JoystickButton(_copilotStick, Constants.JS_CloseGateButton);
  private final JoystickButton _shootBallsButton = new JoystickButton(_copilotStick, Constants.JS_shootBallsButton);
  private final JoystickButton _rotatePanelButton = new JoystickButton(_copilotStick, Constants.JS_rotatePanel);
  private final JoystickButton _positionPanelButton = new JoystickButton(_copilotStick, Constants.JS_positionPanel);
  
 
  // private final Button _positionControlPanel = 
  //   new JoystickButton(_stick, Constants.JS_positionPanel).whenPressed(new ControlPanelPosition(_controlPanel, _driveTrain, _gameDataManager));
  private final TeleopCommand _teleOpCommand = new TeleopCommand(_driveTrain, _driverStick, _gameDataManager);


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
    //_testChooser.addOption("PanelPositioner", _controlPanelPositionCommand);
    
    SmartDashboard.putData("AutoMode", _autoChooser);
    SmartDashboard.putData("TestMode", _testChooser);
    // Configure the button bindings
    configureButtonBindings();

    CommandScheduler scheduler = CommandScheduler.getInstance();
    scheduler.setDefaultCommand(_driveTrain, _teleOpCommand);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    _openGateButton.whenHeld(new DeliverBalls(_shooterManager));
    _shootBallsButton.whenHeld(new EngageShooter(_shooterManager));
    _raiseClimberButton.whenPressed(new RaiseClimber(_climberManager));
    _climberButton.whenPressed(new Climb(_climberManager));
    _rotatePanelButton.whenHeld(new ControlPanelCommand(_controlPanel, _driveTrain));
    _positionPanelButton.whenHeld(new ControlPanelPosition(_controlPanel, _driveTrain, _gameDataManager));
/*
    //test code to figure out which button is which

    JoystickButton b1 = new JoystickButton(_driverStick, 1);
    JoystickButton b2 = new JoystickButton(_driverStick, 2);
    JoystickButton b3 = new JoystickButton(_driverStick, 3);
    JoystickButton b4 = new JoystickButton(_driverStick, 4);
    JoystickButton b5 = new JoystickButton(_driverStick, 5);
    JoystickButton b6 = new JoystickButton(_driverStick, 6);
    JoystickButton b7 = new JoystickButton(_driverStick, 7);
    JoystickButton b8 = new JoystickButton(_driverStick, 8);
    JoystickButton b9 = new JoystickButton(_driverStick, 9);
    JoystickButton b10 = new JoystickButton(_driverStick, 10);
    JoystickButton b11 = new JoystickButton(_driverStick, 11);
    JoystickButton b12 = new JoystickButton(_driverStick, 12);
  
    b1.whenPressed(new ExampleCommand(1));
    b2.whenPressed(new ExampleCommand(2));
    b3.whenPressed(new ExampleCommand(3));
    b4.whenPressed(new ExampleCommand(4));
    b5.whenPressed(new ExampleCommand(5));
    b6.whenPressed(new ExampleCommand(6));
    b7.whenPressed(new ExampleCommand(7));
    b8.whenPressed(new ExampleCommand(8));
    b9.whenPressed(new ExampleCommand(9));
    b10.whenPressed(new ExampleCommand(10));
    b11.whenPressed(new ExampleCommand(11));
    b12.whenPressed(new ExampleCommand(12));

*/
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
    return _driverStick;
  }

  
}