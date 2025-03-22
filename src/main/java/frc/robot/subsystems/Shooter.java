package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkFlex;

import com.revrobotics.spark.SparkLowLevel.MotorType;


public class Shooter extends SubsystemBase{

    private final SparkFlex Big_Wheel_Shooter;
    private final SparkFlex Small_Wheel_Shooter; 

    public Shooter(){
        Big_Wheel_Shooter = new SparkFlex(41, MotorType.kBrushless);
        Small_Wheel_Shooter = new SparkFlex(40, MotorType.kBrushless);
    }

    public void runShooter(double small_Speed, double big_Speed) {
        Small_Wheel_Shooter.set(small_Speed);
        Big_Wheel_Shooter.set(big_Speed);
    }

    public void placeCoral(double bigSpeed, double smallSpeed) {
        Small_Wheel_Shooter.set(smallSpeed);
        Big_Wheel_Shooter.set(bigSpeed);
    }

}
