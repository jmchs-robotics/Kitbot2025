package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPLTVController;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

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
import frc.robot.Configs;

public class DriveSubsystem extends SubsystemBase {

    private final SparkMax left1;
    private final SparkMax right2;
    private final SparkMax left3;
    private final SparkMax right4;

    private DifferentialDrive differentialDrive;

    private AHRS gyro = new AHRS(NavXComType.kMXP_SPI);

    public DifferentialDriveOdometry m_odometry;

    DifferentialDriveKinematics kinematics =
        new DifferentialDriveKinematics(Units.inchesToMeters(21.5));

    public DriveSubsystem() {

        left1 = new SparkMax(1, MotorType.kBrushed);
        left3 = new SparkMax(3, MotorType.kBrushed);
        right2 = new SparkMax(2, MotorType.kBrushed);
        right4 = new SparkMax(4, MotorType.kBrushed);

        left1.configure(Configs.KitbotConfigs.driveLeftPrimaryConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        left3.configure(Configs.KitbotConfigs.driveLeftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right2.configure(Configs.KitbotConfigs.driveRightPrimaryConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        right4.configure(Configs.KitbotConfigs.driveRightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        differentialDrive = new DifferentialDrive(left1, right2);

        m_odometry = new DifferentialDriveOdometry(
            new Rotation2d(gyro.getYaw()),
            left1.getEncoder().getPosition(), right2.getEncoder().getPosition(),
            new Pose2d());

        RobotConfig config;
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
            left1.getEncoder().getPosition(), right2.getEncoder().getPosition()
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
        return ChassisSpeeds.fromRobotRelativeSpeeds(
            (double) gyro.getRobotCentricVelocityX(),
            0,
            gyro.getRate(),
            new Rotation2d(gyro.getYaw())
        );
    }

    public void driveRobotRelative(ChassisSpeeds speeds) {
        differentialDrive.
    }

}