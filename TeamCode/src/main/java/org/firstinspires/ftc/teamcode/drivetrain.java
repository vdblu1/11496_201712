package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Mark on 11/19/2017.
 */

class drivetrain {

    DcMotor left_drive;
    DcMotor right_drive;
    public String DrivetrainName="";
    double myPower = 0;
    boolean dtStopped = true;
    double steerSensativity = -0.5; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
    double pivotSensativity = 0.9; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
    double triggSensativity = 0; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
    boolean armControling = false;


    public void init (String name, DcMotor left_drive, DcMotor right_drive){
        DrivetrainName=name;
        this.left_drive=left_drive;
        this.right_drive=right_drive;
        this.left_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE );
        this.right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE );
    }


    public void armDrive(double pivValue, double fwdValue){//allows the arm operator fine control of the pivot
            if (pivValue!=0) {
                pivValue = pivValue / 6;
                left_drive.setPower(pivValue);
                right_drive.setPower(-pivValue);
            }else {
                    fwdValue = fwdValue / 6;
                    left_drive.setPower(fwdValue);
                    right_drive.setPower(fwdValue);
                }

    }

    public void triggerDrive201711(double direction, double forward, double reverse) {

        myPower =forward - reverse; //combine to simplify algorithm and prevent conflicting logic
        myPower = triggSensativity * (Math.pow(myPower,3)) + (1 - triggSensativity)* myPower;

        if (myPower==0 && direction==0){dtStopped=true;}//requires direction stick to be neutral for pivot to init

        if (myPower==0 && dtStopped) {//if power is zero pivot by running motors opposite direction
            //direction = pivotSensativity * (Math.pow(direction, 3)) + (1 - pivotSensativity) * direction;
            direction = direction/4;
            left_drive.setPower(direction);
            right_drive.setPower(-direction);
        }else{
            dtStopped = false;
            direction = steerSensativity * (Math.pow(direction, 3)) + (1 - steerSensativity) * direction;
            if (direction >= 0) {//On power the max turn is when one motor is off and the other is full on
                left_drive.setPower(myPower);
                right_drive.setPower(myPower * (1-direction));
            }else{
                left_drive.setPower(myPower * (direction+1));
                right_drive.setPower(myPower);
            }
        }

    }

    /*
    public void cAlgorithm() {
        //Algorithm found on the web converted from C. Not tested to work
        final float nJoyX = (gamepad1.right_stick_x);
        float nJoyY = (gamepad1.right_trigger);
        float fPivYLimit = 1;
        //  int     nMotMixL;           // Motor (left)  mixed output           (-128..+127)
        //  int     nMotMixR;           // Motor (right) mixed output           (-128..+127)
        //float Forward = gamepad1.right_stick_x;
        //float Backward = -gamepad1.right_stick_x;
        float   nMotPremixL;
        float   nMotPremixR;
        float   fPivScale;      // Balance scale b/w drive and pivot    (   0..1   )

        if (nJoyY > 0) {
            // Forward
            nMotPremixL = (nJoyX>=0)? 1 : (1 + nJoyX);
            nMotPremixR = (nJoyX>=0)? (1 - nJoyX) : 1;
        } else {
            // Reverse
            nMotPremixL = (nJoyX>=0)? (1 - nJoyX) : 1;
            nMotPremixR = (nJoyX>=0)? 1 : (1 + nJoyX);
        }

        nMotPremixL = nMotPremixL * nJoyY/1;
        nMotPremixR = nMotPremixR * nJoyY/1;
        final float nPivSpeed = nJoyX;
        fPivScale = (Math.abs(nJoyY)>fPivYLimit)? 0 : (1 - Math.abs(nJoyY)/fPivYLimit);
        final float Left = (1-fPivScale)*nMotPremixL + fPivScale*( nPivSpeed);
        final float Right = (1-fPivScale)*nMotPremixR + fPivScale*(-nPivSpeed);
        left_drive.setPower(Left);
        right_drive.setPower(Right);
    }
    */

}
