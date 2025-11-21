package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final TalonFX shootermotor;

    public ShooterSubsystem() {

        shootermotor = new TalonFX(5);

        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
        motorConfig.CurrentLimits.StatorCurrentLimit = 50;
        motorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        
        shootermotor.getConfigurator().apply(motorConfig);

    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void setMotor(double speed) {
        shootermotor.set(speed);
    }    

    public void stopMotor() {
        shootermotor.set(0);
    }

}