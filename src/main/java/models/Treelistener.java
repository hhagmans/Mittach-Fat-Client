package models;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import views.Main_view;

import java.util.List;

public class Treelistener implements TreeSelectionListener{
	private JTree tree;
	private JPanel panel;
	
	public Treelistener(JTree tree, JPanel panel) {
		super();
		this.tree = tree;
		this.panel = panel;
	}
	
	public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;
        
        Object eventInfo = node.getUserObject();
        
			String[] eventdetails = new String[7];
			eventdetails[0] = "ID: " + ((EventInfo) eventInfo).getID();
			eventdetails[1] = "Titel: " + ((EventInfo) eventInfo).getTitle();
			eventdetails[2] = "Date: " + ((EventInfo) eventInfo).getDate();
			eventdetails[3] = "Details: " + ((EventInfo) eventInfo).getDetails();
			eventdetails[4] = "Slots: " + ((EventInfo) eventInfo).getSlots();
			eventdetails[5] = "Vegetarian: " + ((EventInfo) eventInfo).isVegetarian_opt();
			String book = "Bookings: ";
			for (int i = 0; i < ((EventInfo) eventInfo).getBookings().size(); i++) {
				book = book + ((EventInfo) eventInfo).getBookings().get(i).getUser().getShortname() + ", ";
			}
			eventdetails[6] = book;
			Main_view.displayEvent(eventdetails);
    }
}
