package edu.nyu.cs9053.homework7;

public class CryptographicMoneyArrayCreator implements ArrayCreator<CryptographicMoney> {

    public CryptographicMoney[] create(int size) {
        return new CryptographicMoney[size];
    }

}
