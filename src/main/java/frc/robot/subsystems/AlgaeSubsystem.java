package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AlgaeConstants;


public class AlgaeSubsystem extends SubsystemBase{
    
    private final SparkMax wheelMotor;

    public AlgaeSubsystem() {

        wheelMotor = new SparkMax(AlgaeConstants.wheelMotorId, MotorType.kBrushless);
        
    }

    public void stopWheelMotors() {
        wheelMotor.stopMotor();
    }

    public void setWheelMotors(double speed) {
        wheelMotor.set(speed);
    }


    
}
