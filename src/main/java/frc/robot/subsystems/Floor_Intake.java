package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;




public class Floor_Intake extends SubsystemBase{

    private final SparkFlex Floor_Rotation;
    private final SparkFlex Floor_Wheels; 

    public Floor_Intake(){
        Floor_Rotation = new SparkFlex(60, MotorType.kBrushless);
        Floor_Wheels= new SparkFlex(61,MotorType.kBrushless); 
        
    }

    public void run_Floor_Rotation(double speed) {
        Floor_Rotation.set(speed);
    }

    public void run_Floor_Wheels(double speed){
        Floor_Wheels.set(speed);
    }
}
