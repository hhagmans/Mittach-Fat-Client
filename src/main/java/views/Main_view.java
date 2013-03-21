package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import models.Event;
import models.EventInfo;

import services.Htmlservices;
import services.Services;

public class Main_view {

	public JFrame initializeFrame(String name,Dimension size, Point location){
		final JFrame jfrm=new JFrame(name);

		jfrm.setSize(size);
		
		jfrm.setLocation(location);
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jfrm.setLayout(new FlowLayout());
		return jfrm;
	}
	
	private void createNodes(DefaultMutableTreeNode top) {	    
	    Services serv = new Htmlservices();
	    ArrayList<Event> events = (ArrayList<Event>) serv.listEvents();
	    Iterator<Event> iter = events.iterator();
	    while (iter.hasNext()){
	    top.add(new DefaultMutableTreeNode(new EventInfo(iter.next())));
	    }
	}
	
public void display(){
		
		
		final JFrame jfrm=initializeFrame("Mittach Application Client", new Dimension(650,400), new Point(500, 350));
		jfrm.setLayout(new FlowLayout(FlowLayout.LEFT));
		DefaultMutableTreeNode events =
		        new DefaultMutableTreeNode("Events");
		createNodes(events);
		JTree eventTree = new JTree(events);
		JScrollPane treepanel = new JScrollPane(eventTree);
		treepanel.setPreferredSize( new Dimension( 300, 350 ) );
		treepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		jfrm.getContentPane().add(treepanel);
		JPanel eventpanel = new JPanel();
		eventpanel.setPreferredSize(new Dimension( 300, 350 ));
		eventpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		jfrm.getContentPane().add(eventpanel);
		treepanel.setVisible(true);
		eventpanel.setVisible(true);
		jfrm.setVisible(true);
		
		
		
	}
	public static void main(String j[]){
		//creating thread
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main_view obj=new Main_view();
				obj.display();
				
			}
		});
	}
	
}
