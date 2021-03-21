// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ShooterSub;

import static frc.robot.Constants.*;

public class Feed extends CommandBase {
  /** Creates a new Feed. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSub m_subsystem;

  private int countTime = 0;

  public Feed(ShooterSub subsystems) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_subsystem = subsystems;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.feederMotorOn();
    countTime ++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // not good to have more than one return 
    Boolean done = false;

    // if count time grater than 2 seconds, then stop 
    if(countTime > k_feedCount){
      m_subsystem.feederMotorOff();
      countTime = 0;
      done = true;
    }
   return done;
  }
}
