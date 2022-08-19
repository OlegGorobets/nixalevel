package com.nixalevel.module.utility;

import com.nixalevel.module.model.invoice.Invoice;
import com.nixalevel.module.model.invoice.InvoiceType;
import com.nixalevel.module.model.product.Product;
import com.nixalevel.module.model.product.Telephone;
import com.nixalevel.module.model.product.Television;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalyticalData {
    private void numberOfProductSoldByCategory(List<Invoice> invoices) {
        Long telephone = invoices.stream()
                .map(Invoice::getProductList)
                .map(products -> products.stream()
                        .filter(product -> product.getClass().equals(Telephone.class))
                        .count())
                .toList().stream()
                .reduce(0L, Long::sum);
        Long television = invoices.stream()
                .map(Invoice::getProductList)
                .map(products -> products.stream()
                        .filter(product -> product.getClass().equals(Television.class))
                        .count())
                .toList().stream()
                .reduce(0L, Long::sum);
        System.out.println("Number of telephones: " + telephone + ", Number of televisions: " + television);
    }

    private void amountOfSmallestInvoiceAndBuyerInformation(List<Invoice> invoices) {
        String sumSmallestInvoice = null;
        String buyerInformation = null;
        Optional<Invoice> invoiceOptional = invoices.stream()
                .min(Comparator.comparing(invoice -> invoice.getProductList().size()));
        if (invoiceOptional.isPresent()) {
            sumSmallestInvoice = invoiceOptional.stream()
                    .map(Invoice::getProductList)
                    .map(products -> products.stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                    .toList().stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining());
            buyerInformation = invoiceOptional.stream()
                    .map(Invoice::getCustomer)
                    .toList().stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining());
        }
        System.out.println("Amount of smallest invoice: " + sumSmallestInvoice + ",  Buyer information: " + buyerInformation);
    }

    private void sumOfAllPurchases(List<Invoice> invoices) {
        String sumOfAllPurchases = "The sum of all purchases: " + invoices.stream()
                .map(Invoice::getProductList)
                .map(products -> products.stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                .toList().stream()
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        System.out.println(sumOfAllPurchases);

    }

    private void numberOfInvoiceByTypeRetail(List<Invoice> invoices) {
        String numberOfInvoiceByTypeRetail = "Number of invoices with category retail: " + invoices.stream()
                .filter(type -> type.getType().equals(InvoiceType.RETAIL))
                .count();
        System.out.println(numberOfInvoiceByTypeRetail);
    }

    private void invoicesContainingOnlyOneTypeOfProduct(List<Invoice> invoices) {
        String invoicesContainingOnlyTelephone = "Invoices containing only telephones: " + invoices.stream()
                .filter(invoice -> invoice.getProductList().stream()
                        .allMatch(product -> product.getClass().equals(Telephone.class)))
                .toList().stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
        String invoicesContainingOnlyTelevision = "Invoices containing only televisions: " + invoices.stream()
                .filter(invoice -> invoice.getProductList().stream()
                        .allMatch(product -> product.getClass().equals(Television.class)))
                .toList().stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(invoicesContainingOnlyTelephone + "\n" + invoicesContainingOnlyTelevision);
    }

    private void firstThreeInvoice(List<Invoice> invoices) {
        String firstThreeInvoice = "First three invoices: " + invoices.stream()
                .limit(3)
                .toList().stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(firstThreeInvoice);
    }

    private void invoicesInformationByBuyersLowAge(List<Invoice> invoices) {
        int allowableAge = 18;
        String invoicesInformationByBuyersLowAge = "Invoices information by low age buyers: " + invoices.stream()
                .filter(invoice -> invoice.getCustomer().getAge() < allowableAge)
                .toList().stream()
                .peek(invoice -> invoice.setType(InvoiceType.LOWAGE))
                .toList().stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(invoicesInformationByBuyersLowAge);
    }

    private void sort(List<Invoice> invoices) {
        invoices.sort(Comparator.comparing((Invoice invoice) -> invoice.getCustomer().getAge())
                .reversed()
                .thenComparing(invoice -> invoice.getProductList().size())
                .thenComparing(invoice -> invoice.getProductList().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)));
    }

    public void getAnalytics(List<Invoice> invoices) {
        if (Invoice.getInvoiceCount() >= 15) {
            System.out.println("Statistic");
            numberOfProductSoldByCategory(invoices);
            amountOfSmallestInvoiceAndBuyerInformation(invoices);
            sumOfAllPurchases(invoices);
            numberOfInvoiceByTypeRetail(invoices);
            invoicesContainingOnlyOneTypeOfProduct(invoices);
            firstThreeInvoice(invoices);
            invoicesInformationByBuyersLowAge(invoices);
            System.out.println("Sorting");
            sort(invoices);
        }
    }
}
