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

        JTabbedPane tabbedPane = new JTabbedPane();
        rootPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel stationPage = controller.getStationPage();
        tabbedPane.addTab("Stations", null, stationPage, "Stations");

        JPanel bikePage = controller.getBikePage();
        tabbedPane.addTab("Bikes", null, bikePage, "Bikes");

        JPanel historyPage = controller.getHistoryPage();
        tabbedPane.addTab("Renting History", null, historyPage, "Histories");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Eco Bike Rental for ADMINISTRATOR");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }


}
