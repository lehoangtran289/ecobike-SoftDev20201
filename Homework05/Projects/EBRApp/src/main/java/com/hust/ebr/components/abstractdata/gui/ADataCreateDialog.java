package com.hust.ebr.components.abstractdata.gui;

import com.hust.ebr.components.abstractdata.controller.IDataManageController;

import javax.swing.*;
import java.awt.*;

public abstract class ADataCreateDialog<T> extends JDialog {

    protected T t;
    protected GridBagLayout layout;
    protected GridBagConstraints c = new GridBagConstraints();

    public ADataCreateDialog(IDataManageController<T> controller) {
        super((Frame) null, "Edit", true);

        setContentPane(new JPanel());
        layout = new GridBagLayout();
        getContentPane().setLayout(layout);
        c.fill = GridBagConstraints.HORIZONTAL;
        this.buildControls();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            T newT = getNewData();
            if (newT != null) {
                controller.onAct(newT);
                ADataCreateDialog.this.dispose();
            }
        });

        c.gridx = 1;
        c.gridy = getLastRowIndex();
        getContentPane().add(saveButton, c);

        pack();
        setLocationRelativeTo(null);
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
