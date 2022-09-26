package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameButton extends javax.swing.JButton{
	private Color[] colours = {Color.RED,Color.BLUE}; //the colours for the button 
	private String symbol = "";
	private String[] symbols = {"1","2"}; //the player names 
	public void setSymbol(String symbol) {
		this.symbol = symbol;
		this.setOpaque(true); //mac error xd 
		this.setBackground(colours[Integer.parseInt(this.symbol)%2]) ; //sets the colour according to the remainder, ie if the player is 1 then their colour is red 
		this.setText(symbols[Integer.parseInt(this.symbol)%2]); //sets the text according to the remainder, ie if the remainder is 1 it is p1, p2 otherwise
		this.setEnabled(false); //makes the button untouchable
	}
	public String getSymbol() {
		return this.symbol;
	}
	public void reset() {
		this.symbol = null; //sets all fields modified in setSymbol to null 
		this.setBackground(null) ;
		this.setText(null);
		this.setEnabled(true);
	}
	
	
}
