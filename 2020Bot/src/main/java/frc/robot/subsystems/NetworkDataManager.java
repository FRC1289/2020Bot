/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

public class NetworkDataManager extends SubsystemBase {
  private NetworkTableEntry _targetEncoderCount;
  private NetworkTableEntry _gyroPGain;
  private NetworkTableEntry _panelDriveMotorStallSpeed;
  private NetworkTableEntry _panelRotateSpeed;
  private NetworkTableEntry _panelThrust;
  private NetworkTableEntry _panelRotateCount;
  private NetworkTableEntry _shooterMotorSpeed;
  private NetworkTableEntry _shooterMotorStartupTime;
  private NetworkTableEntry _elevateSpeed;
  private NetworkTableEntry _climbSpeed;
  private NetworkTableEntry _lowerGateServoClosed;
  private NetworkTableEntry _lowerGateServoOpened;
  private NetworkTableEntry _highGateServoClosed;
  private NetworkTableEntry _highGateServoOpened;
  private NetworkTableEntry _autoModeSpeed;

  private Preferences _preferences;

  public NetworkDataManager() {
    _preferences = Preferences.getInstance();
    NetworkTableSetup();
    UpdateDashboard();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void UpdateDashboard() {
    // Static values
    SmartDashboard.putNumber(Constants.PARAM_targetEncoderCount, 
                            _targetEncoderCount.getDouble(Constants.DEFAULT_targetEncoderCount));
    SmartDashboard.putNumber(Constants.PARAM_kP, 
                            _gyroPGain.getDouble(Constants.DEFAULT_kP));
    SmartDashboard.putNumber(Constants.PARAM_autoModeSpeed, 
                            _autoModeSpeed.getDouble(Constants.DEFAULT_autoModeSpeed));
    SmartDashboard.putNumber(Constants.PARAM_panelDriveMotorStallSpeed, 
                            _panelDriveMotorStallSpeed.getDouble(Constants.DEFAULT_panelDriveMotorStallSpeed));
    SmartDashboard.putNumber(Constants.PARAM_panelThrust, 
                            _panelThrust.getDouble(Constants.DEFAULT_panelThrust));
    SmartDashboard.putNumber(Constants.PARAM_panelRotateSpeed, 
                            _panelRotateSpeed.getDouble(Constants.DEFAULT_panelRotateSpeed));
    SmartDashboard.putNumber(Constants.PARAM_panelRotateCount, 
                            _panelRotateCount.getDouble(Constants.DEFAULT_panelRotateCount));
    SmartDashboard.putNumber(Constants.PARAM_shooterMotorSpeed, 
                            _shooterMotorSpeed.getDouble(Constants.DEFAULT_shooterMotorSpeed));
    SmartDashboard.putNumber(Constants.PARAM_shooterMotorStartupTime, 
                            _shooterMotorStartupTime.getDouble(Constants.DEFAULT_shooterMotorStartupTime));
    SmartDashboard.putNumber(Constants.PARAM_elevateSpeed, 
                            _elevateSpeed.getDouble(Constants.DEFAULT_elevateSpeed));
    SmartDashboard.putNumber(Constants.PARAM_climbSpeed, 
                            _climbSpeed.getDouble(Constants.DEFAULT_climbSpeed));
    SmartDashboard.putNumber(Constants.PARAM_lowerGateServoClosed, 
                            _lowerGateServoClosed.getDouble(Constants.DEFAULT_lowerGateServoClosed));
    SmartDashboard.putNumber(Constants.PARAM_lowerGateServoOpened, 
                            _lowerGateServoOpened.getDouble(Constants.DEFAULT_lowerGateServoOpened));
    SmartDashboard.putNumber(Constants.PARAM_highGateServoClosed, 
                            _highGateServoClosed.getDouble(Constants.DEFAULT_highGateServoClosed));
    SmartDashboard.putNumber(Constants.PARAM_highGateServoOpened, 
                            _highGateServoOpened.getDouble(Constants.DEFAULT_highGateServoOpened));
    
    // Calibrated values
    SmartDashboard.putNumber(Constants.PARAM_Blue_Rgb, _preferences.getDouble(Constants.PARAM_Blue_Rgb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Blue_rGb, _preferences.getDouble(Constants.PARAM_Blue_rGb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Blue_rgB, _preferences.getDouble(Constants.PARAM_Blue_rgB, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Green_Rgb, _preferences.getDouble(Constants.PARAM_Green_Rgb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Green_rGb, _preferences.getDouble(Constants.PARAM_Green_rGb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Green_rgB, _preferences.getDouble(Constants.PARAM_Green_rgB, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Red_Rgb, _preferences.getDouble(Constants.PARAM_Red_Rgb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Red_rGb, _preferences.getDouble(Constants.PARAM_Red_rGb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Red_rgB, _preferences.getDouble(Constants.PARAM_Red_rgB, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Yellow_Rgb, _preferences.getDouble(Constants.PARAM_Yellow_Rgb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Yellow_rGb, _preferences.getDouble(Constants.PARAM_Yellow_rGb, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Yellow_rgB, _preferences.getDouble(Constants.PARAM_Yellow_rgB, 0.0));
    SmartDashboard.putNumber(Constants.PARAM_Proximity, _preferences.getDouble(Constants.PARAM_Proximity, 15.0));

    // Dynamic values
    SmartDashboard.putNumber(Constants.PARAM_encoderCount, 0.0);
    SmartDashboard.putString(Constants.PARAM_PANEL_COLOR, "------");
  }

  private void NetworkTableSetup() {
    NetworkTableInstance server = NetworkTableInstance.getDefault();
    NetworkTable table = server.getTable("SmartDashboard");

    _targetEncoderCount = table.getEntry(Constants.PARAM_targetEncoderCount);
    _targetEncoderCount.setPersistent();
    _gyroPGain = table.getEntry(Constants.PARAM_kP);
    _gyroPGain.setPersistent();
    _panelDriveMotorStallSpeed = table.getEntry(Constants.PARAM_panelDriveMotorStallSpeed);
    _panelDriveMotorStallSpeed.setPersistent();
    _panelRotateSpeed = table.getEntry(Constants.PARAM_panelRotateSpeed);
    _panelRotateSpeed.setPersistent();
    _panelThrust = table.getEntry(Constants.PARAM_panelThrust);
    _panelThrust.setPersistent();
    _panelRotateCount = table.getEntry(Constants.PARAM_panelRotateCount);
    _panelRotateSpeed.setPersistent();
    _shooterMotorSpeed = table.getEntry(Constants.PARAM_shooterMotorSpeed);
    _shooterMotorSpeed.setPersistent();
    _shooterMotorStartupTime = table.getEntry(Constants.PARAM_shooterMotorStartupTime);
    _shooterMotorStartupTime.setPersistent();
    _elevateSpeed = table.getEntry(Constants.PARAM_elevateSpeed);
    _elevateSpeed.setPersistent();
    _climbSpeed = table.getEntry(Constants.PARAM_climbSpeed);
    _climbSpeed.setPersistent();
    _lowerGateServoClosed = table.getEntry(Constants.PARAM_lowerGateServoClosed);
    _lowerGateServoClosed.setPersistent();
    _lowerGateServoOpened = table.getEntry(Constants.PARAM_lowerGateServoOpened);
    _lowerGateServoOpened.setPersistent();
    _highGateServoClosed = table.getEntry(Constants.PARAM_highGateServoClosed);
    _highGateServoClosed.setPersistent();
    _highGateServoOpened = table.getEntry(Constants.PARAM_highGateServoOpened);
    _highGateServoOpened.setPersistent();
    _autoModeSpeed = table.getEntry(Constants.PARAM_autoModeSpeed);
    _autoModeSpeed.setPersistent();
  }

}
