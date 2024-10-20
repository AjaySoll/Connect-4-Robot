import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class DispensePuck {
	
	

	public static void dispense () {
		BaseRegulatedMotor puckDispenser = new EV3LargeRegulatedMotor(MotorPort.B);
		//sets the speed of the puckDispenser motor to forcefully push a puck out.
		puckDispenser.setSpeed(70);
		//rotates it to push the puck out
		puckDispenser.rotate(45);
		//sets a slower speed so that the dispenser doesn't fly out when retracting
		puckDispenser.setSpeed(40);
		//retracts to return to normal position
		puckDispenser.rotate(-45);
		
		puckDispenser.close();
		
	}
	
	

}
