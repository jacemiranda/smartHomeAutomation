package smartHome;

public class ThirdPartyCurtainSDK {
    public void open()  { System.out.println("[CurtainSDK] OPEN"); }
    public void close() { System.out.println("[CurtainSDK] CLOSE"); }
    public void liftTo(int percent) {
        System.out.println("[CurtainSDK] LIFT " + percent + "%");
    }
}