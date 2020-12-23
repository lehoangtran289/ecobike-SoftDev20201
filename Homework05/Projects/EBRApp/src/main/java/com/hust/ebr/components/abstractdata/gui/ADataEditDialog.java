package com.hust.ebr.components.abstractdata.gui;

import com.hust.ebr.components.abstractdata.controller.IDataManageController;

import javax.swing.*;
import java.awt.*;

public abstract class ADataEditDialog<T> extends JDialog {

    protected T t;
    protected GridBagLayout layout;
    protected GridBagConstraints c = new GridBagConstraints();

    public ADataEditDialog(T t, IDataManageController<T> controller) {
        super((Frame) null, "Edit", true);

        this.t = t;

        setContentPane(new JPanel());
        layout = new GridBagLayout();
        getContentPane().setLayout(layout);

        this.buildControls();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            T newT = getNewData();
            controller.update(newT);
            ADataEditDialog.this.dispose();
        });

        c.gridx = 1;
        c.gridy = getLastRowIndex();
        getContentPane().add(saveButton, c);

        pack();
        setResizable(false);
        setVisible(true);
    }

    protected int getLastRowIndex() {
        layout.layoutContainer(getContentPane());
        int[][] dim = layout.getLayoutDimensions();
        int rows = dim[1].length;
        return rows;
    }

    public abstract void buildControls();

    public abstract T getNewData();
}
