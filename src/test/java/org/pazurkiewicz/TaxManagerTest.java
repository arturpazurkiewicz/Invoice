package org.pazurkiewicz;

import org.junit.Assert;
import org.junit.Test;

import static org.pazurkiewicz.TaxManager.taxCalculation;

public class TaxManagerTest {
    @Test
    public void shouldChangeTaxToString(){
        Assert.assertEquals("23%",TaxManager.taxToString(TaxManager.taxType.o23));
    }
    @Test
    public void shouldChangeStringToTax(){
        Assert.assertEquals(TaxManager.taxType.o23,TaxManager.stringToTax("23%"));
    }

    @Test
    public void shouldCalculateTax(){
        Assert.assertEquals(283.82f, taxCalculation(1234, TaxManager.taxType.o23),0.00f);
    }
}
