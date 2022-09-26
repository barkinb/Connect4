package game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class GameWindow extends JFrame implements ActionListener {
	
	private GameButton[][] buttons;
	private JButton resetButton;
	private JLabel statusLabel;
	private int round = 0; //the roundno 
	private int currentPlayer = 0; //the current player 
	public GameWindow(int size) {
		super("Let's play a game!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		 //initial round 
		
		
		statusLabel = new JLabel("Player" + (currentPlayer+1) +"'s turn"); //states whose turn it is 
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(size, size));
		
		buttons = new GameButton[size][];
		for (int i = 0; i < size; i++) {
			buttons[i] = new GameButton[size];
			for (int j = 0; j < size; j++) {
				GameButton button = new GameButton();
				button.setFont(button.getFont().deriveFont(25.0f));
				button.setPreferredSize(new Dimension(100, 100));
				buttonPanel.add(button);
				buttons[i][j] = button;
				button.addActionListener(this);
				
			}	
		}
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(statusLabel, BorderLayout.NORTH);
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		resetButton = new JButton("Reset");
		getContentPane().add(resetButton, BorderLayout.SOUTH);
		resetButton.addActionListener(this); //invokes the action listener 
		
		pack();
	}
	
    /**
     * Main method -- just creates and displays the window.
     */
    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameWindow(8).setVisible(true);
			}
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(resetButton)) {
			for (int i = 0; i < this.buttons.length; i++){ //loops through 
				for (int j = 0; j < this.buttons.length; j++) {
					buttons[i][j].reset(); //resets the buttons 
					round = 0; //resets the round 
					currentPlayer = 0; //resets the current player
					statusLabel.setText("Player" + (currentPlayer+1) +"'s turn");
				}
			}
		}else {
			Boolean playerWon = false; //true if someone won 
			GameButton button =(GameButton)e.getSource(); //gets the button clicked 
			if (e.getSource().equals(button)) {
				button.setSymbol(String.valueOf(currentPlayer)); //sets the user name on the button 
				for (int i = 0; i < this.buttons.length-1; i++){ //loops from 0 to length-1 since we need to check the next line as well
					for (int j = 0; j < this.buttons.length-1; j++) {
						if ((this.buttons[i][j].getSymbol()!=null)&&(this.buttons[i+1][j].getSymbol()!=null)&&(this.buttons[i][j+1].getSymbol()!=null)&&(this.buttons[i+1][j+1].getSymbol()!=null)) { //checks if they are not null and not the empty string 
							if (!(this.buttons[i][j].getSymbol().equals(""))&&!(this.buttons[i+1][j].getSymbol().equals(""))&&!(this.buttons[i][j+1].getSymbol().equals(""))&&!(this.buttons[i+1][j+1].getSymbol().equals(""))) {
								
								if ((this.buttons[i][j].getSymbol().equals(this.buttons[i][j+1].getSymbol()))&&(this.buttons[i][j].getSymbol().equals(this.buttons[i+1][j].getSymbol()))&&(this.buttons[i+1][j].getSymbol().equals(this.buttons[i+1][j+1].getSymbol()))) {
									statusLabel.setText("Player " + (round %2 +1)+" won"); //prints that someone won
									playerWon = true;
									
								}
							}
						}
					}
					
				}
				round += 1; //the round is incremented 
				currentPlayer = round%2; //updates it 
				if (playerWon) {
					for (int k = 0; k < this.buttons.length; k++){ //loops from 0 to end to disable all 
						for (int l = 0; l < this.buttons.length; l++) {
							this.buttons[k][l].setEnabled(false); //disables all
						}
					}
				}
				if (!playerWon){//if nobody wins prints the msg that it is next player's turn
					statusLabel.setText("Player"+(currentPlayer+1)+"'s turn");
				}
				
				if (!playerWon && ((this.buttons.length*this.buttons.length)==round)) { //if nobody won and the number of rounds is equal to the number of squares it prints draw
					statusLabel.setText("Draw");
				}
				
			}
		}
		
		getContentPane().add(statusLabel, BorderLayout.NORTH);
	}
}

