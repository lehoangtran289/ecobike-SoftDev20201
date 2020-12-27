package com.hust.ebr.app.login;

import com.hust.ebr.app.admin.EBRAdmin;
import com.hust.ebr.app.admin.EBRAdminController;
import com.hust.ebr.app.user.EBRUser;
import com.hust.ebr.app.user.EBRUserController;

import javax.swing.*;

public class EBRLogin extends JFrame {

    private JPanel rootPanel;
    private JButton btnUserLogin;
    private JButton btnAdminLogin;
    private JPasswordField pfAdminPass;

    public EBRLogin() {
        initFrame();
        handleActionEvents();
    }

    private void initFrame() {
        setTitle("Eco Bike Rental");
        setContentPane(rootPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void handleActionEvents() {
        btnUserLogin.addActionListener(e -> {
            setVisible(false);
            new EBRUser(new EBRUserController());
        });

        btnAdminLogin.addActionListener(e -> {
            String password = String.valueOf(pfAdminPass.getPassword());
            checkPassword(password);
        });
    }

    /**
     * Check password for admin authentication
     * Password = "henlocheems"
     * @param password for admin
     */
    private void checkPassword(String password) {
        final String ADMIN_PASSWORD = "henlocheems";
        if (password.equals(ADMIN_PASSWORD)) {
            setVisible(false);
            new EBRAdmin(new EBRAdminController());
        } else {
            JOptionPane.showMessageDialog(null,
                    "Your password is incorrect, please try again!",
                    "WRONG PASSWORD",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(EBRLogin::new);
    }


}
