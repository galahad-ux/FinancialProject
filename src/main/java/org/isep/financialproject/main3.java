package org.isep.financialproject;

import java.util.Date;


public class main3 {

    public static void main(String[] args) throws Exception{
        Investment investment = new Investment();
        Date now = new Date();

        //double Aprice = AlphaVantageClient.getStockPrice("AAPL");

        double Bprice = AlphaVantageClient.getStockPrice("BTC");

        //investment.buyStock("AAPL", "applelol", 30,Aprice,now );
        //investment.sell("AAPL", 3, Aprice, now);

        investment.buyToken("BTC","BTCCoin",1234,Bprice,now);
        investment.sell("BTC", 3, Bprice, now);
        //getStockPrice can only use one at the same time,Request will be throttled which asked over once;

        for (AssetTransaction t : investment.getTransactions()) {
            System.out.println(t);
        }





    }
}
