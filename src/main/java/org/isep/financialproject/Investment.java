package org.isep.financialproject;
import java.util.ArrayList;
import java.util.Date;

public class Investment {
    private ArrayList<Asset> assets = new ArrayList<>();
    private ArrayList<AssetTransaction> transactions = new ArrayList<>();

    public ArrayList<Asset> getAssets() {
        return assets;
    }
    public ArrayList<AssetTransaction> getTransactions() {
        return transactions;
    }


    private Asset findAsset(String symbol) {
        for (Asset a : assets) {
            if (a.getSymbol().equalsIgnoreCase(symbol)) return a;
        }
        return null;
    }

    public void buyStock(String symbol,String companyName,double quantity,double purchasePrice,Date date){
        Asset asset=findAsset(symbol);

        if (asset==null){
            asset = new Stock(symbol,quantity,purchasePrice,date,companyName);
            assets.add(asset);

        }else{
            double q =asset.getQuantity();
            asset.setQuantity(q+quantity);
            asset.setPurchasePrice(purchasePrice);
            asset.setPurchaseDate(date);
        }

        transactions.add(new AssetTransaction("No."+transactions.size(),date,quantity * purchasePrice,
                "Buy Stock:"+symbol,TransactionType.BUY,quantity));


    }
    public void buyToken(String symbol,String tokenName,double quantity,double purchasePrice,Date date){
        Asset asset=findAsset(symbol);

        if (asset==null){
            asset = new Token(symbol,quantity,purchasePrice,date,tokenName);
            assets.add(asset);

        }else{
            double q =asset.getQuantity();
            asset.setQuantity(q+quantity);
            asset.setPurchasePrice(purchasePrice);
            asset.setPurchaseDate(date);
        }

        transactions.add(new AssetTransaction("No."+transactions.size(),date,quantity * purchasePrice,
                "Buy Token:"+symbol,TransactionType.BUY,quantity));


    }

    public void sell(String symbol, double quantity, double price, Date date) {
        Asset asset = findAsset(symbol);
        if (asset == null || asset.getQuantity() < quantity){
            System.out.println("error:no enough assets");
            return;
        }else {
            asset.setQuantity(asset.getQuantity() - quantity);
            asset.setPurchasePrice(price);
            asset.setPurchaseDate(date);
        }

        transactions.add(new AssetTransaction("No." + transactions.size(), date, quantity * price,
                "Sell :" + symbol, TransactionType.SELL, quantity));

        if (asset.getQuantity() == 0) assets.remove(asset);
    }

    public void addStock(String symbol, String companyName,
                         double quantity, double price, Date date) {
        Asset asset = findAsset(symbol);
        if (asset == null) {
            asset = new Stock(symbol, quantity, price, date, companyName);
            assets.add(asset);
        } else {
            asset.setQuantity(asset.getQuantity() + quantity);
            asset.setPurchasePrice(price);
            asset.setPurchaseDate(date);
        }
        transactions.add(new AssetTransaction("No." + transactions.size(), date, quantity * price,
                "Add Stock:" + symbol, TransactionType.ADD, quantity));

    }

    public void addToken(String symbol, String tokenName,
                         double quantity, double price, Date date) {
        Asset asset = findAsset(symbol);
        if (asset == null) {
            asset = new Token(symbol, quantity, price, date,tokenName);
            assets.add(asset);
        } else {
            asset.setQuantity(asset.getQuantity() + quantity);
            asset.setPurchasePrice(price);
            asset.setPurchaseDate(date);
        }
        transactions.add(new AssetTransaction("No." + transactions.size(), date, quantity * price,
                "Add Token:" + symbol, TransactionType.ADD, quantity));
    }


}
