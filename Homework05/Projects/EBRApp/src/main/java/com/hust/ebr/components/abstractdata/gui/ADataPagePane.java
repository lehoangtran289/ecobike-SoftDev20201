package com.hust.ebr.components.abstractdata.gui;

import javax.swing.*;
import java.awt.*;

public class ADataPagePane<T> extends JPanel {
    private SpringLayout layout = new SpringLayout();

    public ADataPagePane(ADataSearchPane searchPane, ADataListPane<T> listPane) {
//        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(searchPane);
        this.add(listPane);

        layout.putConstraint(SpringLayout.WEST, searchPane, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, searchPane, 5, SpringLayout.NORTH, this);
//        layout.putConstraint(SpringLayout.EAST, searchPane, -5, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, listPane, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, listPane, 5, SpringLayout.SOUTH, searchPane);
        layout.putConstraint(SpringLayout.EAST, listPane, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, listPane, -5, SpringLayout.SOUTH, this);
    }

    public void addPane(Component comp) {
        this.add(comp);
        layout.putConstraint(SpringLayout.EAST, comp, -5, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, comp, 5, SpringLayout.NORTH, this);
    }

}
