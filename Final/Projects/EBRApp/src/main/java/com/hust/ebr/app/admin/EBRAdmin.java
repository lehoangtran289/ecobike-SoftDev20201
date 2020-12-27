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

        try {
            Class.forName("com.hust.ebr.components.dockingstation.controller.AdminStationPageController");
            Class.forName("com.hust.ebr.components.bike.controller.AdminBikePageController");
            Class.forName("com.hust.ebr.components.historyrental.controller.AdminHistoryPageController");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JPanel stationPage = controller.getPage("adminStationPage");
        tabbedPane.addTab("Stations", null, stationPage, "Stations");

        JPanel bikePage = controller.getPage("adminBikePage");
        tabbedPane.addTab("Bikes", null, bikePage, "Bikes");

        JPanel historyPage = controller.getPage("adminHistoryPage");
        tabbedPane.addTab("Renting History", null, historyPage, "Renting History");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Eco Bike Rental for ADMINISTRATOR");
        setVisible(true);
    }


}
