package OldAutonomous;

import static org.firstinspires.ftc.teamcode.Variables.globalTargetRotation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.DriveMethods;
import org.firstinspires.ftc.teamcode.Variables;

@Autonomous(name ="BRHigh", group = "A")
@Disabled
public class BRHigh extends DriveMethods {
    public void runOpMode() {
        globalTargetRotation = 0;
        initMotorsBlue();

        clawClamp();
        sleep(500);
        waitForStart();

        GoToHeight(300);

        driveForDistance(0.1, Variables.Direction.FORWARD,0.35, globalTargetRotation);
        driveForDistance(0.62, Variables.Direction.RIGHT,0.35, globalTargetRotation);
        driveForDistance(1.22, Variables.Direction.FORWARD,0.35, globalTargetRotation);
        driveForDistance(0.45, Variables.Direction.LEFT, 0.35, globalTargetRotation);
        goToHigh();
        driveForDistance(0.145, Variables.Direction.FORWARD,0.2, globalTargetRotation);
        sleep(1000);
        clawRelease();
        sleep(200);
        driveForDistance(0.135, Variables.Direction.BACKWARD,0.35, globalTargetRotation);
        goToDown();
        sleep(500);
        driveForDistance(0.35, Variables.Direction.RIGHT, 0.35, globalTargetRotation);
        driveForDistance(1.22, Variables.Direction.BACKWARD,0.35, globalTargetRotation);
        driveForDistance(1.55, Variables.Direction.LEFT,0.35, globalTargetRotation);

        while (opModeIsActive()) {

        }
    }
}
