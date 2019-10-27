package org.pazurkiewicz;

public abstract class TaxManager {
    public enum taxType {
        o23,
        o8,
        o5,
        o0,
        zw
    }

    public static taxType stringToTax(String s) {
        switch (s) {
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

    public static String taxToString(taxType tax) {
        switch (tax) {
            case o23:
                return "o23";
            case o8:
                return "o8";
            case o5:
                return "o5";
            case o0:
                return "o0";
            case zw:
                return "zw";
            default:
                return "000";
        }
    }

    public static float taxCalculation(float net, taxType tax) {
        switch (tax) {
            case o23:
                return net * 0.23f;
            case o8:
                return net * 0.08f;
            case o5:
                return net * 0.05f;
            default:
                return 0f;
        }
    }
}
