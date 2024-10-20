import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class HaltBehaviour implements Behavior {
    
    @Override
    public boolean takeControl() {
        // Take control if the back button is pressed
        return Button.getButtons() == Button.ID_ESCAPE;
    }

    @Override
    public void action() {
        // Halting arbitration
        System.exit(0);
    }

    @Override
    public void suppress() {

    }
}