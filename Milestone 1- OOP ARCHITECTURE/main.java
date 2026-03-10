import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        SmartLight livingRoomLight = new SmartLight("Living Room Light");
        SmartThermostat nest = new SmartThermostat("Main Nest");


        livingRoomLight.setBrightness(85);
        livingRoomLight.setBrightness(true);

        // 3. Polymorphism: Store different types in one list
     
        List<SmartDevice> myHome = new ArrayList<>();
        myHome.add(livingRoomLight);
        myHome.add(nest);

        System.out.println("\n--- Running Home Automation ---");

        for (SmartDevice device : myHome) {
            device.turnOn();       // Works for both!
            device.displayStatus(); // Runs the SPECIFIC child version (Method Overriding)
        }
    }
}