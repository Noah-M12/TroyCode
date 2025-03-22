package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;

import com.revrobotics.spark.SparkLowLevel.MotorType;


public class AlgeaIntake extends SubsystemBase{

    private final SparkFlex rotationMotor;
    private RelativeEncoder rotation_Encoder;
    private RelativeEncoder wrist_Encoder; 
    private final SparkFlex Algea_Wheel; 


    public AlgeaIntake(){
        rotationMotor = new SparkFlex(50, MotorType.kBrushless);
        Algea_Wheel = new SparkFlex(52, MotorType.kBrushless);
        rotation_Encoder = rotationMotor.getEncoder();

        rotation_Encoder.setPosition(0); 

        SmartDashboard.putNumber("Algea Rotation Encoder", rotation_Encoder.getPosition());
        
    }
    public void periodic() {
        rotation_Encoder.getPosition();
        super.periodic();
    }


    public void runWheel(double speed) {
        Algea_Wheel.set(speed); 
    }

    public void runRotation(double speed) {
        SmartDashboard.putNumber("Algea Rotation Encoder", rotation_Encoder.getPosition());
      //  if(rotation_Encoder.getPosition() >= -10 && rotation_Encoder.getPosition() <= 5) {
       //     rotationMotor.set(speed);
        //} else {
          //  rotationMotor.set(0);
       // }
        rotationMotor.set(speed);
      //  Algea_Wheel.set(speed);

    }

    public void runWrist(double speed){
        //wrist.set(speed);
        //wrist_Encoder.getPosition() < 0; 
    }

    public double wrist_getPostion(){
        SmartDashboard.putNumber("Algea Wrist Encoder", wrist_Encoder.getPosition());
    
        return wrist_Encoder.getPosition(); 
    }

    public double rotation_getPosition(){
       // SmartDashboard.putNumber("Algea Rotation Encoder", rotation_Encoder.getPosition());
        return rotation_Encoder.getPosition(); 
    }


}
