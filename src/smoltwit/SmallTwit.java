package smoltwit;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

//Singleton is implemented in this class
public class SmallTwit {
	
	public static Map<String, User> userMap = new HashMap<String, User>();
	private int totalUsers, totalGroups, totalTweets; // stores total num of users, total num of groups, total num of tweets
	private double positiveTweets; // stores % of positive Tweets in all users' newsfeeds
	
	private static SmallTwit instance = null;
	private JFrame twitterFrame;
	private JTextField userTextField, groupTextField, userViewTextField;
	
	protected static SmallTwit getInstance(){ // Singleton implementation
		if(instance == null){
			instance = new SmallTwit();
			instance.run();			
		}
		return instance;
	}
	
	public SmallTwit() { // creates the actual application
		initializeFrame();
	}
	
	private void run() { // in charge of launching Admin Control
		try {
			SmallTwit window = new SmallTwit();
			window.twitterFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeFrame() { // initializes the frame
		twitterFrame = new JFrame();
		twitterFrame.setTitle("Small Twitter");
		twitterFrame.setBounds(200, 200, 850, 600);
		twitterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		twitterFrame.getContentPane().setLayout(null);
		
		//Text Pane for output
		JTextPane outputPane = new JTextPane();
		outputPane.setText("[all outputs shown here]");
		outputPane.setBounds(394, 218, 420, 110);
		twitterFrame.getContentPane().add(outputPane);
		
		//Tree
		//TODO:
		JTree TreeView = new JTree();		
		TreeView.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Root") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					add(new DefaultMutableTreeNode("john"));
					add(new DefaultMutableTreeNode("bob"));
					add(new DefaultMutableTreeNode("steve"));
					node_1 = new DefaultMutableTreeNode("CS356");
						node_1.add(new DefaultMutableTreeNode("stu1"));
						node_1.add(new DefaultMutableTreeNode("stu2"));
						node_1.add(new DefaultMutableTreeNode("stu3"));
						node_2 = new DefaultMutableTreeNode("CS356Session01");
							node_2.add(new DefaultMutableTreeNode("stu8"));
							node_2.add(new DefaultMutableTreeNode("stu9"));
							node_2.add(new DefaultMutableTreeNode("stu10"));
						node_1.add(node_2);
						node_1.add(new DefaultMutableTreeNode("stu4"));
					add(node_1);
					add(new DefaultMutableTreeNode("oostu"));
					add(new DefaultMutableTreeNode("ppstu2"));
				}
			}
		)
		);
					
		TreeView.setBounds(10, 16, 375, 472);
		twitterFrame.getContentPane().add(TreeView);
	
		// Add User section
		userTextField = new JTextField();
		userTextField.setText("Enter User ID");
		userTextField.setBounds(395, 11, 182, 60);
		twitterFrame.getContentPane().add(userTextField);
		userTextField.setColumns(10);
		
		JButton addUserButton = new JButton("Add User");
		addUserButton.setFont(new Font("Arial",Font.BOLD,20));
		addUserButton.setBackground(new Color(97,237,211));
		addUserButton.setForeground(new Color(85,89,88));
		addUserButton.setBorderPainted(false);
		
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				User newUser = new User();
				newUser.setID(userTextField.getText());
				if (!userMap.containsKey(userTextField.getText())) {
					userMap.put(userTextField.getText(), newUser);
				}
				outputPane.setText("Added User " + newUser.getID());
			}
		}
		);
		addUserButton.setBounds(587, 11, 182, 60);
		twitterFrame.getContentPane().add(addUserButton);
	
		// Add User Group section
		groupTextField = new JTextField();
		groupTextField.setText("Enter User Group Id");
		groupTextField.setBounds(395, 80, 182, 60);
		twitterFrame.getContentPane().add(groupTextField);
		groupTextField.setColumns(10);
		
		JButton addGroupButton = new JButton("Add Group");
		addGroupButton.setFont(new Font("Arial",Font.BOLD,20));
		addGroupButton.setBackground(new Color(97,237,211));
		addGroupButton.setForeground(new Color(85,89,88));
		addGroupButton.setBorderPainted(false);
		
		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				UserGroup group = new UserGroup();
				group.setID(groupTextField.getText());
				outputPane.setText("Added Group " + group.getID());
			}
		}
		);
		addGroupButton.setBounds(587, 80, 182, 60);
		twitterFrame.getContentPane().add(addGroupButton);
		
		//View User Button
		JButton viewUserButton = new JButton("Open User View");
		viewUserButton.setFont(new Font("Arial",Font.BOLD,20));
		viewUserButton.setBackground(new Color(97,237,211));
		viewUserButton.setForeground(new Color(85,89,88));
		viewUserButton.setBorderPainted(false);
		
		viewUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!userMap.containsKey(userViewTextField.getText())) {
					outputPane.setText(userViewTextField.getText() + " doesnt exist.");
				}
				else {
					ViewUser openUV = new ViewUser(userViewTextField.getText());
					openUV.run(userViewTextField.getText());
					outputPane.setText("User View " + userViewTextField.getText());
				}
			}
		}
		);
		viewUserButton.setBounds(395, 150, 375, 60);
		twitterFrame.getContentPane().add(viewUserButton);
	
		// section to display total amount of users
		JButton totalUsersButton = new JButton("Total Users");
		totalUsersButton.setFont(new Font("Arial",Font.BOLD,20));
		totalUsersButton.setBackground(new Color(128,172,229));
		totalUsersButton.setForeground(new Color(82,91,102));
		totalUsersButton.setBorderPainted(false);
		
		totalUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalUsers = User.getUserTotal();
				outputPane.setText("Number of users: " + totalUsers);
			}
		});
		totalUsersButton.setBounds(395, 345, 182, 66);
		twitterFrame.getContentPane().add(totalUsersButton);
	
		// section to display total amount of user groups
		JButton totalGroupsButton = new JButton("Show Group Total");
		totalGroupsButton.setFont(new Font("Arial",Font.BOLD,20));
		totalGroupsButton.setBackground(new Color(128,172,229));
		totalGroupsButton.setForeground(new Color(85,89,88));
		totalGroupsButton.setBorderPainted(false);
		
		totalGroupsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalGroups = UserGroup.getGroupTotal();
				outputPane.setText("Number of groups: " + totalGroups);
			}
		});
		totalGroupsButton.setBounds(587, 345, 230, 66);
		twitterFrame.getContentPane().add(totalGroupsButton);
		
		// section to display total messages
		JButton totalTweetsButton = new JButton("Total Tweets");
		totalTweetsButton.setFont(new Font("Arial",Font.BOLD,20));
		totalTweetsButton.setBackground(new Color(128,172,229));
		totalTweetsButton.setForeground(new Color(82,91,102));
		totalTweetsButton.setBorderPainted(false);
		
		totalTweetsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalTweets = User.getTweetTotal();
				outputPane.setText("Number of tweets: " + totalTweets);
			}
		});
		totalTweetsButton.setBounds(395, 422, 182, 66);
		twitterFrame.getContentPane().add(totalTweetsButton);
	
		// section to display percentage of positive tweets
		JButton posPercentageButton = new JButton("% Positive Tweets");
		posPercentageButton.setFont(new Font("Arial",Font.BOLD,20));
		posPercentageButton.setBackground(new Color(128,172,229));
		posPercentageButton.setForeground(new Color(82,91,102));
		posPercentageButton.setBorderPainted(false);
		
		posPercentageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Positivity currentsesh = new Positivity();
				positiveTweets = currentsesh.getPercentage();
				outputPane.setText("Percentage of positive Tweets : " + positiveTweets + "%");
			}
		});
		posPercentageButton.setBounds(587, 422, 230, 66);
		twitterFrame.getContentPane().add(posPercentageButton);			
	}
}
