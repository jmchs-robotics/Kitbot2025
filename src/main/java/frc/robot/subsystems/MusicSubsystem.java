package frc.robot.subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeConstants;

public class MusicSubsystem extends SubsystemBase{
    
    private final Orchestra orchestra;
    private final TalonFX algaeMusicMotor;

    public MusicSubsystem() {
        
        orchestra = new Orchestra();
        algaeMusicMotor = new TalonFX(AlgaeConstants.wheelMotorId);

        orchestra.addInstrument(algaeMusicMotor);
        var status = orchestra.loadMusic("");

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
