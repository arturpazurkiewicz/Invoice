package org.pazurkiewicz;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class InvoiceBuilderTest {
    @Test
    public void totalShouldBeCorrect(){
        InvoiceBuilder invoiceBuilder = new InvoiceBuilder();
        InvoiceElement invoiceElement1 = mock(InvoiceElement.class);
        InvoiceElement invoiceElement2 = mock(InvoiceElement.class);
        when(invoiceElement1.netCalculation()).thenReturn(1234.00f);
        when(invoiceElement1.grossCalculation()).thenReturn(134342.02f);
        when(invoiceElement2.netCalculation()).thenReturn(12345.00f);
        when(invoiceElement2.grossCalculation()).thenReturn(1233f);
        invoiceBuilder.addElement(invoiceElement1);
        invoiceBuilder.addElement(invoiceElement2);
        Client client = mock(Client.class);
        Date date = mock(Date.class);
        Invoice invoice = invoiceBuilder.createNewInvoice(client,date,0);

        Assert.assertEquals(13579f,invoice.getFullNet(),0.00f);
        Assert.assertEquals(135575.02f,invoice.getFullGross(),0.00f);

    }
}
