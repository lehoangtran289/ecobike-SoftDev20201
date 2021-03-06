package com.hust.ebr.components.abstractdata.gui;

import javax.swing.*;
import java.awt.*;

public abstract class ADataSinglePane<T> extends JPanel {

    protected T t;

    protected GridBagLayout layout;
    protected GridBagConstraints c;
    private JPanel panel;

    public ADataSinglePane() {
        buildControls();
    }

    public ADataSinglePane(T t) {
        this();
        this.t = t;
        displayData();
    }

    public void buildControls() {
        layout = new GridBagLayout();
        this.setLayout(layout);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
    }

    public abstract void displayData();

    public void addDataHandlingComponent(Component component) {
        if (panel == null) {
            int row = getLastRowIndex();
            c.gridx = 0;
            c.gridy = row;
            panel = new JPanel();
            this.add(panel, c);
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        }

        panel.add(component);
    }

    public void removeDataHandlingComponent() {
        panel.removeAll();
    }

    public void updateData(T t) {
        this.t = t;
        displayData();
    }

    public T getData() {
        return this.t;
    }

    protected int getLastRowIndex() {
        layout.layoutContainer(this);
        int[][] dim = layout.getLayoutDimensions();
        int rows = dim[1].length;
        return rows;
    }
}
