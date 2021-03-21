// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class TurnAngle extends CommandBase {
  /** Creates a new TurnAngle. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSub m_subsystem;
  
  // for gyro
  //private static final double kP = 0.005;
  private double kAngleSetpoint = 0.0;

  // bounds for th gyro angle measure to reach
  private double gyro_upperBound = 0.0;

  private double gyro_lowerBound = 0.0;

  //check to see if gyro reset
  private Boolean first_time = true;


  public TurnAngle(DriveSub subsystems, double degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystems;
    addRequirements(m_subsystem);

    // make it equal to the angle
    kAngleSetpoint = degrees;

    // upper and lower bounds of gyro angle
    gyro_upperBound = kAngleSetpoint + 2;

    gyro_lowerBound = kAngleSetpoint - 2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setStraight();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // check to see if gyro reset
    if(first_time){
      first_time = false;
      m_subsystem.setStraight();
    }

    /*
    double gyroAngle = m_subsystem.getAngle();


    double turningValue = (kAngleSetpoint - gyroAngle) * kP;
    */

    //System.out.println(kAngleSetpoint);

    // if angle negative, change the rotation value
    int x = 0;

    if(kAngleSetpoint > 0){
      x = 1;
    }
    else{
      x = -1;
    }
  
    m_subsystem.robotDrive().arcadeDrive(0.125, x);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.robotDrive().arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*
    // angle value to hold
    double hold = 0.0;

    hold = gyro_upperBound;
    // if the degree is negative then reverse upper and lower bounds
    if(kAngleSetpoint < 0){
      gyro_upperBound = gyro_lowerBound;
      gyro_lowerBound = hold;
    }
    */

    // get gyro reading
    double ang = m_subsystem.getAngle();

    System.out.println(ang);

    //test to see if in bounds
    if(ang >= gyro_lowerBound && ang <= gyro_upperBound){
      return true;
    }
    else{
      return false;
    }

  }
}

/*
double ang = m_subsystem.getAngle();

if(ang >= gyro_lowerBound and turningValue <= gyro_upperBound){
  return false;
}
else{
  return true;
}
*/
