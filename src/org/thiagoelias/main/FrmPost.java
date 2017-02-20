package org.thiagoelias.main;

import org.thiagoelias.helpers.Helpers;
import org.thiagoelias.helpers.WebService;
import org.thiagoelias.models.Post;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thiago Elias on 19/02/17.
 */
public class FrmPost implements ActionListener{
    private JTextField txtId;
    private JButton btnProcessar;
    private JTextArea txtBody;
    private JPanel mainPanel;
    private JTextField txtTitle;

    public static void main(String[] args) {

        int id = 0;
        if (args.length > 0) {
            id = Integer.valueOf(args[0]);
        }

        JFrame frame = new JFrame("FrmPost");
        frame.setContentPane(new FrmPost(id).mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500); //Setting the screen size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(
                dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2); //centering

        frame.setVisible(true);
    }

    public FrmPost(int id) {
        btnProcessar.addActionListener(this);
        txtBody.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtId.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (id > 0) {
            txtId.setText(String.valueOf(id));
            setValues(id);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Fetch")) {
            try {
                setValues(Integer.parseInt(txtId.getText()));
            } catch (NumberFormatException nfe) {
                Helpers.showWarning("You must type a valid ID number");
            }
        }
    }

    private void setValues(final int idPost) {
        new Thread() {
            public void run() {
                WebService ws = new WebService();
                Post post = ws.fetchPost(idPost);
                txtTitle.setText(post.getTitle());
                txtBody.setText(post.getBody());
            }
        }.start();
    }
}
