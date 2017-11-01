package smoltwit;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewUser {

	private User user;
	private String userID;
	private JFrame uvFrame;
	private JTextField userTextField;
	private JButton postTweetButton;
	private JTextField sendTweetTxtField;
	private JTextPane followingPane;

	/**
	 * Launch the application.
	 * @param string 
	 */

	public void run(String user) {
		try {
			ViewUser window = new ViewUser(user);
			window.uvFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ViewUser(String person) { // creates the application
		this.userID = person;
		if(SmallTwit.userMap.containsKey(person)){
			this.user = SmallTwit.userMap.get(person);
		}
		initialize();
	}

	private void initialize() { // initializes frame contents
		uvFrame = new JFrame();
		uvFrame.setTitle("User ID");
		uvFrame.setBounds(100, 100, 505, 501);
		uvFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uvFrame.getContentPane().setLayout(null);
		
		final JTextPane txtpnNewsFeed = new JTextPane();
		txtpnNewsFeed.setText("Twit NewsFeed");
		txtpnNewsFeed.setBounds(10, 275, 469, 177);
		uvFrame.getContentPane().add(txtpnNewsFeed);
		
		followingPane = new JTextPane();
		followingPane.setText("Current Following");
		followingPane.setBounds(10, 70, 469, 135);
		uvFrame.getContentPane().add(followingPane);
		
		JButton btnFollowUser = new JButton("Follow User");
		btnFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.addFollower(userTextField.getText());
				
				String followers = user.getFollowers();
				followingPane.setText(followers);		
				
				System.out.println("Follor User: " + userTextField.getText());
			}
		});
		btnFollowUser.setBounds(247, 11, 232, 48);
		uvFrame.getContentPane().add(btnFollowUser);
		
		userTextField = new JTextField();
		userTextField.setText("User Id");
		userTextField.setBounds(10, 11, 223, 48);
		uvFrame.getContentPane().add(userTextField);
		userTextField.setColumns(10);
		
		postTweetButton = new JButton("Post Tweet");
		postTweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.twit(sendTweetTxtField.getText());
				String news = user.getTweets();
				txtpnNewsFeed.setText(news);				
				}
		});
		postTweetButton.setBounds(275, 216, 204, 48);
		uvFrame.getContentPane().add(postTweetButton);
		
		sendTweetTxtField = new JTextField();
		sendTweetTxtField.setText("Tweet Message");
		sendTweetTxtField.setBounds(10, 216, 255, 48);
		uvFrame.getContentPane().add(sendTweetTxtField);
		sendTweetTxtField.setColumns(10);

	}
}
