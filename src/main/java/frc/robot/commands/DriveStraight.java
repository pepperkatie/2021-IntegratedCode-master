// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

import static frc.robot.Constants.*;

public class DriveStraight extends CommandBase {
  /** Creates a new DriveStraight. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSub m_subsystem;

  // for gyro
  private static final double kP = k_kP;
  private static final double AngleSetpoint = k_AngleSetPoint;

  public DriveStraight(DriveSub subsystems) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystems;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setStraight();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // reading from gyro
    double gyroAngle = m_subsystem.getAngle();

    double turningValue = (AngleSetpoint - gyroAngle) * kP;
  
    m_subsystem.robotDrive().arcadeDrive(0.5, turningValue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.robotDrive().arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
