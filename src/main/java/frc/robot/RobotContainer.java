// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.Unused.Coral_Station_Alignment;
import frc.robot.Unused.Reef_Alignment;
import frc.robot.Unused.Run_Algea_Wrist;
import frc.robot.Unused.Run_Climber;
import frc.robot.Unused.Stop_Auto_Intake;
import frc.robot.Unused.Timer_Run_Shooter;
import frc.robot.commands.Intake_Lift;
import frc.robot.commands.PrintTelemetry;
import frc.robot.commands.Run_Algea_Rotation;
import frc.robot.commands.Run_Algea_Wheel;
import frc.robot.commands.Run_Floor_Rotation;
import frc.robot.commands.Run_Floor_Wheel;
import frc.robot.commands.Run_Shooter;
import frc.robot.commands.Run_Shooter_Auto;
import frc.robot.commands.SlowDrive;
import frc.robot.subsystems.AlgeaIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Pneunamatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Floor_Intake;
import frc.robot.commands.DriveTeleop;
import frc.robot.commands.ResetGyro;


/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems //////////////////////////////////////////////////////
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Pneunamatics Pneunamatics = new Pneunamatics();
  private final Shooter shooter = new Shooter(); 
  private final AlgeaIntake algeaIntake = new AlgeaIntake();
  private final Climber Climber = new Climber(); 
  private final Floor_Intake floor_Intake = new Floor_Intake(); 
  //private final CameraServer cameraServer = new camer

  // The driver's controller
  private final CommandXboxController m_driverController =
  new CommandXboxController(0);
  private final CommandXboxController m_auxController = new CommandXboxController(1); 

private final SendableChooser<Command> autoChooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Register Named Commands
    NamedCommands.registerCommand("Lift Intake", new Intake_Lift(Pneunamatics));
    NamedCommands.registerCommand("Score", new Run_Shooter_Auto(shooter, -0.25, -0.15).withTimeout(1));
    NamedCommands.registerCommand("Intake Coral", new Run_Shooter_Auto(shooter, 0,-0.2).withTimeout(1));
   // NamedCommands.registerCommand("Timed Intake Coral", new Timer_Run_Shooter(shooter, 0,-0.2));
    //NamedCommands.registerCommand("Stop Intake Coral", new Stop_Auto_Intake(shooter));

    //NamedCommands.registerCommand("Get to Reef", new DriveTeleop(m_robotDrive, () -> 0.1, () -> 0.0, () -> 0.0, false)); 
    //NamedCommands.registerCommand("Reef Alignment",));

    configureButtonBindings();

    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true),
            m_robotDrive)); 
            
    CameraServer.startAutomaticCapture();

    // Build an auto chooser. This will use Commands.none() as the default option.
    autoChooser = AutoBuilder.buildAutoChooser();

    // Another option that allows you to specify the default auto by its name
    // autoChooser = AutoBuilder.buildAutoChooser("My Default Auto");

    SmartDashboard.putData("Auto Chooser", autoChooser);
    //SmartDashboard.putBoolean("Compressor On", true); // Default value for the toggle
    SmartDashboard.putNumber("Wrist Rotationj", algeaIntake.rotation_getPosition());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {

   m_robotDrive.setDefaultCommand(new DriveTeleop(m_robotDrive, 
   () -> MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband), 
   () -> MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband), 
    () -> MathUtil.applyDeadband(-m_driverController.getRightX(), OIConstants.kDriveDeadband), true));

    // AUX CONTROLS

    //RUN THE ALGEA INTAKE WHEELS 
   m_auxController.x().whileTrue(new Run_Algea_Wheel(algeaIntake, .5));
   m_auxController.a().whileTrue(new Run_Algea_Wheel(algeaIntake, -1));  

    //Reverse
    m_auxController.y().whileTrue(new Run_Shooter(shooter, 0.25, 0.15)); 

         //Lift Algea Intake 
         m_auxController.leftBumper().whileTrue(new Run_Algea_Rotation(algeaIntake, 0.2)); 
         //Drop Algea Intake
        m_auxController.rightBumper().whileTrue(new Run_Algea_Rotation(algeaIntake, -0.2)); 

     //Collect Corral From Station 
     m_auxController.leftTrigger().whileTrue(new Run_Shooter(shooter, 0, -0.15));

     //Lift Floor Intake
     m_auxController.rightTrigger().whileTrue(new Run_Floor_Rotation(floor_Intake, 0.4));
     //Drop Floor Intake 
     m_auxController.b().whileTrue(new Run_Floor_Rotation(floor_Intake, -0.4));

    // MAHI / DRIVER CONTROLS 
    
    //Reset Gyro
    m_driverController.y().whileTrue(new ResetGyro(m_robotDrive));

    //Intake Floor Coral
    m_driverController.x().whileTrue(new Run_Floor_Wheel(floor_Intake, -0.3)); 
    
    //Spit Out Floor Coral
    m_driverController.a().whileTrue(new Run_Floor_Wheel(floor_Intake, 0.75)); 

  
    m_driverController.rightTrigger().whileTrue(new Run_Shooter(shooter, -0.35, -0.15));
    m_driverController.leftTrigger().whileTrue(new Intake_Lift(Pneunamatics));

    m_driverController.rightBumper().whileTrue(new SlowDrive(m_robotDrive, 
    () -> MathUtil.applyDeadband(-m_driverController.getLeftY(), OIConstants.kDriveDeadband), 
    () -> MathUtil.applyDeadband(-m_driverController.getLeftX(), OIConstants.kDriveDeadband), 
    () -> MathUtil.applyDeadband(-m_driverController.getRightX(), OIConstants.kDriveDeadband), true));

  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //Pneunamatics.enableCompressor();
    return autoChooser.getSelected();
  }
}

