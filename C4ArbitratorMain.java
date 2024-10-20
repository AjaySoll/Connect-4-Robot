import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class C4ArbitratorMain {

    public static void main(String[] args) {
        // Instantiate your behaviours
        Behavior connect4 = new Connect4();
        Behavior sensorCalibration = new sensorCalibration();
        Behavior batteryLevel = new batteryLevel();
        Behavior winChecker = new WinChecker();
        Behavior haltBehaviour = new HaltBehaviour();
        
        // Create an instance of the Arbitrator with behaviour priority
        Arbitrator arbitrator = new Arbitrator(new Behavior[] {sensorCalibration, connect4, winChecker,batteryLevel,haltBehaviour});

        // Start the Arbitrator, which will manage the behaviours
        arbitrator.go();
    }
}