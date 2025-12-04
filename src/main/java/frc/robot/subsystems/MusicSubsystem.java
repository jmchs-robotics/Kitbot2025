package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MusicSubsystem extends SubsystemBase{
    
    private final Orchestra orchestra;
    private final TalonFX driveMusicMotor1;
    private final TalonFX driveMusicMotor2;
    private final TalonFX driveMusicMotor3;
    private final TalonFX driveMusicMotor4;

    public MusicSubsystem() {
        
        orchestra = new Orchestra();
        driveMusicMotor1 = new TalonFX(1);
        driveMusicMotor2 = new TalonFX(2);
        driveMusicMotor3 = new TalonFX(3);
        driveMusicMotor4 = new TalonFX(4);

        orchestra.addInstrument(driveMusicMotor1);
        orchestra.addInstrument(driveMusicMotor2);
        orchestra.addInstrument(driveMusicMotor3);
        orchestra.addInstrument(driveMusicMotor4);
        
        var status = orchestra.loadMusic("pokemonThemeSong.chrp");

        if (status.isOK()) {
            orchestra.loadMusic("pokemonThemeSong.chrp");
        } else {
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
