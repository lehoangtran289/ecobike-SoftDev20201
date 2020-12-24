package com.hust.ebr.components.abstractdata.gui;

import javax.swing.*;
import java.awt.*;

public abstract class ADataCreditCardDialog<T> extends JDialog {

    protected T t;
    protected GridBagLayout layout;
    protected GridBagConstraints c = new GridBagConstraints();
    protected JTextField creditCardField;

    public ADataCreditCardDialog(T t) {
        super((Frame) null, "CREDIT CARD INFORMATION", true);
        this.t = t;

        setContentPane(new JPanel());
        layout = new GridBagLayout();
        getContentPane().setLayout(layout);

        creditCardField = new JTextField(15);
        addNewField(creditCardField, "Enter your credit card: ");

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            String cardNumber = creditCardField.getText();
            checkCreditCard(cardNumber);
        });

        c.gridx = 1;
        c.gridy = getLastRowIndex();
        getContentPane().add(confirmButton, c);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public abstract void checkCreditCard(String cardNumber);

    private void addNewField(JTextField textField, String label) {
        int row = getLastRowIndex();
        JLabel titleLabel = new JLabel(label);
        c.gridx = 0;
        c.gridy = row;
        getContentPane().add(titleLabel, c);
        c.gridx = 1;
        c.gridy = row;
        getContentPane().add(textField, c);
    }

    private int getLastRowIndex() {
        layout.layoutContainer(getContentPane());
        int[][] dim = layout.getLayoutDimensions();
        int rows = dim[1].length;
        return rows;
    }
}
