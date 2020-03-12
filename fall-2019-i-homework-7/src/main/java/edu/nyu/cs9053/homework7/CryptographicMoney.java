package edu.nyu.cs9053.homework7;

public class CryptographicMoney implements Cryptocurrency {

    private double amount;

    public CryptographicMoney(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CryptographicMoney that = (CryptographicMoney) obj;
        return (this.amount == that.getAmount());
    }

    @Override
    public int hashCode() {
        int hash = Double.valueOf(amount).hashCode();
        return hash;
    }

}
