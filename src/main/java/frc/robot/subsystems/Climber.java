package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;




public class Climber extends SubsystemBase{

    private final SparkFlex Climber;
    private RelativeEncoder Encoder; 

    public Climber(){
        Climber = new SparkFlex(62, MotorType.kBrushless);
        Encoder = Climber.getEncoder(); 
        
    }

    public void runClimber(double speed) {
        Climber.set(speed);
    }
    public double getPostion(){
        SmartDashboard.getNumber("Climber Encoder", Encoder.getPosition()); 
        return Encoder.getPosition();
    }
}
