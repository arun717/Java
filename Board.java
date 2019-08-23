import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
public class Board extends WindowAdapter implements ActionListener
{
	Player p = new Player();											// creates a Player object
	
    private int count =0;												// count to check how many moves are done in all
    private boolean win = false;										// tells you when any player has won already
	private static int cnt = 1;
	
	private JFrame f;													// Holds the JFrame
	private String letter="";   										// temporary name storage for x & y and also player names
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	private int[][] winCombinations = new int[][]						//winning conditions
			{       
			{0, 1, 2}, {3, 4, 5}, {6, 7, 8},{0, 3, 6}, {1, 4, 7}, {2, 5, 8},{0, 4, 8}, {2, 4, 6} 							 
			};
	

	private JButton buttons[] =	new JButton[9];							// The 9 buttons to play tic tac toe on the JFrame
	
	public JButton[] getButtons() {
		return buttons;
	}

	public JMenuBar mb = new JMenuBar();										// creates menubar object
	public JMenuItem newgame = new JMenuItem("New Game");						// creates menuItem New Game
	public JMenuItem instructions = new JMenuItem("Instructions");				// creates menuItem Instructions
	public JMenuItem exit = new JMenuItem("Exit Game");						// creates menuItem ExitGame
	
	public Board()														//constructor
	{			
		
		f = new JFrame("Tic Tac Toe");									// creates JFrame object
		f.setLocationRelativeTo(null);									// shifts the top left of your frame to the center of the screen
		f.addWindowListener(this);  									// adds EventListener
		
		mb.add(newgame);												// adds menuitem to menubar
		newgame.addActionListener(this);								// adds EventListener
		
		mb.add(instructions);											// adds menuitem to menubar
		instructions.addActionListener(this);							// adds EventListener
		
		mb.add(exit);													// adds menuitem to menubar
		exit.addActionListener(this);									// adds EventListener
		
		f.setJMenuBar(mb);												// adds menuBar on the JFrame
		
		for(int i=0; i<=8; i++)											// for initializing all 9 buttons to play
		{
			buttons[i] = new JButton();									// creates all 9 buttons
			f.add(buttons[i]);											// adds all 9 buttons to the frame
			buttons[i].addActionListener(this);						// adds actionListener to all 9 buttons
			buttons[i].setFont(new Font("",0,50));						// sets FontSize 50 to the text of buttons
		}

		f.setLayout(new GridLayout(3,3));								// creates a 3 by 3 grid
		f.setSize(400,400);												// sets the size of the JFrame
		f.setVisible(true);												// makes the JFrame visible
		
		p.setName();
	}// end of constructor

	
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		Object source = ae.getSource();									// gets the source object for ActionEvent
		System.out.println(source);
		if(source == newgame)
		{
			int answer = JOptionPane.showConfirmDialog(null, "Your current game will not be saved. Do you still want to continue ?", "Do you "
					+ "want to start a new game?", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)						// if user selects yes 
			{
				f.setVisible(false);
				new Tic_Tac_Toe();
			}
																		// Do nothing if user selects no
		}
		
		else if(source == instructions)									// if user clicks on Instructions MenuItem
		{
			JOptionPane.showMessageDialog(null,"The goal is to get 3 X's or O's in a row (horizontally,vertically,diagonally)","Instructions",JOptionPane.INFORMATION_MESSAGE); 
		}
		
		else if(source == exit)											// if user clicks on exit MenuItem
		{
			int answer = JOptionPane.showConfirmDialog(null, "EXIT", "Are You sure you want to exit ?",JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)						// if user confirms exit
			{
				JOptionPane.showMessageDialog(null, "Thank you " + p.x+ " and " + p.y + " for playing");
				System.exit(0);
			}															// do nothing if user doesn't want to exit
		}
		
		else
		{
			count ++;													// increment move count
			if(count % 2 == 0)
			
				letter = "O";											// even counts denotes symbol 'O'
			
			else
				letter = "X";											// odd counts denotes symbol 'X'
			
			JButton pressedButton = (JButton)source;					// to find the button where player marked 'X' or 'O' 
			pressedButton.setText(letter);								// set text of the button as 'X' or 'O' according to even/odd count
			pressedButton.setEnabled(false);   							// to prevent overwrite at the same button
			if(letter.equals("O"))
				pressedButton.setBackground(Color.YELLOW);
			else
				pressedButton.setBackground(Color.CYAN);
			

																		//determine who won
			//check within the array of arrays if any player has won on the given winning coordinates with all three symbols same
			for(int i=0; i<=7; i++)
			{
				if( buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) &&
				buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) &&
				buttons[winCombinations[i][0]].getText() != "")
				{
					win = true;											// sets win as true if someone has actually won
				}
			}
			if(win == true)
			{
				if(letter.equals("X"))									// checks the last player's move whether it was 'X' or 'O'
					letter = Player.x;											// if it was 'X' it stores player1's name in letter
				else
					letter = Player.y;											// if winning player was 'O' then stores player2 name in letter 
				JOptionPane.showMessageDialog(null, letter + " wins the game! \n Click on new game to start another one !!!");
			}
			else if(count == 9 && win == false)							// if all 9 buttons are filled and no one has won yet
			{
				JOptionPane.showMessageDialog(null, "It's a tie!\nBetter luck next time.");
			}
			else
			{}
		}// end of else
		

	}// end of action performed
}// end of Board class


