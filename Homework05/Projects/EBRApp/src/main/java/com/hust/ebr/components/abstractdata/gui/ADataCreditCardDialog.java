package com.hust.ebr.components.abstractdata.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;

import javax.swing.*;
import java.awt.*;

import static com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController.CARD_ACCEPTED;

public abstract class ADataCreditCardDialog<T> extends JDialog {

    protected T t;
    protected GridBagLayout layout;
    protected GridBagConstraints c = new GridBagConstraints();
    protected JTextField creditCardField;

    protected String cardNumber;

    public ADataCreditCardDialog(EBRUserRentBikeController controller, T t) {
        super((Frame) null, "CREDIT CARD INFORMATION", true);
        this.t = t;

        setContentPane(new JPanel());
        layout = new GridBagLayout();
        getContentPane().setLayout(layout);

        creditCardField = new JTextField(15);
        addNewField(creditCardField, "Enter your credit card: ");

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            cardNumber = creditCardField.getText();
            int cardStatus = controller.checkCreditCard(cardNumber, (Bike) t);
            if (cardStatus == CARD_ACCEPTED) {
                performNextState(controller);
            }
        });

        c.gridx = 1;
        c.gridy = getLastRowIndex();
        getContentPane().add(confirmButton, c);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

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

    public abstract void performNextState(EBRUserRentBikeController controller);
}
