package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MusicSubsystem extends SubsystemBase{
    
    private final Orchestra orchestra;
    private final TalonFX musicMotor1;
    private final TalonFX musicMotor2;
    private final TalonFX musicMotor3;
    private final TalonFX musicMotor4;
    private final TalonFX musicMotor5;
    private final TalonFX musicMotor6;

    public MusicSubsystem() {
        
        orchestra = new Orchestra();
        musicMotor1 = MotorConstants.driveLeft1;
        musicMotor2 = MotorConstants.driveRight2;
        musicMotor3 = MotorConstants.driveLeft3;
        musicMotor4 = MotorConstants.driveRight4;
        musicMotor5 = MotorConstants.CoralExtakeMotor;
        musicMotor6 = MotorConstants.algaeWheelMotor;

        orchestra.addInstrument(musicMotor1);
        orchestra.addInstrument(musicMotor2);
        orchestra.addInstrument(musicMotor3);
        orchestra.addInstrument(musicMotor4);
        orchestra.addInstrument(musicMotor5);
        orchestra.addInstrument(musicMotor6);
        
        var status = orchestra.loadMusic("YMCA.chrp");

        if (!status.isOK()) {
            DriverStation.reportWarning("orchestra status bad", false);
        }
    }

    public void startSong() {
        orchestra.play();
    }

    public void stopSong() {
        orchestra.stop();
    }

    public void pauseSong() {
        orchestra.pause();
    }

}
