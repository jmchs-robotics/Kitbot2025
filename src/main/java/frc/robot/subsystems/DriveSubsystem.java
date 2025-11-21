package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPLTVController;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class DriveSubsystem extends SubsystemBase {

    // This is the max speed you guys set in pathplanner
    // TODO: actually measure the max speed
    private final double maxSpeedMPS = 4.5;

    private final TalonFX left1;
    private final TalonFX right2;
    private final TalonFX left3;
    private final TalonFX right4;

    private DifferentialDrive differentialDrive;

    private AHRS gyro = new AHRS(NavXComType.kMXP_SPI);

    private  DifferentialDriveOdometry m_odometry;

    private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(21.5));

    public DriveSubsystem() {

        left1 = new TalonFX(1);
        left3 = new TalonFX(3);
        right2 = new TalonFX(2);
        right4 = new TalonFX(4);

        TalonFXConfiguration motorConfigLeft = new TalonFXConfiguration();
        motorConfigLeft.CurrentLimits.StatorCurrentLimit = 50;
        motorConfigLeft.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfigLeft.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfigLeft.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

        TalonFXConfiguration motorConfigRight = new TalonFXConfiguration();
        motorConfigRight.CurrentLimits.StatorCurrentLimit = 50;
        motorConfigRight.CurrentLimits.StatorCurrentLimitEnable = true;
        motorConfigRight.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motorConfigRight.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;


        left1.getConfigurator().apply(motorConfigLeft);
        left3.getConfigurator().apply(motorConfigLeft);
        right2.getConfigurator().apply(motorConfigRight);
        right4.getConfigurator().apply(motorConfigRight);

        left3.setControl(new Follower(left1.getDeviceID(), false));
        right4.setControl(new Follower(right2.getDeviceID(), false));

        differentialDrive = new DifferentialDrive(left1::set, right2::set);

        m_odometry = new DifferentialDriveOdometry(
            new Rotation2d(gyro.getYaw()),
            left1.getPosition().getValueAsDouble(),
            right2.getPosition().getValueAsDouble(),
            new Pose2d()
        );

        // Had to set it to null initially, otherwise the AutoBuilder.config would get mad
        // That config might not have been initialized yet
        RobotConfig config = null;
        try {
            config = RobotConfig.fromGUISettings();
        } catch (Exception e) {
            // Handle exception as needed
            e.printStackTrace();
        }

        // Configure AutoBuilder last
        AutoBuilder.configure(
            this::getPose, // Robot pose supplier
            this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
            this::getRobotRelativeSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
            (speeds, feedforwards) -> driveRobotRelative(speeds), // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds. Also optionally outputs individual module feedforwards
            new PPLTVController(0.02), // PPLTVController is the built in path following controller for differential drive trains
            config, // The robot configuration
            () -> {
            // Boolean supplier that controls when the path will be mirrored for the red alliance
            // This will flip the path being followed to the red side of the field.
            // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

                var alliance = DriverStation.getAlliance();
                if (alliance.isPresent()) {
                    return alliance.get() == DriverStation.Alliance.Red;
                }
                return false;
            },
            this // Reference to this subsystem to set requirements
        );
    }

    @Override
    public void periodic() {
        m_odometry.update(
            new Rotation2d(gyro.getYaw()),
            left1.getPosition().getValueAsDouble(),
            right2.getPosition().getValueAsDouble()
        );
    }

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

    public void tankDrive(double leftVal, double rightVal) {
        setLeftMotors(leftVal);
        setRightMotors(rightVal);
    }

    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    public void resetPose(Pose2d pose) {
        m_odometry.resetPose(pose);
    }

    public ChassisSpeeds getRobotRelativeSpeeds() {
        // .get() returns a value between [-1, 1] of what the motor is currently set to
        // In theory this should work even in autonomous
        // because pathplanner does have to set the motors to a speed to actually move the robot
        DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds(
            left1.get() * maxSpeedMPS,
            right2.get() * maxSpeedMPS
        );

        return kinematics.toChassisSpeeds(wheelSpeeds);
    }

    public void driveRobotRelative(ChassisSpeeds speeds) {
        // This is how we convert the given ChassisSpeeds to wheel speeds that we can use
        // (This is why we needed the kinematics)
        DifferentialDriveWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(speeds);

        // I think the clamping between [-1, 1] is technically unnecessary
        // But I did it anyways because it makes me feel better
        // wheelSpeeds.sideMetersPerSec / maxSpeedMPS should give a value between -1 and 1 anyways
        // which we can then use in our tank drive, because it only takes in values of [-1, 1]
        double leftVal = MathUtil.clamp(wheelSpeeds.leftMetersPerSecond / maxSpeedMPS, -1, 1);
        double rightVal = MathUtil.clamp(wheelSpeeds.rightMetersPerSecond / maxSpeedMPS, -1, 1);

        tankDrive(leftVal, rightVal);
    }

}