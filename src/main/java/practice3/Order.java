package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculate(){
        return new OrderCalculator(this).Calculate();
    }

    private class OrderCalculator {
        private BigDecimal subTotal=new BigDecimal(0);
        private BigDecimal tax;

        public OrderCalculator(Order order) {
            tax=order.tax;
        }

        public BigDecimal Calculate() {
            CalculateSubTotal();
            SubstracDiscount();
            BigDecimal tax = subTotal.multiply(this.tax);
            BigDecimal grandTotal = subTotal.add(tax);
            return grandTotal;
        }
        private void CalculateSubTotal(){
            for (OrderLineItem lineItem : orderLineItemList){
                subTotal=subTotal.add(lineItem.getPrice());
            }
        }

        private void SubstracDiscount(){
            for (BigDecimal discount : discounts) {
                subTotal = subTotal.subtract(discount);
            }
        }



    }


//    public BigDecimal calculate() {
//        BigDecimal subTotal = new BigDecimal(0);
//
//        // Total up line items
//        for (OrderLineItem lineItem : orderLineItemList) {
//            subTotal = subTotal.add(lineItem.getPrice());
//        }
//
//        // Subtract discounts
//        for (BigDecimal discount : discounts) {
//            subTotal = subTotal.subtract(discount);
//        }
//
//        // calculate tax
//        BigDecimal tax = subTotal.multiply(this.tax);
//
//        // calculate GrandTotal
//        BigDecimal grandTotal = subTotal.add(tax);
//
//        return grandTotal;
//    }
}

 