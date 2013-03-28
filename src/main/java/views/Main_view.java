package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
	 JFrame eventjfrm;
	 JTree eventTree;
	 JScrollPane treepanel;
	 JPanel eventpanel;
	 DefaultMutableTreeNode events;
	 static JList eventDetails;
	 static JButton deletebutton;
	 static JButton createbutton;
	 
	 Services serv = new Htmlservices();
	
	public JFrame initializeFrame(String name,Dimension size, Point location){
		final JFrame jfrm=new JFrame(name);

		jfrm.setSize(size);
		
		jfrm.setLocation(location);
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jfrm.setLayout(new FlowLayout());
		return jfrm;
	}
	
	private void createNodes(DefaultMutableTreeNode top) {	    
	    ArrayList<Event> events = (ArrayList<Event>) serv.listEvents();
	    Iterator<Event> iter = events.iterator();
	    while (iter.hasNext()){
	    top.add(new DefaultMutableTreeNode(new EventInfo(iter.next())));
	    }
	}
	
	public static void displayEvent(String[]eventdetails){
		eventDetails.setListData(eventdetails);
		deletebutton.setVisible(true);
	}
	
	public void deleteEvent(){
		String substr = eventDetails.getModel().getElementAt(0).toString();
		long ID = Long.parseLong(substr.substring(4, substr.length()));
		serv.deleteEvent(ID);
		eventDetails.setListData(new String[]{"Wähle Event"});
		deletebutton.setVisible(false);
		updateEventTree();
	}
	
	public void updateEventTree() {
		events = new DefaultMutableTreeNode("Events");
		createNodes(events);
		eventTree = new JTree(events);
		treepanel = new JScrollPane(eventTree);
		treepanel.setPreferredSize( new Dimension( 300, 350 ) );
		treepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		jfrm.getContentPane().remove(0);
		jfrm.getContentPane().add(treepanel,0);
		TreeSelectionListener treeListener = new Treelistener(eventTree, eventpanel);
		eventTree.addTreeSelectionListener(treeListener);
	}
	
	public void createEvent(String title, String details, String date, String slots, boolean vegetarian){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Event event = null;
		int slotsint = 0;
		try{
			slotsint = Integer.parseInt(slots);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			slotsint = -1;
		}

	    try {
			event = new Event((long) 1,title, details, (Date)formatter.parse(date), slotsint, vegetarian); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    if (event != null)
	    serv.createEvent(event);
	    eventjfrm.setVisible(false);
	    updateEventTree();
	    jfrm.setVisible(true);
	}
	
	public void createEventFrame(){
		eventjfrm = initializeFrame("Erstelle Event", new Dimension(270,170), new Point(400, 250));
		final JTextField eventTitleField = new JTextField();
		eventTitleField.setPreferredSize(new Dimension(200,20));
		final JLabel titleLabel = new JLabel("Title:");
		
		final JTextField eventDetailsField = new JTextField();
		eventDetailsField.setPreferredSize(new Dimension(200,20));
		final JLabel detailLabel = new JLabel("Details:");
		
		final JTextField eventDateField = new JTextField();
		eventDateField.setPreferredSize(new Dimension(100,20));
		final JLabel dateLabel = new JLabel("Date(YYYY-MM-DD):");
		
		final JTextField eventSlotsField = new JTextField();
		eventSlotsField.setPreferredSize(new Dimension(50,20));
		final JLabel slotsLabel = new JLabel("Slots:");
		
		final JCheckBox vegetarian = new JCheckBox("Vegetarian");
		
		
		
		final JButton createButton = new JButton("Create");
        ActionListener createE = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
				createEvent(eventTitleField.getText(), eventDetailsField.getText(), eventDateField.getText(), eventSlotsField.getText(), vegetarian.isSelected());
            }
          };
        createButton.addActionListener( createE );
		
        eventjfrm.getContentPane().add(titleLabel);
		eventjfrm.getContentPane().add(eventTitleField);
		eventjfrm.getContentPane().add(detailLabel);
		eventjfrm.getContentPane().add(eventDetailsField);
		eventjfrm.getContentPane().add(dateLabel);
		eventjfrm.getContentPane().add(eventDateField);
		eventjfrm.getContentPane().add(slotsLabel);
		eventjfrm.getContentPane().add(eventSlotsField);
		eventjfrm.getContentPane().add(vegetarian);
		eventjfrm.getContentPane().add(createButton);
		
		eventjfrm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		eventjfrm.setVisible(true);
	}
	
	public void display(){
		
		
		jfrm = initializeFrame("Mittach Application Client", new Dimension(650,430), new Point(400, 250));
		jfrm.setLayout(new FlowLayout(FlowLayout.LEFT));
		events = new DefaultMutableTreeNode("Events");
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
		
		deletebutton = new JButton("Delete");
		deletebutton.setVisible(false);
		ActionListener delete = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
            	deleteEvent();
            }
          };
        deletebutton.addActionListener( delete );
        eventpanel.add(deletebutton);
        
		treepanel.setVisible(true);
		eventpanel.setVisible(true);
		
        createbutton = new JButton("Create Event");
		createbutton.setVisible(true);
		ActionListener create = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
            	createEventFrame();
            }
          };
        createbutton.addActionListener( create );
        jfrm.getContentPane().add(createbutton);
		
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
