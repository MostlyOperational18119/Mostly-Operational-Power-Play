package TestPrograms;

import static org.firstinspires.ftc.teamcode.Variables.servoGrabberThing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.DriveMethods;

@Autonomous(name="grib", group = "a")
@Disabled
public class GrbberTest extends DriveMethods {
    Servo grabber;
    public void runOpMode(){
        // LOOK IN VARIABLES FOR GRIBBER POSISITIONS, SEE NUMBER ON GRIBBER
        double clamp = 0.0;
        double release = 0.5;
        int i = 1;
        grabber = hardwareMap.get(Servo.class, "grabber");
        waitForStart();

        while(opModeIsActive()){
            telemetry.addLine("Servo Position: "+ grabber.getPosition());
            telemetry.update();
            if (i<50){
//                grabber.setPosition(release);
//                telemetry.addLine("Release: "+ grabber.getPosition());
//                telemetry.update();
//                sleep(3000);
//                grabber.setPosition(clamp);
//                telemetry.addLine("Clamp: "+ grabber.getPosition());
//                telemetry.update();
//                sleep(3000); //0.75 release
//                This code is to locate approx location of servo location
                grabber.setPosition(1-(i*0.05));
                telemetry.addLine("Servo Position: "+ grabber.getPosition());
                telemetry.update();
                sleep(2000);
                i++;
            }

        }
    }
}
