//Setup using the Window Builder

package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.brainmentors.chatapp.dao.UserDAO;
import com.brainmentors.chatapp.dto.UserDTO;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
			
	}
	
	UserDAO userDAO = new UserDAO();
	
	private void doLogin() {
		String userid = useridtxt.getText();
		char []password = passwordField.getPassword();  // ClassName@HashCode(HexaDecimal)
		
		UserDTO userDTO = new UserDTO(userid,password);
		try {
			String message ="";
			if(userDAO.isLogin(userDTO)) {
				message ="Welcome "+userid;
				JOptionPane.showMessageDialog(this,message );
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message="Invalid Userid and Password";
				JOptionPane.showMessageDialog(this,message );
			}
			//JOptionPane.showMessageDialog(this,message );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void register() {
		String userid = useridtxt.getText();
		char []password = passwordField.getPassword();  // ClassName@HashCode(HexaDecimal)
		//UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid,password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "Register Successfully");
			//System.out.println("Record Added.....");
		}
		else {
			JOptionPane.showMessageDialog(this, "Register Failed");
			//System.out.println("Record Not Added..");
		}
		}
		catch(ClassNotFoundException|SQLException ex) {
			System.out.println("DB Issue.....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some Generic exception raised...");
			ex.printStackTrace(); // Where is the Exception
		}
		System.out.println("userid "+userid + " Password "+password);
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE);
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(269, 24, 209, 69);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(281, 136, 257, 42);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdlbl.setBounds(171, 204, 100, 42);
		getContentPane().add(pwdlbl);
		
		JLabel useridlbl = new JLabel("Userid");
		useridlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		useridlbl.setBounds(171, 136, 100, 42);
		getContentPane().add(useridlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(281, 204, 257, 42);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginbt.setBounds(230, 283, 107, 42);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerbt.setBounds(383, 283, 107, 42);
		getContentPane().add(registerbt);
		setBackground(Color.WHITE);
		setSize(755, 408);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
