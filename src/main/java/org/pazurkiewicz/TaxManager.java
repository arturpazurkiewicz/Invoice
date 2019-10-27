package org.pazurkiewicz;

public abstract class TaxManager {
    public enum taxType{
        o23,
        o8,
        o5,
        o0,
        zw
    }
    public static taxType stringToTax(String s){
        switch (s){
            case "o23":
                return taxType.o23;
            case "o8":
                return taxType.o8;
            case "o5":
                return taxType.o5;
            case "o0":
                return taxType.o0;
            default:
                return taxType.zw;
        }
    }
}
