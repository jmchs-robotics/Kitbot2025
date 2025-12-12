package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;


public class AlgaeSubsystem extends SubsystemBase{
    
    private final TalonFX wheelMotor;

    public AlgaeSubsystem() {

        wheelMotor = MotorConstants.algaeWheelMotor;
        wheelMotor.setNeutralMode(NeutralModeValue.Brake);
        
    }

    public void stopWheelMotors() {
        wheelMotor.stopMotor();
    }

    public void setWheelMotors(double speed) {
        wheelMotor.set(speed);
    }


    
}