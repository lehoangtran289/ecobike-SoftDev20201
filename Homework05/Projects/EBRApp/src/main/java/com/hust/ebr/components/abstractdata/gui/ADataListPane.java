package com.hust.ebr.components.abstractdata.gui;

import com.hust.ebr.components.abstractdata.controller.ADataPageController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ADataListPane<T> extends JScrollPane {

    private LayoutManager layout;
    protected JPanel pane;

    protected ADataPageController<T> dataPageController;

    public ADataListPane() {
        pane = new JPanel();
        layout = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(layout);

        this.setViewportView(pane);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.getVerticalScrollBar().setUnitIncrement(20);
        this.getHorizontalScrollBar().setUnitIncrement(20);
    }

    public abstract void decorateSinglePane(ADataSinglePane<T> singlePane);

    public void updateData(List<? extends T> list) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();

        for (T t : list) {
            ADataSinglePane<T> singlePane = dataPageController.createSinglePane();
            decorateSinglePane(singlePane);

            singlePane.updateData(t);
            pane.add(singlePane);
            pane.add(Box.createRigidArea(new Dimension(0, 40)));
        }
    }
}
