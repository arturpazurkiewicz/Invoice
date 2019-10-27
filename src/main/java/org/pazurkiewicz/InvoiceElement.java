package org.pazurkiewicz;

public class InvoiceElement {
    private Item item;
    private float quantity;

    public InvoiceElement(Item item, float quantity) {
        this.item = item;
        this.quantity = quantity;

    }

    float taxCalculation(){
        float net = netCalculation();
        switch (item.getTaxType()){
            case o23:
                return net*0.23f;
            case o8:
                return net*0.08f;
            case o5:
                return net*0.05f;
            default:
                return 0f;
        }
    }
    float grossCalculation(){
        return netCalculation()+taxCalculation();
    }

    public Item getItem() {
        return item;
    }

    public float getQuantity() {
        return quantity;
    }

    float netCalculation(){
        return quantity*item.getNetAmount();
    }
}
