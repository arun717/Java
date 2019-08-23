import javax.swing.JOptionPane;

public class Player {
	
	static String x = "X";												// to save Player 1 name
	static String y = "Y";	
	
	public void setName()
	{
		x = JOptionPane.showInputDialog(null,"Please Enter Name of player X: ",  " ",1);	// sets player 1 name
		y = JOptionPane.showInputDialog(null, "Please Enter Name of player O: ",  " ",1);	// sets player 2 name
		if(x==null || x.length()==0)
		{
			x = "Player 1";												// sets default name for player 1
		}
		if(y==null || y.length()==0)
		{
			y = "Player 2";												// sets default name for player 2
		}
	}
}
