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
    public static int CAN_Testmotor = 1;
    public static int CAN_LeftMotor = 1;
    public static int CAN_LeftFollower = 4;
    public static int CAN_RightMotor = 2;
    public static int CAN_RightFollower = 3;


    // PWM Bus
    public static int PWM_motor = 0;

    // DIO Bus
    public static int DIO_leftEncoder = 1;
    public static int DIO_rightEncoder = 0;


    // Operator Interface
    public static int JS_port = 1;
    public static int JS_Trigger = 1;
}
