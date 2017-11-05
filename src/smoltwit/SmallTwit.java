package smoltwit;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

// Singleton is implemented in this class
public class SmallTwit {

	public static Map<String, User> allUsers = new HashMap<String, User>(); // stores all users on Small Twitter
	public static ArrayList<String> allTweets = new ArrayList<String>(); // stores all tweets on Small Twitter
	private int totalUsers = 0, totalGroups = 0, totalTweets = 0; // stores total num of users, total num of groups, total num of tweets
	
	private static SmallTwit instance = null;
	private JFrame twitterFrame;
	private JTextField userTextField, groupTextField;

	protected static SmallTwit getInstance() {
		if (instance == null) {
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
			SmallTwit sesh = new SmallTwit();
			sesh.twitterFrame.setVisible(true);
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
		
		// output text pane to display positive percentages of tweets ,etc.
		JTextArea outputPane = new JTextArea(); 
		outputPane.setText("[all outputs shown here]");
		outputPane.setBounds(394, 218, 420, 110);
		twitterFrame.getContentPane().add(outputPane);

		// creates the beginning Tree with pre-filled users, groups, tweets from assignment
		JTree TwitterTree;
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
		TwitterTree = new JTree(rootNode);

		DefaultTreeModel treeModel = (DefaultTreeModel) TwitterTree.getModel();
		rootNode.add(new DefaultMutableTreeNode("john"));
		allUsers.put("john", new User());
		rootNode.add(new DefaultMutableTreeNode("bob"));
		allUsers.put("bob", new User());
		SmallTwit.allUsers.get("bob").twit("\nbob: Horror night is good");
		SmallTwit.allUsers.get("bob").twit("\nbob: Class Registration starts");
		rootNode.add(new DefaultMutableTreeNode("steve"));
		allUsers.put("steve", new User());
		SmallTwit.allUsers.get("steve").twit("\nsteve: CS356 is cool");
		DefaultMutableTreeNode group1 = new DefaultMutableTreeNode("CS356");
		rootNode.add(group1);
		allUsers.put("CS356", new User());
		totalGroups++;
		group1.add(new DefaultMutableTreeNode("stu1"));
		allUsers.put("stu1", new User());
		group1.add(new DefaultMutableTreeNode("stu2"));
		allUsers.put("stu2", new User());
		group1.add(new DefaultMutableTreeNode("stu3"));
		allUsers.put("stu3", new User());
		DefaultMutableTreeNode group2 = new DefaultMutableTreeNode("CS356Session01");
		group1.add(group2);
		allUsers.put("CS356Session01", new User());
		totalGroups++;
		group2.add(new DefaultMutableTreeNode("stu8"));
		allUsers.put("stu8", new User());
		group2.add(new DefaultMutableTreeNode("stu9"));
		allUsers.put("stu9", new User());
		group2.add(new DefaultMutableTreeNode("stu10"));
		allUsers.put("stu10", new User());
		group1.add(new DefaultMutableTreeNode("stu4"));
		allUsers.put("stu4", new User());
		rootNode.add(new DefaultMutableTreeNode("oostu"));
		allUsers.put("oostu", new User());
		rootNode.add(new DefaultMutableTreeNode("ppstu2"));
		allUsers.put("ppstu2", new User());
		treeModel.reload();

		TwitterTree.setBounds(10, 16, 375, 472);
		twitterFrame.getContentPane().add(TwitterTree);

		// Add User section
		userTextField = new JTextField(); // text field to input user ID to add
		userTextField.setText("Enter User ID");
		userTextField.setBounds(395, 11, 182, 60);
		twitterFrame.getContentPane().add(userTextField);
		userTextField.setColumns(10);

		JButton addUserButton = new JButton("Add User"); // button to add user to tree
		addUserButton.setFont(new Font("Arial", Font.BOLD, 20));
		addUserButton.setBackground(new Color(97, 237, 211));
		addUserButton.setForeground(new Color(85, 89, 88));
		addUserButton.setBorderPainted(false);

		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!allUsers.containsKey(userTextField.getText())) { // checks to see if user is already in hashmap
					allUsers.put(userTextField.getText(), new User());
					DefaultMutableTreeNode user = new DefaultMutableTreeNode(userTextField.getText());
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) TwitterTree.getLastSelectedPathComponent();

					if (selectedNode != null) { // add user to another group
						selectedNode.add(user);
						treeModel.reload(selectedNode);
					} else { // add user to root
						rootNode.add(new DefaultMutableTreeNode(userTextField.getText()));
						treeModel.reload(rootNode);
					}
					outputPane.append("\nAdded User " + userTextField.getText());
				}
				else {
					outputPane.append("\nCould not add user. User already exists.");
				}
			}
		});

		addUserButton.setBounds(587, 11, 182, 60);
		twitterFrame.getContentPane().add(addUserButton);

		// Add User Group section
		groupTextField = new JTextField(); // text field to input user group ID to add
		groupTextField.setText("Enter User Group ID");
		groupTextField.setBounds(395, 80, 182, 60);
		twitterFrame.getContentPane().add(groupTextField);
		groupTextField.setColumns(10);

		JButton addGroupButton = new JButton("Add Group"); // button to add user group to tree
		addGroupButton.setFont(new Font("Arial", Font.BOLD, 20));
		addGroupButton.setBackground(new Color(97, 237, 211));
		addGroupButton.setForeground(new Color(85, 89, 88));
		addGroupButton.setBorderPainted(false);

		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!allUsers.containsKey(groupTextField.getText())) { // checks to see if user is already in hashmap
					allUsers.put(groupTextField.getText(), new User());
					DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(groupTextField.getText());
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) TwitterTree.getLastSelectedPathComponent();

					if (selectedNode != null) { // add group to another group
						selectedNode.add(newGroup);
						treeModel.reload(selectedNode);
					} else { // add group to root
						rootNode.add(newGroup);
						treeModel.reload(rootNode);
					}
					outputPane.append("\nAdded Group " + groupTextField.getText());
					totalGroups++;
				}
				else {
					outputPane.append("\nCould not add group. Group already exists.");
				}
			}
		});

		addGroupButton.setBounds(587, 80, 182, 60);
		twitterFrame.getContentPane().add(addGroupButton);

		// View User Button
		JButton viewUserButton = new JButton("Open User View");
		viewUserButton.setFont(new Font("Arial", Font.BOLD, 20));
		viewUserButton.setBackground(new Color(97, 237, 211));
		viewUserButton.setForeground(new Color(85, 89, 88));
		viewUserButton.setBorderPainted(false);

		viewUserButton.addMouseListener(new MouseAdapter() { // checks for location of last mouse click to know which user to open
			public void mouseClicked(MouseEvent event) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) TwitterTree.getLastSelectedPathComponent();
				ViewUser openUV = new ViewUser(selectedNode.toString());
				openUV.run(selectedNode.toString());
			}
		});

		viewUserButton.setBounds(395, 150, 375, 60);
		twitterFrame.getContentPane().add(viewUserButton);

		// section to display total amount of users
		JButton totalUsersButton = new JButton("Total Users");
		totalUsersButton.setFont(new Font("Arial", Font.BOLD, 20));
		totalUsersButton.setBackground(new Color(128, 172, 229));
		totalUsersButton.setForeground(new Color(82, 91, 102));
		totalUsersButton.setBorderPainted(false);

		totalUsersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalUsers = allUsers.size();
				outputPane.setText("\nNumber of users: " + totalUsers);
			}
		});
		totalUsersButton.setBounds(395, 345, 182, 66);
		twitterFrame.getContentPane().add(totalUsersButton);

		// section to display total amount of user groups
		JButton totalGroupsButton = new JButton("Show Group Total");
		totalGroupsButton.setFont(new Font("Arial", Font.BOLD, 20));
		totalGroupsButton.setBackground(new Color(128, 172, 229));
		totalGroupsButton.setForeground(new Color(85, 89, 88));
		totalGroupsButton.setBorderPainted(false);

		totalGroupsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputPane.setText("\nNumber of groups: " + totalGroups);
			}
		});
		totalGroupsButton.setBounds(587, 345, 230, 66);
		twitterFrame.getContentPane().add(totalGroupsButton);

		// section to display total messages
		JButton totalTweetsButton = new JButton("Total Tweets");
		totalTweetsButton.setFont(new Font("Arial", Font.BOLD, 20));
		totalTweetsButton.setBackground(new Color(128, 172, 229));
		totalTweetsButton.setForeground(new Color(82, 91, 102));
		totalTweetsButton.setBorderPainted(false);

		totalTweetsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalTweets = allTweets.size();
				outputPane.setText("\nNumber of tweets: " + totalTweets);
			}
		});
		totalTweetsButton.setBounds(395, 422, 182, 66);
		twitterFrame.getContentPane().add(totalTweetsButton);

		// section to display percentage of positive tweets
		JButton posPercentageButton = new JButton("% Positive Tweets");
		posPercentageButton.setFont(new Font("Arial", Font.BOLD, 20));
		posPercentageButton.setBackground(new Color(128, 172, 229));
		posPercentageButton.setForeground(new Color(82, 91, 102));
		posPercentageButton.setBorderPainted(false);
		
		ArrayList<String> positiveWords = new ArrayList<String>();
		positiveWords.add("good");
		positiveWords.add("fun");
		positiveWords.add("cool");
		positiveWords.add("like");
		positiveWords.add("amazing");
		positiveWords.add("love");

		posPercentageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double percentage = 0.0;
				int totalMessages = SmallTwit.allTweets.size();
				int positiveMessages = 0;
				for(String tweet: allTweets){
					for(String pos: positiveWords){
						if(tweet.toLowerCase().contains(pos)){
							positiveMessages++;
						}
					}
				}
				percentage = ((double)positiveMessages / (double)totalMessages) * 100;
				
				outputPane.setText("\nPercentage of positive Tweets : " + percentage + "%");
			}
		});
		posPercentageButton.setBounds(587, 422, 230, 66);
		twitterFrame.getContentPane().add(posPercentageButton);
	}
}
