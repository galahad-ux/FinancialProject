package org.isep.financialproject;

import javafx.fxml.FXML;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


public class AnalyticsController {
    @FXML
    private LineChart<String, Number> Analytics;
    @FXML
    public void initialize() {
        refreshChart();
    }

    private void refreshChart() {
        Analytics.getData().clear();

        if (LoggedInUser.investment == null) {
            return;
        }

        ArrayList<AssetTransaction> AT = LoggedInUser.investment.getTransactions();

        if (AT ==null || AT.size()==0) {
            return;
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Asset ^W^");
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Double> quantities = new ArrayList<>();
        ArrayList<Double> lastPrices = new ArrayList<>();

        SimpleDateFormat fmt = new SimpleDateFormat("MM-dd HH:mm");

        for(int k = 0; k <AT.size(); k++) {
            AssetTransaction at =AT.get(k);

            String symbol = extractSymbol(at.getDescription());
            int index = symbols.indexOf(symbol);

            if (index ==-1) {
                symbols.add(symbol);
                quantities.add(0.0);
                lastPrices.add(0.0);

                index = symbols.size()-1;
            }

            double q = at.getQuantity();
            double price = 0;
            if (q != 0) {
                price = at.getAmount() / q;
            }


            if (price > 0) {
                lastPrices.set(index, price);
            }


            if (at.getType() == TransactionType.SELL) {
                quantities.set(index, quantities.get(index) - q);
            } else {
                quantities.set(index, quantities.get(index) + q);
            }

            double total = 0;
            for (int i = 0; i < symbols.size(); i++) {
                double quan = quantities.get(i);
                double p = lastPrices.get(i);
                if (quan > 0 && p > 0) {
                    total += quan * p;
                }
            }


            String Date = fmt.format(at.getDate());
            series.getData().add(new XYChart.Data<>(Date, total));
        }
        Analytics.getData().add(series);
    }



    private String extractSymbol(String desc) {
        String[] p = desc.split(":");
        return p[p.length - 1];
    }
}

