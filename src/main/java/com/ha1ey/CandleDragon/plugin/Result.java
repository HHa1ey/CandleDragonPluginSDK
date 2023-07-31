package com.ha1ey.CandleDragon.plugin;

public interface Result {
    void printInfo(String str);
    void printError(Throwable throwable);
    void printRaw(String str);
    void printFail(String str);
    void printSuccess(String str);


    void setPocVul(boolean isvul);

    void setPocMsg(String msg);


    boolean isPocVul();



}
