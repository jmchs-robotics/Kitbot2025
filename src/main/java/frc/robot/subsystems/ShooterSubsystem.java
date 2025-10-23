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

        // make a new shootermotor that is a sparkmax for 5 and kBrushed
        // make the configs for it
        

    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void setMotor(double speed) {
        // set the speed of the motor
    }    

    public void stopMotor() {
        // stop the motor
    }

}
