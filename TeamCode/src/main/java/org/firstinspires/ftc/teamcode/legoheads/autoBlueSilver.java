//Run from the package
package org.firstinspires.ftc.teamcode.legoheads;

//Import necessary items

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.subsystems.sampling.GoldMineralDetector;
import org.opencv.core.Point;

@Autonomous(name="AutoWithCV") //Name the program
public class autoBlueSilver extends LinearOpMode
{
    //Define drive motors
    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;

    //Define glyph motors
    DcMotor mineralSpool;
    DcMotor spinner;
    DcMotor mineralFlipper;
    DcMotor hanger;

    CRServo pin;
    Servo markerDropper;
    Servo mineralFlipInit;

    private GoldMineralDetector genericDetector = null;

    //Define possible mineral locations in enum
    enum location {
        LEFT, CENTER, RIGHT, UNKNOWN
    };

    //Create location object to store the mineral location data
    location mineralLocation;

    //Define drive powers to avoid magic numbers
    float drivePower = (float) 0.3;
    float shiftPower = (float) 0.3;
    float turnPower = (float) 0.3;

//***************************************************************************************************************************
    //MAIN BELOW
    @Override
    public void runOpMode() throws InterruptedException
    {
        //Get references to the DC Motors from the hardware map
        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");

        mineralSpool = hardwareMap.dcMotor.get("mineralSpool");
        spinner = hardwareMap.dcMotor.get("spinner");
        mineralFlipper = hardwareMap.dcMotor.get("mineralFlipper");
        hanger = hardwareMap.dcMotor.get("hanger");

        pin = hardwareMap.crservo.get("pin");
        markerDropper = hardwareMap.servo.get("markerDropper");
        mineralFlipInit = hardwareMap.servo.get("mineralFlipInit");

        //Set up the DriveFunctions class and give it all the necessary components (motors, sensors)
        DriveFunctions functions = new DriveFunctions(leftMotorFront, rightMotorFront, leftMotorBack, rightMotorBack, mineralSpool, spinner, mineralFlipper, hanger, pin, markerDropper, mineralFlipInit);

        //Set the sensor to active mode
        //Set the directions and modes of the motors.
        functions.initializeMotorsAndSensors();

        //Vuforia Initialization
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Adp/KFX/////AAAAGYMHgTasR0y/o1XMGBLR4bwahfNzuw2DQMMYq7vh4UvYHleflzPtt5rN2kFp7NCyO6Ikkqhj/20qTYc9ex+340/hvC49r4mphdmd6lI/Ip64CbMTB8Vo53jBHlGMkGr0xq/+C0SKL1hRXj5EkXtSe6q9F9T/nAIcg9Jr+OfAcifXPH9UJYG8WmbLlvpqN+QuVA5KQ6ve1USpxYhcimV9xWCBrq5hFk1hGLbeveHrKDG3wYRdwBeYv3Yo5qYTsotfB4CgJT9CX/fDR/0JUL7tE29d1v1eEF/VXCgQP4EPUoDNBtNE6jpKJhtQ8HJ2KjmJnW55f9OqNc6SsULV3bkQ52PY+lPLt1y4muyMrixCT7Lu";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        genericDetector = new GoldMineralDetector();
        genericDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        genericDetector.colorFilter = new LeviColorFilter(LeviColorFilter.ColorPreset.YELLOW);

        genericDetector.enable();

        telemetry.addData("Status", "Initialized.");

        Point blockLocation = null;
        //Set the sensor to active mode
        //Set the directions and modes of the motors.
        functions.initializeMotorsAndSensors();

        //Wait for start button to be clicked
        waitForStart();

//***************************************************************************************************************************
        while (opModeIsActive())
        {
//            functions.dropDown();
                telemetry.addData("Location", genericDetector.getScreenPosition());
                //telemetry.addData("Rect", genericDetector.getRect().toString());
                blockLocation = genericDetector.getScreenPosition();
                if (blockLocation != null)
                {
                    float power = (float) 0.3;
                    int startDistance = 800;
                    int distanceToBlock = 1100;
                    int turnDistance = 1400;

                    int count = 0;
                    DriveFunctions.location goldLocation;
                    while (((blockLocation.y < 30.0) || (blockLocation.y > 500.0)) && (count < 300))
                    {
                        sleep(10);
                        count++;
                    }


                    if (blockLocation.y < 120)
                    {
                        goldLocation = DriveFunctions.location.RIGHT;
                    }
                    else if (blockLocation.y < 320)
                    {
                        goldLocation = DriveFunctions.location.CENTER;
                    }
                    else if (blockLocation.y < 520)
                    {
                        goldLocation = DriveFunctions.location.LEFT;
                    }
                    else
                    {
                        goldLocation = DriveFunctions.location.CENTER;
                    }


                    if (goldLocation == DriveFunctions.location.RIGHT)
                    {
                        functions.driveAutonomous(- power, - startDistance);
                    }
                    if (goldLocation == DriveFunctions.location.LEFT)
                    {
                        functions.driveAutonomous(power, startDistance);
                    }

                    functions.rightTurnAutonomous(power, turnDistance);

                    functions.driveAutonomous(power, distanceToBlock);
                }

//            functions.rightTurnAutonomous(turnPower, 1450);
//
//            functions.driveAutonomous(-drivePower, -200);
//
//            functions.leftTurnAutonomous(turnPower, 720);
//

            //Always call idle() at the bottom of your while(opModeIsActive()) loop
            idle();

            break;
        }//Close while opModeIsActive loop
    } //Close "run Opmode" loop
} //Close class and end program