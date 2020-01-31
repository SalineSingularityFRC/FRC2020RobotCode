package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;


public class ConveyorBelt {

    MotorController conveyorBelt1, conveyorBelt2;

    private final double forwardSpeed = 0.25;
    final private double reverseSpeed = -0.25;
    
    
    public ConveyorBelt ( int conveyorBeltport1,  int conveyorBeltport2) {
        conveyorBelt1 = new Spark(conveyorBeltport1, true, 0.0);
        conveyorBelt2 = new Spark(conveyorBeltport2, true, 0.0);
        conveyorBelt2.follow(conveyorBelt1, true);

    

    }
        public void conveyorBeltForward() {
            conveyorBelt1.setSpeed(forwardSpeed);
        }

        public void conveyorBeltReverse() {
            conveyorBelt1.setSpeed(reverseSpeed);
        }

        public void conveyorBeltOff() {
            conveyorBelt1.setSpeed(0);

        }

        
}
