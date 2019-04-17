package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);

        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);

            BigDecimal reducedPrice =calculatereduce(product.getPrice(),product.getDiscountRate(),curItem.getCount());

//            BigDecimal reducedPrice = product.getPrice()
//                    .multiply(product.getDiscountRate())
//                    .multiply(new BigDecimal(curItem.getCount()));

            subTotal = subTotal.subtract(reducedPrice);
        }
        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = calculatetotal(product.getPrice(),item.getCount());

//            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }

    private BigDecimal calculatetotal(BigDecimal price, Integer count) {
        return (price.multiply(new BigDecimal(count)));
    }
    private BigDecimal calculatereduce(BigDecimal price, BigDecimal discountRate, Integer count) {
        BigDecimal result=price.multiply(discountRate).multiply(new BigDecimal(count));
        return result;
    }
}
