package org.thiagoelias.main;

import org.thiagoelias.helpers.WebService;
import org.thiagoelias.models.Post;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Thiago Elias on 19/02/17.
 */
public class FrmListPosts implements ListSelectionListener {
    private JPanel postsPanel;
    private JTable tblPosts;
    private ArrayList<Post> posts;

    public static void main(String[] args) {
        UIManager.put("Table.gridColor", new ColorUIResource(Color.gray));
        JFrame frame = new JFrame("Posts List");
        frame.setContentPane(new FrmListPosts().postsPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600); //Setting the screen size
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(
                dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2); //centering

        frame.setVisible(true);
    }

    public FrmListPosts() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("User Id");
        model.addColumn("Title");
        tblPosts.setModel(model);
        tblPosts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblPosts.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblPosts.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblPosts.getColumnModel().getColumn(2).setPreferredWidth(500);
        tblPosts.getSelectionModel().addListSelectionListener(this);

        new Thread() {
            public void run() {
                WebService ws = new WebService();
                posts = ws.fetchAllPosts();

                for (Post post : posts) {
                    model.addRow(new Object[]{
                            post.getId(),
                            post.getUserId(),
                            post.getTitle()
                    });
                }

            }
        }.start();
    }

    public void valueChanged(ListSelectionEvent event) {
        if(!event.getValueIsAdjusting()) {
            int idSelected = tblPosts.getSelectedRow();
            FrmPost.main(new String[]{String.valueOf(posts.get(idSelected).getId())});
        }
    }
}
