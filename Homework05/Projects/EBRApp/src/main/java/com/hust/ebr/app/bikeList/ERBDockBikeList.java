package com.hust.ebr.app.bikeList;

import com.hust.ebr.beans.DockingStation;

import javax.swing.*;
import java.awt.*;

import static com.hust.ebr.utils.Constants.WINDOW_HEIGHT;
import static com.hust.ebr.utils.Constants.WINDOW_WIDTH;

public class ERBDockBikeList extends JFrame {
    public ERBDockBikeList(ERBDockBikeListController controller, DockingStation dock) {
        JPanel rootPanel = new JPanel();
        setContentPane(rootPanel);

        BorderLayout layout = new BorderLayout();
        rootPanel.setLayout(layout);

        JPanel dockBikeListPage = controller.getBikeListPage();
        rootPanel.add(dockBikeListPage);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("List of bike in " + dock.getStationName() + " station");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }
}
