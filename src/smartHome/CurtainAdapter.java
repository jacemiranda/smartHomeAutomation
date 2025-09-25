package smartHome;

public class CurtainAdapter implements Device {
    private final ThirdPartyCurtainSDK sdk = new ThirdPartyCurtainSDK();
    private final String name;

    public CurtainAdapter(String name) { this.name = name; }

    public void open()  { sdk.open(); }
    public void close() { sdk.close(); }

    @Override
    public String getName() { return name; }
}