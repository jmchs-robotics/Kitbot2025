package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public final class Configs {

    public static final class KitbotConfigs {

        public static final SparkMaxConfig shooterConfig = new SparkMaxConfig();
        public static final SparkMaxConfig driveLeftConfig = new SparkMaxConfig();
        public static final SparkMaxConfig driveRightConfig = new SparkMaxConfig();

        
        static {

            shooterConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(20)
                .inverted(false); 
                    
            driveLeftConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(50)
                .inverted(false);
            
            driveRightConfig
                .idleMode(IdleMode.kCoast)
                .smartCurrentLimit(50)
                .inverted(true);            

        }
    }
}
