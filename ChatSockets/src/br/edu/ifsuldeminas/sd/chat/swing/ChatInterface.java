package br.edu.ifsuldeminas.sd.chat.swing;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.edu.ifsuldeminas.sd.chat.ChatException;
import br.edu.ifsuldeminas.sd.chat.ChatFactory;
import br.edu.ifsuldeminas.sd.chat.MessageContainer;
import br.edu.ifsuldeminas.sd.chat.Sender;

public class ChatInterface extends JFrame implements MessageContainer {

	private static final long serialVersionUID = 1L;
	private JTextField localPortField, remotePortField, messageField, nameField;
	private JTextArea chatArea;
	private JButton connectButton, sendButton;
	private Sender sender;
	private String userName;

	public ChatInterface() {
		setTitle("Chat Interface - UDP");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		JPanel topPanel = new JPanel(new java.awt.GridLayout(2, 4, 5, 5));
		topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		topPanel.add(new JLabel("Name:"));
		nameField = new JTextField();
		topPanel.add(nameField);

		topPanel.add(new JLabel("Local Port:"));
		localPortField = new JTextField();
		topPanel.add(localPortField);

		topPanel.add(new JLabel("Remote Port:"));
		remotePortField = new JTextField();
		topPanel.add(remotePortField);

		topPanel.add(new JPanel());

		connectButton = new JButton("Connect");
		topPanel.add(connectButton);

		add(topPanel, BorderLayout.NORTH);

		chatArea = new JTextArea();
		chatArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatArea);
		add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
		messageField = new JTextField();
		bottomPanel.add(messageField, BorderLayout.CENTER);
		sendButton = new JButton("Send Message");
		bottomPanel.add(sendButton, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);

		messageField.setEnabled(false);
		sendButton.setEnabled(false);

		setupActionListeners();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setupActionListeners() {
		connectButton.addActionListener(e -> connect());
		sendButton.addActionListener(e -> sendMessage());
		messageField.addActionListener(e -> sendMessage());
	}

	private void connect() {
		try {
			userName = nameField.getText().trim();
			int localPort = Integer.parseInt(localPortField.getText().trim());
			int serverPort = Integer.parseInt(remotePortField.getText().trim());

			if (userName.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please, type your name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.sender = ChatFactory.build("localhost", serverPort, localPort, this);

			nameField.setEditable(false);
			localPortField.setEditable(false);
			remotePortField.setEditable(false);
			connectButton.setEnabled(false);
			messageField.setEnabled(true);
			sendButton.setEnabled(true);
			chatArea.append("******\nConnected successfully!\n******\n\n");

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Ports must be valid numbers.", "Format error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ChatException ex) {
			JOptionPane.showMessageDialog(this, "Error connecting: " + ex.getCause().getMessage(), "Connection Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sendMessage() {
		String messageText = messageField.getText().trim();
		if (!messageText.isEmpty() && sender != null) {
			try {
				String messageToSend = String.format("%s%s%s", messageText, MessageContainer.FROM, userName);
				sender.send(messageToSend);

				chatArea.append(String.format("I:\n %s\n\n", messageText));
				messageField.setText("");
			} catch (ChatException ex) {
				JOptionPane.showMessageDialog(this, "Error sending message: " + ex.getMessage(), "Error sending",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void newMessage(String message) {
		if (message == null || message.trim().isEmpty()) {
			return;
		}

		String[] messageParts = message.split(MessageContainer.FROM);
		String content = messageParts[0];
		String from = (messageParts.length > 1) ? messageParts[1].trim() : "Desconhecido";

		SwingUtilities.invokeLater(() -> {
			chatArea.append(String.format("%s:\n %s\n\n", from, content));
			chatArea.setCaretPosition(chatArea.getDocument().getLength());
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ChatInterface::new);
	}
}
