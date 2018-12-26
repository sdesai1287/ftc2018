//Run from the necessary package
package org.firstinspires.ftc.teamcode.legoheads;

//Import necessary items

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@TeleOp(name="Color Sensor Test") //Name the class
public class colorSensorTest extends LinearOpMode
{
    //Define drive motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;

    //Define glyph motors
    DcMotor mineralSpool;
    DcMotor spinner;
    DcMotor hanger;

    Servo mineralFlipper;
    Servo mineralDoor;
    Servo dunker;
    CRServo pin;
    Servo markerDropper;

//    private GoldMineralDetector genericDetector = null;

    //Define possible mineral locations in enum
    enum location {
        LEFT, CENTER, RIGHT, UNKNOWN
    };

    //Create location object to store the mineral location data
    autoBox.location mineralLocation;

    //Define drive powers to avoid magic numbers
    float drivePower = (float) 0.3;
    float shiftPower = (float) 0.3;
    float turnPower = (float) 0.3;

    //***************************************************************************************************************************
    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");

        mineralSpool = hardwareMap.dcMotor.get("mineralSpool");
        spinner = hardwareMap.dcMotor.get("spinner");
        hanger = hardwareMap.dcMotor.get("hanger");

        mineralFlipper = hardwareMap.servo.get("mineralFlipper");
        mineralDoor = hardwareMap.servo.get("mineralDoor");
        dunker = hardwareMap.servo.get("dunker");
        pin = hardwareMap.crservo.get("pin");
        markerDropper = hardwareMap.servo.get("markerDropper");

        //Set up the DriveFunctions class and give it all the necessary components (motors, sensors)
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, mineralSpool, spinner, hanger, mineralFlipper, mineralDoor, dunker, pin, markerDropper);

        //Set the sensor to active mode
        //Set the directions and modes of the motors.
        functions.initializeMotorsAndSensors();

        //Set the sensor to active mode
        //Set the directions and modes of the motors.
        functions.initializeMotorsAndSensors();

        //Wait for start button to be clicked
        waitForStart();

//***********************************************************************************************************
        //LOOP BELOW
        //While the op mode is active, do anything within the loop
        //Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive())
        {
//            //Convert the color sensor values from RGB to HSV
//            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
//
//            //Display the sensor outputs on the screen
//            telemetry.addData("Clear ", colorSensor.alpha());
//            telemetry.addData("Red ", colorSensor.red());
//            telemetry.addData("Green ", colorSensor.green());
//            telemetry.addData("Blue ", colorSensor.blue());
//            telemetry.addData("Hue ", hsvValues[0]);
//            telemetry.addData("Saturation ", hsvValues[1]);
//            telemetry.addData("Value ", hsvValues[2]);
//
//            //Update the data if/when it changes
//            telemetry.update();
//
            //Always call idle() at the bottom of your while(opModeIsActive()) loop
            idle();

        } //Close "while (opModeIsActive())" loop
    } //Close main
} //Close class and end program