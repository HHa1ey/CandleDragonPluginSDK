package com.ha1ey.CandleDragon.plugin;



public interface HelpPluginInfo {
    public ArgsInfo createArg();
    public ArgsUsage createArgsUsage();
    public ScanResult createScanResult();
    public String getThrowableInfo(Throwable e);
    public boolean isEnableProxy();
    public String getProtocol();
    public String getHost();
    public String getPort();
    public String getUsername();
    public String getPassword();
    public int getDefaultTimeout();
    public boolean isEnableTimeout();
    public int getTimeout();

}
