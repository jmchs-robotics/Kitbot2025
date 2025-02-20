package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class ShooterSubsystem extends SubsystemBase {

    private final SparkMax shootermotor;

    public ShooterSubsystem() {

        shootermotor = new SparkMax(5, MotorType.kBrushed);

        shootermotor.configure(Configs.KitbotConfigs.shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

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