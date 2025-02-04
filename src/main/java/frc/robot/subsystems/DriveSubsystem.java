package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    private final WPI_TalonSRX left1;
    private final WPI_TalonSRX right2;
    private final WPI_TalonSRX left3;
    private final WPI_TalonSRX right4;

    private DifferentialDrive differentialDrive;

    private boolean isBrakeOn = false;

    public DriveSubsystem() {

        left1 = new WPI_TalonSRX(1);
        left3 = new WPI_TalonSRX(3);
        right2 = new WPI_TalonSRX(2);
        right4 = new WPI_TalonSRX(4);

        left1.setInverted(true);
        left3.setInverted(true);
        right2.setInverted(false);
        right4.setInverted(false);

        left1.setNeutralMode(NeutralMode.Coast);
        left3.setNeutralMode(NeutralMode.Coast);
        right2.setNeutralMode(NeutralMode.Coast);
        right4.setNeutralMode(NeutralMode.Coast);

        left3.follow(left1);
        right4.follow(right2);

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

    public void setCoastMode() {
        left1.setNeutralMode(NeutralMode.Coast);
        right2.setNeutralMode(NeutralMode.Coast);

        isBrakeOn = false;
    }

    public void setBrakeMode() {
        left1.setNeutralMode(NeutralMode.Brake);
        right2.setNeutralMode(NeutralMode.Brake);

        isBrakeOn = true;
    }

    public boolean isBrakeOn() {
        return isBrakeOn;
    }
}