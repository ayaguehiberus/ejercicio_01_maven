package com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils;

public class Flags {

    private static final String BROWSER = "browser";
    private static final String HEADLESS = "headless";
    private final String browser = System.getProperty(BROWSER);
    private final boolean isHeadless = Boolean.parseBoolean(System.getProperty(HEADLESS));


    private static Flags instance;

    private boolean parseBoolean(String string){
        String result= (string == null) ? "false" : string;
        result = result.toLowerCase().trim();
        return (result.equals("true") || result.equals("false")) && Boolean.parseBoolean(result);
    }
    private Flags(){

    }
    public static Flags getInstance(){
        if (instance == null){
            instance = new Flags();
        }
        return instance;
    }

    public boolean isHeadless(){
        return isHeadless;
    }
    public String getBrowser(){
        return browser;
    }
}
