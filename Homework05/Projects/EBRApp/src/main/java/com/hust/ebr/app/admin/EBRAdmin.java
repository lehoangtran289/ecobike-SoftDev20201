package com.hust.ebr.app.admin;

import javax.swing.*;
import java.awt.*;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class EBRAdmin extends JFrame {

    public EBRAdmin(EBRAdminController controller) {
        JPanel rootPanel = new JPanel();
        setContentPane(rootPanel);

        BorderLayout layout = new BorderLayout();
        rootPanel.setLayout(layout);

        JPanel homePage = controller.getHomePage();
        rootPanel.add(homePage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Eco Bike Rental for ADMINISTRATOR");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }


}
