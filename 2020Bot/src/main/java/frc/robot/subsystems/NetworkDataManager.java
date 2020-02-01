/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

public class NetworkDataManager extends SubsystemBase {
  private NetworkTableEntry _targetEncoderCount;
  private NetworkTableEntry _gyroPGain;
  private NetworkTableEntry _panelDriveMotorStallSpeed;
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
    SmartDashboard.putNumber("targetEncoderCount", _targetEncoderCount.getDouble(0.0));
    SmartDashboard.putNumber("encoderCount", 0.0);
    SmartDashboard.putNumber("kP", _gyroPGain.getDouble(0.0));
    SmartDashboard.putNumber("PanelStallSpeed", _panelDriveMotorStallSpeed.getDouble(0.0));
    SmartDashboard.putString("PANEL COLOR", "------");
    
    SmartDashboard.putNumber("Blue_Rgb", _preferences.getDouble("Blue_Rgb", 0.0));
    SmartDashboard.putNumber("Blue_rGb", _preferences.getDouble("Blue_rGb", 0.0));
    SmartDashboard.putNumber("Blue_rgB", _preferences.getDouble("Blue_rgB", 0.0));
    SmartDashboard.putNumber("Green_Rgb", _preferences.getDouble("Green_Rgb", 0.0));
    SmartDashboard.putNumber("Green_rGb", _preferences.getDouble("Green_rGb", 0.0));
    SmartDashboard.putNumber("Green_rgB", _preferences.getDouble("Green_rgB", 0.0));
    SmartDashboard.putNumber("Red_Rgb", _preferences.getDouble("Red_Rgb", 0.0));
    SmartDashboard.putNumber("Red_rGb", _preferences.getDouble("Red_rGb", 0.0));
    SmartDashboard.putNumber("Red_rgB", _preferences.getDouble("Red_rgB", 0.0));
    SmartDashboard.putNumber("Yellow_Rgb", _preferences.getDouble("Yellow_Rgb", 0.0));
    SmartDashboard.putNumber("Yellow_rGb", _preferences.getDouble("Yellow_rGb", 0.0));
    SmartDashboard.putNumber("Yellow_rgB", _preferences.getDouble("Yellow_rgB", 0.0));
    SmartDashboard.putNumber("Proximity", _preferences.getDouble("Proximity", 0.0));
  }

  private void NetworkTableSetup() {
    NetworkTableInstance server = NetworkTableInstance.getDefault();
    NetworkTable table = server.getTable("SmartDashboard");

    _targetEncoderCount = table.getEntry("targetEncoderCount");
    _targetEncoderCount.setPersistent();
    _gyroPGain = table.getEntry("kP");
    _gyroPGain.setPersistent();
    _panelDriveMotorStallSpeed = table.getEntry("PanelStallSpeed");
    _panelDriveMotorStallSpeed.setPersistent();
  }

}
