package com.takisjoeapp.laundryaja.util.debug;

public class DebugLog {
    public static void DEBUG(String note, String LOG) {
        System.out.println("@DebugLog - DEBUG {"+LOG+"} : "+note);
    }

    public static void ROOT(String note, String LOG) {
        System.out.println("@DebugLog - ROOT {"+LOG+"} : "+note);

    }

    public static void RULES(String note, String LOG) {
        System.out.println("@DebugLog - RULES {"+LOG+"} : "+note);

    }
}
