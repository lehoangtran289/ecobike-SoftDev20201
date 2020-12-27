package com.hust.ebr.components.abstractdata.gui;

import com.hust.ebr.components.abstractdata.controller.IDataSearchController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class ADataSearchPane extends JPanel {

    protected GridBagLayout layout;
    protected GridBagConstraints c;

    private IDataSearchController searchController;

    public ADataSearchPane() {
        layout = new GridBagLayout();
        this.setLayout(layout);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        buildControls();

        int row = getLastRowIndex();
        c.gridx = 0;
        c.gridy = row;
        JButton searchButton = new JButton("Search");
        add(searchButton, c);
        searchButton.addActionListener(e -> {
            searchController.search(getQueryParams());
        });

        // Empty label for resizing
        c.weightx = 1;
        c.gridx = 3;
        c.gridy = row - 1;
        add(new JLabel(), c);
    }

    public ADataSearchPane(IDataSearchController controller) {
        this();
        this.searchController = controller;
    }

    public abstract void buildControls();

    protected int getLastRowIndex() {
        layout.layoutContainer(this);
        int[][] dim = layout.getLayoutDimensions();
        return dim[1].length;
    }

    public void setSearchController(IDataSearchController searchController) {
        this.searchController = searchController;
    }

    public Map<String, String> getQueryParams() {
        return new HashMap<>();
    }

    public void fireSearchEvent() {
        searchController.search(getQueryParams());
    }
}
