package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public final class Configs {

    public static final class KitbotConfigs {

        public static final SparkMaxConfig shooterConfig = new SparkMaxConfig();
        public static final SparkMaxConfig driveLeftPrimaryConfig = new SparkMaxConfig();
        public static final SparkMaxConfig driveRightPrimaryConfig = new SparkMaxConfig();
        public static final SparkMaxConfig driveLeftFollowerConfig = new SparkMaxConfig();
        public static final SparkMaxConfig driveRightFollowerConfig = new SparkMaxConfig();
        
        static {

            shooterConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(20)
                .inverted(false); 
                    
            driveLeftPrimaryConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(50)
                .inverted(false);
            
            driveRightPrimaryConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(50)
                .inverted(true);

            driveLeftFollowerConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(50)
                .follow(1, false);
            
            driveRightFollowerConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(50)
                .follow(2, false);  

        }
    }
}
