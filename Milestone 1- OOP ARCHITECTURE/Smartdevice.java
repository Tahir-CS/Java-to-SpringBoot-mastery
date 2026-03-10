//This is the "Template." It provides shared logic so we don't repeat code in every device.
public abstract class Smartdevice implements Connectable {
    protected String deviceName;
    protected boolean isEnabled=false;
   
   
    public Smartdevice(String devicename){
        this.deviceName=devicename;

 
    }
    //now implementing interface function
    
    @Override
    public void turnoff(){
        isEnabled =true;
        System.out.println(deviceName + "Device is turned off")
    }
    @Override
    public void turnon(){
        isEnabled=false;
        System.out.println(deviceName+" device is turned on");

    }

    //Now making a abstract function that every child will define themselves
public abstract void display();

}

