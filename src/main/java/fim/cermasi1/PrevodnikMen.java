package fim.cermasi1;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrevodnikMen extends JFrame {
    public JPanel mainPanel, topGrid, resultPanel;
    public JLabel lInput, lcombox, lcalculate, lresult;
    public JTextField quantity;
    public JButton convert;
    public JComboBox<Currency> comboBox;
    public ComboCurrencyModel comboModel;

    public PrevodnikMen(){
        List<Currency> currencies = new CurrencyReader().getCurrencies();
        mainPanel = new JPanel();

        topGrid = new JPanel();
        topGrid.setLayout(new GridLayout(2,3));

        // First row - Just labels
        lInput = new JLabel("Množství");
        lcombox = new JLabel("Měna");
        lcalculate = new JLabel("Konverze");

        // Second row - Amount, combo box, button
        quantity = new JTextField();

        comboBox = new JComboBox<>();
        comboModel = new ComboCurrencyModel(currencies, this);
        comboBox.setModel(comboModel);

        convert = new JButton("Převést");
        setEventListeners();

        // Add components to the top grid
        topGrid.add(lInput);
        topGrid.add(lcombox);
        topGrid.add(lcalculate);
        topGrid.add(quantity);
        topGrid.add(comboBox);
        topGrid.add(convert);

        // Result
        lresult = new JLabel("Výsledek:", SwingConstants.LEFT);

        resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultPanel.add(lresult);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topGrid);

        mainPanel.add(resultPanel);

        add(mainPanel);
        pack();
        setVisible(true);
    }

    private void setEventListeners() {
        // NAVÍC: listenery pro automatické změny currency resultu pro combobox a input box
        convert.addActionListener(e -> {
            updateCurrencyResult();
        });

        quantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCurrencyResult();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCurrencyResult();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCurrencyResult();
            }
        });
    }
    public void updateCurrencyResult(){
        Currency c = (Currency) comboBox.getSelectedItem();
        assert c != null;
        try {
            double result = Double.parseDouble(quantity.getText()) * (c.getCourse()/c.getQuantity());

            result = Math.round(result * 100.0) / 100.0;
            
            lresult.setText("Výsledek: " + result+ " Kč");
        }
        catch (NumberFormatException exception){
            lresult.setText("Musíte zadat číslo pro výpočet měny");
        }
    }
}
