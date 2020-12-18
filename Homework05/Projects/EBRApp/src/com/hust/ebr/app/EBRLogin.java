package com.hust.ebr.app;

import com.hust.ebr.app.admin.EBRAdmin;
import com.hust.ebr.app.admin.EBRAdminController;
import com.hust.ebr.app.user.EBRUser;
import com.hust.ebr.app.user.EBRUserController;

import javax.swing.*;

public class EBRLogin {
    private JButton buttonLoginUser;
    private JButton buttonLoginAdmin;
    private JPanel panelLogin;

    public EBRLogin() {
        initActionListeners();
    }

    private static void initFrame() {
        JFrame frame = new JFrame("Eco Bike Rental");
        frame.setContentPane(new EBRLogin().panelLogin);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void initActionListeners() {
        buttonLoginAdmin.addActionListener(e -> {
            new EBRAdmin(new EBRAdminController());
        });

        buttonLoginUser.addActionListener(e -> {
            new EBRUser(new EBRUserController());
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(EBRLogin::initFrame);
    }
}
