package com.hust.ebr.components.rentbike.gui;

import com.hust.ebr.beans.Bike;
import com.hust.ebr.beans.CreditCard;
import com.hust.ebr.components.rentbike.controller.EBRUserRentBikeController;

import javax.swing.*;
import java.awt.*;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class EBRUserRentBike extends JFrame {

    public EBRUserRentBike(EBRUserRentBikeController controller, Bike bike, CreditCard creditCard) {
        JPanel rootPanel = new JPanel();
        setContentPane(rootPanel);

        BorderLayout layout = new BorderLayout();
        rootPanel.setLayout(layout);

        JPanel rentBikePage = controller.getRentBikePage(bike, creditCard);
        rootPanel.add(rentBikePage);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Rent Bike Detail");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }
}
