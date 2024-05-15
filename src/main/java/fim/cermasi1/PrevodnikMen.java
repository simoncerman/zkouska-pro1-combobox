package fim.cermasi1;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.List;

public class PrevodnikMen extends JFrame {
    public JPanel mainPanel, topGrid;
    public JLabel lInput, lcombox, lcalculate, lresult;
    public JTextField quantity;
    public JButton convert;
    public JComboBox<Currency> comboBox;
    public ComboCurrencyModel comboModel;

    public PrevodnikMen(){
        List<Currency> currencies = new CurrencyReader().getCurrencies();
        System.out.println(currencies);

        mainPanel = new JPanel();

        topGrid = new JPanel();
        topGrid.setLayout(new GridLayout(2,3));

        // Labels
        lInput = new JLabel("Množství");
        lcombox = new JLabel("Měna");
        lcalculate = new JLabel("Konverze");

        // Text field
        quantity = new JTextField();

        // ComboBox
        comboBox = new JComboBox<>();
        comboModel = new ComboCurrencyModel(currencies);
        comboBox.setModel(comboModel);

        // Button
        convert = new JButton("Převést");
        convert.addActionListener(e -> {
            Currency c = (Currency) comboBox.getSelectedItem();
            assert c != null;
            double result = Double.parseDouble(quantity.getText()) * c.getCourse();
            lresult.setText("Výsledek: " + result);
        });

        // Add components to the top grid
        topGrid.add(lInput);
        topGrid.add(lcombox);
        topGrid.add(lcalculate);
        topGrid.add(quantity);
        topGrid.add(comboBox);
        topGrid.add(convert);

        // Result
        lresult = new JLabel("Výsledek:");

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topGrid);
        mainPanel.add(lresult);




        add(mainPanel);
        pack();
        setVisible(true);
    }

}
