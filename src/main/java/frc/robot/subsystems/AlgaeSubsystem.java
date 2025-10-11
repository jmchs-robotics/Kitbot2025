package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeConstants;


public class AlgaeSubsystem extends SubsystemBase{
    
    private final TalonFX wheelMotor;

    public AlgaeSubsystem() {

        wheelMotor = new TalonFX(AlgaeConstants.wheelMotorId);
        wheelMotor.setNeutralMode(NeutralModeValue.Brake);
        
    }

    public void stopWheelMotors() {
        wheelMotor.stopMotor();
    }

    public void setWheelMotors(double speed) {
        wheelMotor.set(speed);
    }


    
}
