package com.eoh.digitalplatoon;

import java.util.Date;

public class Invoice {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    Long id;
String Client;
Long vatRate;
Date invoiceDate;
}