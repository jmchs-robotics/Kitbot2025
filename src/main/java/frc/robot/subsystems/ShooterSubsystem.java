package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final WPI_TalonSRX shootermotor;

    private boolean isBrakeOn = false;

    public ShooterSubsystem() {

        shootermotor = new WPI_TalonSRX(5);

        shootermotor.setInverted(false);

        shootermotor.setNeutralMode(NeutralMode.Brake);
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

    public void setCoastMode() {
        shootermotor.setNeutralMode(NeutralMode.Coast);

        isBrakeOn = false;
    }

    public void setBrakeMode() {
        shootermotor.setNeutralMode(NeutralMode.Brake);

        isBrakeOn = true;
    }

    public boolean isBrakeOn() {
        return isBrakeOn;
    }
}