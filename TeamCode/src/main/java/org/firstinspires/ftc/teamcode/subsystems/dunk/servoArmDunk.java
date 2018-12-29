package org.firstinspires.ftc.teamcode.subsystems.dunk;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class servoArmDunk implements Dunk{

    DcMotor hanger;
    Servo dunker;

    //DC Hanger Variables
    final int DUNK_DISTANCE = 3300;
    final double DUNK_POWER = 1.0;

    //Dunk Servo Variables
    final double DUNK_POSITION = 0.2;
    final double DOWN_POSTITION = 0.85;

    public servoArmDunk(DcMotor verticalMotion, Servo dunkArm){
        hanger = verticalMotion;
        dunker = dunkArm;
    }

    //Dunk
    @Override
    public void dunk() throws InterruptedException {

        //Use the encoder
        hanger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Reset the encoder
        hanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Use the encoder
        hanger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Set up the motor to run to the given position
        hanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set the target position as the value entered
        hanger.setTargetPosition(-DUNK_DISTANCE);

        //Turn the motor on at the corresponding power
        hanger.setPower(-DUNK_POWER);

                //Empty while loop while the motor is moving
                while ((hanger.isBusy())) {
                    dunker.setPosition(DUNK_POSITION+0.25);

                    Thread.sleep(500);

                    dunker.setPosition(DUNK_POSITION);
                }

        //Stop the motor
        hanger.setPower(0.0);

        //Use the encoder in the future
        hanger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        dunker.setPosition(DUNK_POSITION);


    }
    //Dunk return
    @Override
    public void down(){

            //Use the encoder
            hanger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Reset the encoder
            hanger.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Use the encoder
            hanger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //Set up the motor to run to the given position
            hanger.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //Set the target position as the value entered
            hanger.setTargetPosition(DUNK_DISTANCE);

            //Turn the motor on at the corresponding power
            hanger.setPower(DUNK_POWER);

            //Empty while loop while the motor is moving
            while ((hanger.isBusy())){

                dunker.setPosition(DOWN_POSTITION);
            }

            //Stop the motor
            hanger.setPower(0.0);

            //Use the encoder in the future
            hanger.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            dunker.setPosition(DOWN_POSTITION);

    }
}




