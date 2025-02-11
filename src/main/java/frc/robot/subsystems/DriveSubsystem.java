package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class DriveSubsystem extends SubsystemBase {

    private final SparkMax left1;
    private final SparkMax right2;
    private final SparkMax left3;
    private final SparkMax right4;

    private DifferentialDrive differentialDrive;

    public DriveSubsystem() {

        left1 = new SparkMax(1, MotorType.kBrushed);
        left3 = new SparkMax(3, MotorType.kBrushed);
        right2 = new SparkMax(2, MotorType.kBrushed);
        right4 = new SparkMax(4, MotorType.kBrushed);

        left1.configure(Configs.KitbotConfigs.driveLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        left3.configure(Configs.KitbotConfigs.driveLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right2.configure(Configs.KitbotConfigs.driveRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right4.configure(Configs.KitbotConfigs.driveRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
        leftFollowerConfig.follow(left1, false);
        left3.configure(leftFollowerConfig, null, null);

        SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
        rightFollowerConfig.follow(right2, false);
        right2.configure(rightFollowerConfig, null, null);

        differentialDrive = new DifferentialDrive(left1, right2);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic() {}

    public void setLeftMotors(double speed) {
        left1.set(speed);
    }    

    public void stopLeftMotors() {
        left1.set(0);
    }

    public void setRightMotors(double speed) {
        right2.set(speed);
    }    

    public void stopRightMotors() {
        right2.set(0);
    }

    public void stopAllMotors() {
        stopRightMotors();
        stopLeftMotors();
    }

    public void setAllMotors(double speed) {
        setRightMotors(speed);
        setLeftMotors(speed);
    }

    public void arcadeDrive(double forward, double rotation) {
        differentialDrive.arcadeDrive(forward, rotation);
    }

    // public void setCoastMode() {
    //     left1.setNeutralMode(NeutralMode.Coast);
    //     right2.setNeutralMode(NeutralMode.Coast);

    //     isBrakeOn = false;
    // }

    // public void setBrakeMode() {
    //     left1.setNeutralMode(NeutralMode.Brake);
    //     right2.setNeutralMode(NeutralMode.Brake);

    //     isBrakeOn = true;
    // }

    // public boolean isBrakeOn() {
    //     return isBrakeOn;
    // }
}