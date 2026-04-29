package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {

    private String from;
    private String to;
    private double rate;
    private String date;
    private String source;
    private double value;
    private double convertedValue;

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public double getConvertedValue() { return convertedValue; }
    public void setConvertedValue(double convertedValue) { this.convertedValue = convertedValue; }
}
