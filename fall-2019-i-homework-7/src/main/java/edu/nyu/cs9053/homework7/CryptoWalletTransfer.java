package edu.nyu.cs9053.homework7;

public class CryptoWalletTransfer<T extends Cryptocurrency, S extends T> {

    private CryptoWallet<T> copyToWallet;
    private CryptoWallet<S> copyFromWallet;

    public CryptoWalletTransfer(CryptoWallet<T> copyToWallet, CryptoWallet<S> copyFromWallet) {
        this.copyToWallet = copyToWallet;
        this.copyFromWallet = copyFromWallet;
    }

    public boolean copyProcess() {
        boolean flag = true;
        int length = copyFromWallet.size();
        for (int i = 0; i < length; i++) {
            S value = copyFromWallet.get(i);
            if (! copyToWallet.add(value)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
