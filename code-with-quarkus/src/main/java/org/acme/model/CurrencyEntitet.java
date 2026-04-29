package org.acme.model;

import jakarta.persistence.*;

@Entity
@Table(name = "currency")
public class CurrencyEntitet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private double value;
    private double convertedValue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFromCurrency() { return fromCurrency; }
    public void setFromCurrency(String fromCurrency) { this.fromCurrency = fromCurrency; }

    public String getToCurrency() { return toCurrency; }
    public void setToCurrency(String toCurrency) { this.toCurrency = toCurrency; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public double getConvertedValue() { return convertedValue; }
    public void setConvertedValue(double convertedValue) { this.convertedValue = convertedValue; }
}
