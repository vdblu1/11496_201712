package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by DEC1MAP on 1/15/2018.
 */
@Autonomous(name="Red Audience", group="Competition")


public class redAudience extends Autonomous_11496 {
    String alianceColor = "red"; // "blue"
    String stoneLoc = "audience"; // "audience"

    public void runOpMode(){

        super.acceptParam(alianceColor, stoneLoc);
        super.runOpMode();

    }

}
