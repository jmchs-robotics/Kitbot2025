package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MusicSubsystem extends SubsystemBase{
    
    private final Orchestra orchestra;
    private final TalonFX MusicMotor1;
    private final TalonFX MusicMotor2;
    private final TalonFX MusicMotor3;
    private final TalonFX MusicMotor4;

    public MusicSubsystem() {
        
        orchestra = new Orchestra();
        MusicMotor1 = MotorConstants.driveLeft1;
        MusicMotor2 = MotorConstants.driveRight2;
        MusicMotor3 = MotorConstants.driveLeft3;
        MusicMotor4 = MotorConstants.driveRight4;

        orchestra.addInstrument(MusicMotor1);
        orchestra.addInstrument(MusicMotor2);
        orchestra.addInstrument(MusicMotor3);
        orchestra.addInstrument(MusicMotor4);
        
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
