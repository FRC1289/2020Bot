/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN Bus
    //public static int CAN_Testmotor = 1;
    public static int CAN_LeftMotor = 1;
    public static int CAN_LeftFollower = 4;
    public static int CAN_RightMotor = 2;
    public static int CAN_RightFollower = 3;
    public static int CAN_ShooterLeftMotor = 5;
    public static int CAN_ShooterRightMotor = 6;


    // PWM Bus
    public static int PWM_ControlPanelMotor = 0;
    public static int PWM_ShooterFeederMotor = 3;
    public static int PWM_ClimberClimbMotor = 4;
    public static int PWM_ClimberElevateMotor = 5;
    public static int PWM_LowerBallFeederGate = 6;
    public static int PWM_HighBallFeederGate = 7;
    public static int PWM_LEDStrip = 1;
    

    // DIO Bus
    public static int DIO_leftEncoder = 1;
    public static int DIO_rightEncoder = 0;
    public static int DIO_ClimberElevateLimitSwitch = 2;
    public static int DIO_ClimberClimbLimitSwitch = 3;
    public static int DIO_ControlPanelEncoderChannel_A = 4;
    public static int DIO_ControlPanelEncoderChannel_B = 5;



    // Operator Interface
    public static int JS_DriverPort = 0;
    public static int JS_CopilotPort = 1;

    // Driver Stick Buttons
    public static int JS_Trigger = 1;
    public static int JS_RaiseClimber = 3;
    public static int JS_Climb = 2;

    // Copilot Stick Buttons
    public static int JS_OpenGateButton = 2;
    public static int JS_shootBallsButton = 1;
    public static int JS_rotatePanel = 3;
    public static int JS_positionPanel = 5;
    //public static int JS_CloseGateButton = 5;
    
    

   
    // String Constants
    public static String PARAM_targetEncoderCount = "targetEncoderCount";
    public static String PARAM_encoderCount = "encoderCount";
    public static String PARAM_kP = "kP";
    public static String PARAM_panelDriveMotorStallSpeed = "PanelDriveMotorStallSpeed";
    public static String PARAM_panelRotateSpeed = "PanelRotateSpeed";
    public static String PARAM_panelThrust = "PanelThrust";
    public static String PARAM_panelRotateCount = "PanelRotateCount";

    public static String PARAM_PANEL_COLOR = "PANEL COLOR";
    public static String PARAM_Blue_Rgb = "Blue_Rgb";
    public static String PARAM_Blue_rGb = "Blue_rGb";
    public static String PARAM_Blue_rgB = "Blue_rgB";

    public static String PARAM_Green_Rgb = "Green_Rgb";
    public static String PARAM_Green_rGb = "Green_rGb";
    public static String PARAM_Green_rgB = "Green_rgB";

    public static String PARAM_Red_Rgb = "Red_Rgb";
    public static String PARAM_Red_rGb = "Red_rGb";
    public static String PARAM_Red_rgB = "Red_rgB";

    public static String PARAM_Yellow_Rgb = "Yellow_Rgb";
    public static String PARAM_Yellow_rGb = "Yellow_rGb";
    public static String PARAM_Yellow_rgB = "Yellow_rgB";

    public static String PARAM_Proximity = "Proximity";

    public static String PARAM_shooterMotorSpeed = "ShooterMotorSpeed";
    public static String PARAM_shooterMotorStartupTime = "ShooterMotorStartupTime";
    public static String PARAM_elevateSpeed = "ElevateSpeed";
    public static String PARAM_climbSpeed = "ClimbSpeed";

    public static String PARAM_lowerGateServoClosed = "LowerGateServoClosed";
    public static String PARAM_lowerGateServoOpened = "LowerGateServoOpened";
    public static String PARAM_highGateServoClosed = "HighGateServoClosed";
    public static String PARAM_highGateServoOpened = "HighGateServoOpened";

    public static String PARAM_autoModeSpeed = "AutoModeSpeed";

    // default values for constants to be modified via dashboard
    public static double DEFAULT_targetEncoderCount = 35.0;
    public static double DEFAULT_kP = 0.0;
    public static double DEFAULT_autoModeSpeed = 0.4;
    public static double DEFAULT_panelDriveMotorStallSpeed = 0.2;
    public static double DEFAULT_panelThrust = 0.1;
    public static double DEFAULT_panelRotateSpeed = 0.2;
    public static double DEFAULT_panelRotateCount = 300;
    public static double DEFAULT_shooterMotorSpeed = 0.4;
    public static double DEFAULT_shooterMotorStartupTime = 1.0;
    public static double DEFAULT_elevateSpeed = 0.4;
    public static double DEFAULT_climbSpeed = 0.4;
    public static double DEFAULT_lowerGateServoClosed = 0.0;
    public static double DEFAULT_lowerGateServoOpened = 0.5;
    public static double DEFAULT_highGateServoClosed = 0.0;
    public static double DEFAULT_highGateServoOpened = 0.5;
}
