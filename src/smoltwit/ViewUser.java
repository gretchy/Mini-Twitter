package smoltwit;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

// Composite is implemented in this class
public class ViewUser {

	private User user;
	private String userID;
	private JFrame uvFrame;
	
	public ViewUser(String person) { // creates the application
		userID = person;
		if(SmallTwit.allUsers.containsKey(person)){
			this.user = SmallTwit.allUsers.get(person);
		}
		initialize();
	}

	public void run(String user) {
		try {
			ViewUser popup = new ViewUser(user);
			popup.uvFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() { // initializes frame contents
		uvFrame = new JFrame();
		uvFrame.setTitle(userID);
		uvFrame.setBounds(1050, 195, 505, 501);
		uvFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uvFrame.getContentPane().setLayout(null);
		
		// displays for following, followers, and newsfeed
		JTextArea followingPane = new JTextArea();
		JTextArea followersPane = new JTextArea();
		JTextArea tweetTextField = new JTextArea();
		ActionListener constantRefresh = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
        		followingPane.setText("----- Currently Following Users -----");
        		followingPane.setBounds(10, 72, 230, 135);
        		uvFrame.getContentPane().add(followingPane);
        		followingPane.append("\n" + user.getFollowing());
        		
        		
        		followersPane.setText("----- Your Followers -----");
        		followersPane.setBounds(249, 72, 230, 135);
        		uvFrame.getContentPane().add(followersPane);
        		followersPane.append("\n" + user.getFollowers());
        		
        		
        		tweetTextField.setText("----- Twit NewsFeed -----");
        		tweetTextField.setBounds(10, 278, 469, 177);
        		uvFrame.getContentPane().add(tweetTextField);
        		tweetTextField.append("\n" + user.getTweets());
            }
		};
		Timer timer = new Timer(100 ,constantRefresh); // Execute task each 100 miliseconds
		timer.setRepeats(true);
		timer.start();
		
		
		
		// section to follow a new user
		JTextField userTextField = new JTextField();
		userTextField.setText("User Id");
		userTextField.setBounds(10, 11, 223, 60);
		uvFrame.getContentPane().add(userTextField);
		userTextField.setColumns(10);
		
		JButton followButton = new JButton("Follow User");
		followButton.setFont(new Font("Arial",Font.BOLD,20));
		followButton.setBackground(new Color(97,237,211));
		followButton.setForeground(new Color(85,89,88));
		followButton.setBorderPainted(false);
		
		followButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SmallTwit.allUsers.containsKey(userTextField.getText())){
					if(userTextField.getText().equals(userID)) {
						JOptionPane.showMessageDialog(followButton, "Cannot follow yourself.");
					}
					else {
						user.startFollowing(userTextField.getText());
						SmallTwit.allUsers.get(userTextField.getText()).addFollower(userID);;

						String following = "\n  " + userTextField.getText();
						followingPane.append(following);
					}
				}
				else {
					JOptionPane.showMessageDialog(followButton, "User doesn't exist.");
				}
			}
		});
		followButton.setBounds(247, 11, 225, 60);
		uvFrame.getContentPane().add(followButton);
		
		// section for user to send a tweet
		JTextField sendTweetTxtField = new JTextField();
		sendTweetTxtField.setText("Tweet Message");
		sendTweetTxtField.setBounds(10, 216, 255, 60);
		uvFrame.getContentPane().add(sendTweetTxtField);
		sendTweetTxtField.setColumns(10);
		
		JButton postTweetButton = new JButton("Post Tweet");
		postTweetButton.setFont(new Font("Arial",Font.BOLD,20));
		postTweetButton.setBackground(new Color(128,172,229));
		postTweetButton.setForeground(new Color(82,91,102));
		postTweetButton.setBorderPainted(false);
		
		postTweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String news = "\n " + userID + ": " + sendTweetTxtField.getText();
				user.twit(news);
				tweetTextField.append(news);				
			}
		});
		postTweetButton.setBounds(275, 216, 204, 60);
		uvFrame.getContentPane().add(postTweetButton);
	}
}
