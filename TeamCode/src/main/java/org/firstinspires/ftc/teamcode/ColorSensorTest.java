package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import java.lang.*;

@TeleOp(name = "ColorTest")
@Disabled
public class ColorSensorTest extends OpMode {

    ColorSensor RGB;

    public void init()
    {
            MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
            mediaPlayer.start();
            RGB = hardwareMap.colorSensor.get("RGB");
    }


    @Override
    public void loop() {


        float HValues[] = {0F,0F,0F};
        final float values[] = HValues;
        Color.RGBToHSV((RGB.red() * 255) / 800, (RGB.green() * 255) / 800, (RGB.blue() * 255) / 800, HValues);
        telemetry.addData("RightBumper Value", gamepad1.right_bumper);
        telemetry.addData("RightTrigger Value", gamepad1.right_trigger);
        telemetry.addData( "Joystick Value: ", -gamepad1.left_stick_y );
        telemetry.addData( "Joystick Value: ", -gamepad1.right_stick_y);
        telemetry.addData("Red  ", RGB.red());
        telemetry.addData("Green", RGB.green());
        telemetry.addData("Blue ", RGB.blue());
        telemetry.addData("Hue", HValues[0]);

    }
}
