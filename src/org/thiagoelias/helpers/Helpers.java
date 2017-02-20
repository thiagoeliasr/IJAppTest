package org.thiagoelias.helpers;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thiagopters on 19/02/17.
 */
public class Helpers {

    public static void showWarning(String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Alert");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void showError(String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static void showInformation(String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Information");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

}
