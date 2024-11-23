package com.example.trspo_lab1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController extends Application {
    StringBuilder sb = new StringBuilder();

    @FXML
    public TextField nField;
    @FXML
    public TextField threadField;
    @FXML
    private TextArea tabArea;

    @Override
    public void start(Stage primaryStage) {
        tabArea = new TextArea();
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void calc() {
        int n = Integer.parseInt(nField.getText());
        int numThreads = Integer.parseInt(threadField.getText());

        IntegralCalculator ic = new IntegralCalculator(numThreads);
        Function f = new Function();

        long startTime = System.nanoTime();
        double result = ic.calculate(1, 4, n, f);
        long endTime = System.nanoTime();

        double[] res = new double[4];
        res[0] = result;
        res[1] = (endTime - startTime) / 1e6;
        res[2] = numThreads;
        res[3] = n;

        show(res);
    }

    private void show(double[] res) {
        if (res.length >= 4) {
            double result = res[0];
            double executionTime = res[1];
            int numThreads = (int) res[2];
            int n = (int) res[3];

            sb.append("Кількість потоків: ").append(numThreads).append("\t\t\t");
            sb.append("Кількість інтервалів розбиття (n): ").append(n).append("\n");
            sb.append("F = ").append(result).append("\t\t");
            sb.append(String.format("Час виконання: %.5f ms\n", executionTime));
            sb.append("\n");

            tabArea.setText(sb.toString());
        } else {
            tabArea.setText("Invalid data.");
        }
    }

    @FXML
    private void clear() {
        sb.delete(0, sb.length());
        tabArea.setText("");
    }
}