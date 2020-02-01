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


    // PWM Bus
    public static int PWM_ControlPanelMotor = 0;
    public static int PWM_ShooterLeftMotor = 1;
    public static int PWM_ShooterRightMotor = 2;
    public static int PWM_ShooterFeederMotor = 3;
    public static int PWM_ClimberClimbMotor = 4;
    public static int PWM_ClimberElevateMotor = 5;
    public static int PWM_LowerBallFeederGate = 6;
    public static int PWM_HighBallFeederGate = 7;
    

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
    public static int JS_RaiseClimber = 4;
    public static int JS_Climb = 5;
    public static int JS_positionPanel = 7;

    // Copilot Stick Buttons
    public static int JS_OpenGateButton = 4;
    public static int JS_shootBallsButton = 1;
    //public static int JS_CloseGateButton = 5;
    
    

   
    // String Constants
    public static String PARAM_targetEncoderCount = "targetEncoderCount";
    public static String PARAM_encoderCount = "encoderCount";
    public static String PARAM_kP = "kP";
    public static String PARAM_panelStallSpeed = "PanelStallSpeed";
    public static double PARAM_panelRotateSpeed = 0.2;
    public static double PARAM_panelThrust = 0.1;
    public static int PARAM_panelRotateCount = 300;

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


    public static double PARAM_ShooterMotorSpeed = 0.4;
    public static double PARAM_ShooterMotorStartupTime = 1.0;
    public static double PARAM_elevateSpeed = 0.4;
    public static double PARAM_climbSpeed = 0.4;

}
