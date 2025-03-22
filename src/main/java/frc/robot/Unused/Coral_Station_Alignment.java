// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Unused;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.DriveSubsystem;

/** An example command that uses an example subsystem. */
public class Coral_Station_Alignment extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_drive;
  private double align_kP = .0005; 
  private double targetingForwardSpeed = .008; 
  private double drive_kP = .05; 
  private double targetAngularVelocity; 
  private String Limelight; 
  private final Supplier<Double> m_ySpeedFunction;
  //private final Supplier<Boolean> m_fieldRelativeFunction, m_rateLimitFunction;
  

  /**
   * Creates a new ExampleCommand.
   
   * @param subsystem The subsystem used by this command.
   */
  //Supplier<Double> ySpeedFunction, Supplier<Double> rotFunction, Supplier<Boolean> fieldRelativeFunction, Supplier<Boolean> rateLimitFunction
  public Coral_Station_Alignment(DriveSubsystem driveSubsystem, Supplier<Double> ySpeedFunction, String Limelight) {

    m_drive = driveSubsystem;
    m_ySpeedFunction = ySpeedFunction;
    Limelight = Limelight;

   // m_drive.setIntakeMode("VisionTurn");

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //Rotation 
    targetAngularVelocity = LimelightHelpers.getTX("limelight-llthree") * align_kP;  //limelight-llthree or lltwo
    targetAngularVelocity *= Constants.DriveConstants.kMaxAngularSpeed; 
    targetAngularVelocity *= -1.0; 
    SmartDashboard.putNumber("targetAngularVelocity", targetAngularVelocity); 

    //Drive YOU WOULD USE SOMETHING LIKE THIS  IF YOU NEED TO GET INTO RANGE OF THE OBJECT BUT SINCE FOR 2024 ALL WE HAD TO DO WAS RUN OVER IT SO I JUST SET THE COMMAND TO MOVE AT A CONSTANT RATE 
    //targetingForwardSpeed = LimelightHelpers.getTY("limelight-lltwo") * drive_kP;
    //targetingForwardSpeed *= Constants.DriveConstants.kMaxSpeedMetersPerSecond;
    //targetingForwardSpeed *= -1.0;
    //SmartDashboard.putNumber("targetingForwardSpeed", targetingForwardSpeed); 
    //SmartDashboard.putString("VisionTurnwithMove's ySpeedFunction = 0", "Zero"); 

    //Turn off fieldRelative when using Limelight to track 
    //m_ySpeedFunction.get() *.25
    
    m_drive.drive(.2, targetAngularVelocity * 6, targetAngularVelocity,  true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
