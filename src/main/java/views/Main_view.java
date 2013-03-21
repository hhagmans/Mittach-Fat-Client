package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.Event;
import models.EventInfo;
import models.Treelistener;
import services.Htmlservices;
import services.Services;

public class Main_view {

	 JFrame jfrm;
	 JTree eventTree;
	 JScrollPane treepanel;
	 JPanel eventpanel;
	 static JList eventDetails;
	
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
	
	public static void displayEvent(String[]eventdetails){
		eventDetails.setListData(eventdetails);
	}
	
public void display(){
		
		
		jfrm = initializeFrame("Mittach Application Client", new Dimension(650,400), new Point(500, 350));
		jfrm.setLayout(new FlowLayout(FlowLayout.LEFT));
		DefaultMutableTreeNode events =
		        new DefaultMutableTreeNode("Events");
		createNodes(events);
		eventTree = new JTree(events);
		
		treepanel = new JScrollPane(eventTree);
		treepanel.setPreferredSize( new Dimension( 300, 350 ) );
		treepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		jfrm.getContentPane().add(treepanel);
		
		eventpanel = new JPanel();
		eventpanel.setPreferredSize(new Dimension( 300, 350 ));
		eventpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		jfrm.getContentPane().add(eventpanel);
		
		TreeSelectionListener treeListener = new Treelistener(eventTree, eventpanel);
		eventTree.addTreeSelectionListener(treeListener);
		
		eventDetails = new JList(new String[]{"Wähle Event"});
		eventDetails.setVisible(true);
		eventpanel.add(eventDetails);
		
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
