
//INHERITANCE- We are inheriting parent abstract class smart device in smartlight class
public class Smartlight extends Smartdevice{
    
    private int brightness=50;
    public smartlight(string name);
    super(name);    //constructor of parent class is called through this keyword super

    public void setBrightness(int level) {
        this.brightness = level;
        System.out.println(deviceName + " brightness set to " + level + "%.");
    }
//overloading- same method with different parameters

    public void setBrightness(boolean max) {
        this.brightness = max ? 100 : 0;
        System.out.println(deviceName + " set to " + (max ? "MAX" : "MIN") + " brightness.");
    }



    @Override
    public void display(){

        String Power= isEnabled ? "ON" : "OFF";
        System.out.println("[Light}" + deviceName + "[Power ]" + Power + "[Brightness "+ brightness +"%");

    }
}
