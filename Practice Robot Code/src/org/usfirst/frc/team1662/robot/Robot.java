/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1662.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.SpeedController;

//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {                    // C++ --> class Robot : public frc::IterativeRobot {
	
	//Connects motor to a pin on the RoboRio
	private SpeedController motor1 = new Victor(0);             // C++ --> //frc::Victor motor1{0};
	private SpeedController motor2 = new Victor(1);
	private SpeedController motor3 = new Victor(2);
	private SpeedController motor4 = new Victor(3);
	
	//Connects encoder to pins on the RoboRio 
	private Encoder encoder1 = new Encoder(1, 2, false, EncodingType.k4X);   //C++ --> Encoder* encoder1 = new Encoder(1, 2, false, EncodingType::k4X);
	
	private Joystick joystick1 = new Joystick(0);
	
	@Override
	public void robotInit() {
		
		encoder1.setDistancePerPulse(6 * Math.PI / 128);
		encoder1.setSamplesToAverage(5);
		encoder1.reset();
	}

	
	@Override
	public void autonomousInit() {
		
		encoder1.reset();
	}

	
	@Override
	public void autonomousPeriodic() {                           				// C++ --> void AutonomousPeriodic() override {
		
		//When the robot moves 10 feet stop all the motors
		if (encoder1.getDistance() > 10) {                      				 // C++ --> if (encoder1->GetDistance() > 10.0) {
			motor1.set(0);                                     					 // C++ --> motor1.Set(0);
			motor2.set(0);
			motor3.set(0);
			motor4.set(0);
		//If the robot has not got to 10 foot mark keep the motors moving
		} else {
			motor1.set(0.6);                                  					 // C++ --> motor1.Set(0.6); 
			motor2.set(0.6);
			motor3.set(-0.6);                                  					// C++ --> motor3.Set(-0.6);
			motor4.set(-0.6);
		}
	}


	@Override
	public void teleopPeriodic() {
		double power = joystick1.getRawAxis(1);
		double turn = joystick1.getRawAxis(4);
		
		power = power * 0.6;
		turn = turn * 0.6;
		
		double leftspeed = power + turn;
		double rightspeed = power - turn;
		
		motor1.set(leftspeed);
		motor2.set(leftspeed);
		motor3.set(rightspeed);
		motor4.set(rightspeed);
		
	}


	@Override
	public void testPeriodic() {
	}
}
