package org.pazurkiewicz;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvoiceElementTest {
    @Test
    public void totalShouldBeCorrect() {
        Item item = mock(Item.class);
        when(item.getNetAmount()).thenReturn(12.0f);
        when(item.getTaxType()).thenReturn(TaxManager.taxType.o23);
        InvoiceElement invoiceElement = new InvoiceElement(item, 4.0f);
        Assert.assertEquals(48.0f, invoiceElement.netCalculation(), 0.00f);
        Assert.assertEquals(11.04f + 48.0f, invoiceElement.grossCalculation(), 0.00f);
    }
}
