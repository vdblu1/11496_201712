package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by DEC1MAP on 1/15/2018.
 */
@Autonomous(name="Red Timer", group="Competition")


public class redTimer extends Autonomous_11496 {
    String alianceColor = "red"; // "blue"
    String stoneLoc = "timer"; // "audience"

    public void runOpMode(){

        super.acceptParam(alianceColor, stoneLoc);
        super.runOpMode();

    }

}
