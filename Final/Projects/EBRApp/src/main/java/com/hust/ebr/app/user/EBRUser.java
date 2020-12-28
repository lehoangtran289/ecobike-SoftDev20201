package com.hust.ebr.app.user;

import javax.swing.*;
import java.awt.*;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class EBRUser extends JFrame {

    public EBRUser(EBRUserController controller) {
        JPanel rootPanel = new JPanel();
        setContentPane(rootPanel);

        BorderLayout layout = new BorderLayout();
        rootPanel.setLayout(layout);

        try {
            Class.forName("com.hust.ebr.components.dockingstation.controller.UserStationPageController");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JPanel stationPage = controller.getPage("userStationPage");
        rootPanel.add(stationPage);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Eco Bike Rental for USER");
        setVisible(true);
    }
}
