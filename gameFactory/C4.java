package gameFactory;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.*;
public class C4 extends JFrame implements Iterator<Object>{
	protected JLabel error= new JLabel("");
	protected JLabel win= new JLabel(" ");
	protected JLabel directions= new JLabel("Enter a column 1 - 7: It's R's turn.");
	protected JTextField userIn = new JTextField(8);
	protected JButton enter = new JButton("Enter");
	protected JButton reset = new JButton("New Game");
	protected String[][] board;
	protected String gb="";
	protected int turn=0;
	protected int player=1;
	protected Font f =new Font("Cam", Font.PLAIN,44);
	protected JTextArea visual =new JTextArea(gb);
	protected int x,y;
	protected int c=0;
	protected int h=0;
	protected int v=0;
	
	//creates blank board
	public void newBoard() {
		board =new String[6][7];
		for(int x=0; x<board.length; x++) {
			for(int y=0; y<board[0].length; y++) {
				board[x][y]="|_|";
			}
		}
	}
	//Set gb to the current board for display
		public void showBoard() {
			gb=next();
			gb+="|1||2||3| |4||5||6| |7|";
			h=0;
		}
		//Sets layout and activates buttons
		C4(){
			newBoard();
			showBoard();
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(420, 520);
			JPanel boardPanel =new JPanel();
			boardPanel.setLayout(new BorderLayout());
			visual.setFont(f);
			visual.setEditable(false);
			visual.setText(gb);
			boardPanel.add(visual,"Center");
			JPanel resetPanel = new JPanel();
			resetPanel.setLayout(new BorderLayout());
			resetPanel.add(reset,"East");
			JPanel play= new JPanel();
			play.setLayout(new BorderLayout());
			play.add(directions,"North");
			play.add(error,"South");
			JPanel input=new JPanel();
			input.add(win);
			input.add(userIn);
			input.add(enter);
			play.add(input,"Center");
			gamePanel.add(resetPanel,"North");
			gamePanel.add(boardPanel,"Center");
			gamePanel.add(play,"South");
			enter.addActionListener(new ActionListener() {
	        	 public void actionPerformed(ActionEvent e) {
	        		 try {
	        	        int msg = Integer.parseInt(userIn.getText());
	        	        if((msg<8 && msg>0)&&c%2==0) {
	        	        	x=msg-1;
	        	        	c++;
	        	        	directions.setText("Enter a column 1 - 7: It's Y's turn.");
	        	        	userIn.setText("");
	        	        	error.setText("");
	        	        	marks(x);
	        	        	showBoard();
	        	        	win();
	        	        	visual.setText(gb);
	        	        }
	        	        else if(msg<8 && msg>0&&c%2!=0) {
	        	        	x=msg-1;
	        	        	c++;
	        	        	directions.setText("Enter a column 1 - 7: It's R's turn.");
	        	        	userIn.setText("");
	        	        	error.setText("");
	        	        	marks(x);
	        	        	showBoard();
	        	        	win();
	        	        	visual.setText(gb);
	        	        }
	        	        else {
	        	        	error.setText("Must enter a number 1 - 7!");
	        	        	userIn.setText("");
	        	        }
	        	        
	        		 }catch(NumberFormatException nf) {
	        			 displayErrorMessage("Must enter a number");
	        		 }
	        	 }});
			userIn.addActionListener(new ActionListener() {
	        	 public void actionPerformed(ActionEvent e) {
	        		 try {
	        	        int msg = Integer.parseInt(userIn.getText());
	        	        if((msg<8 && msg>0)&&turn%2==0) {
	        	        	x=msg-1;
	        	        	
	        	        	directions.setText("Enter a column 1 - 7: \tIt's Y's turn.");
	        	        	userIn.setText("");
	        	        	error.setText("");
	        	        	marks(x);
	        	        	showBoard();
	        	        	win();
	        	        	visual.setText(gb);
	        	        }
	        	        else if(msg<8 && msg>0&&turn%2!=0) {
	        	        	x=msg-1;
	        	        	directions.setText("Enter a column 1 - 7: \tIt's R's turn.");
	        	        	userIn.setText("");
	        	        	error.setText("");
	        	        	marks(x);
	        	        	showBoard();
	        	        	win();
	        	        	visual.setText(gb);
	        	        }
	        	        else {
	        	        	error.setText("Must enter a number 1 - 7!");
	        	        	userIn.setText("");
	        	        }
	        		 }catch(NumberFormatException nf) {
	        			 displayErrorMessage("Must enter a number");
	        		 }
	        	 }});
			reset.addActionListener(new ActionListener() {
	        	 public void actionPerformed(ActionEvent e) {
	        	        reset();
	        }});
			this.add(gamePanel);
		}
		//puts pieces on board
	public void marks(int col) {
		if(turn%2==0) {
			for(int x=5; x>=0; x--) {
				if(board[x][col].equalsIgnoreCase("|_|")) {
					player=2;
					board[x][col]="|R|";
					turn++;
					gb="";
					error.setText("");
					break;
				}
				if(x==0) {
					error.setText("You can not go there.");
					directions.setText("Enter a column 1 - 7: \tIt's R's turn.");
				}
			}
		}	
		else {
			for(int x=5; x>=0; x--) {
				if(board[x][col].equalsIgnoreCase("|_|")) {
					player=1;
					board[x][col]="|Y|";
					turn++;
					gb="";
					error.setText("");
					break;
				}
				if(x==0) {
					error.setText("You can not go there.");
					directions.setText("Enter a column 1 - 7: \tIt's Y's turn.");
				}
			}
		}
	}
	
	public void win() {
		//check for verical wins
		String red ="|R||R||R||R|";
		String yellow="|Y||Y||Y||Y|";
		String check="";
		for(int y=0; y<7; y++) {
			check="";
			for(int x=5; x>=0; x--) {
				check+=board[x][y];
				if(check.contains(red)) {
					win.setText("R wins!");
					userIn.setEditable(false);
				}
				if(check.contains(yellow)) {
					win.setText("Y wins!");
					userIn.setEditable(false);
				}
				
			}
		}
		//checks for horizontal victories
		for(int x=5; x>=0; x--) {
			check="";
			for(int y=0; y<board[0].length; y++) {
				check+=board[x][y];
				if(check.contains(red)) {
					win.setText("R wins!");
					userIn.setEditable(false);
				}
				if(check.contains(yellow)) {
					win.setText("Y wins!");
					userIn.setEditable(false);
				}
			}
		}
		//Checks for an up to the right diagonal victory 
		for(int x=3; x<=5; x++) {
			for(int y=0; y<4; y++) {
				if(board[x][y].equals(board[x-1][y+1])&&board[x][y].equals(board[x-2][y+2])
						&&board[x][y].equals(board[x-3][y+3])&&board[x][y].equals("|R|")) {
					win.setText("R wins!");
					userIn.setEditable(false);
				}
				if(board[x][y].equals(board[x-1][y+1])&&board[x][y].equals(board[x-2][y+2])
						&&board[x][y].equals(board[x-3][y+3])&&board[x][y].equals("|Y|")) {
					win.setText("Y wins!");
					userIn.setEditable(false);
				}
			}
			
		}
		//Checks for down to the right diagonal wins
		for(int x=0; x<=2; x++) {
			for(int y=0; y<4; y++) {
				if(board[x][y].equals(board[x+1][y+1])&&board[x][y].equals(board[x+2][y+2])
						&&board[x][y].equals(board[x+3][y+3])&&board[x][y].equals("|R|")) {
					win.setText("R wins!");
					userIn.setEditable(false);
				}
				if(board[x][y].equals(board[x+1][y+1])&&board[x][y].equals(board[x+2][y+2])
						&&board[x][y].equals(board[x+3][y+3])&&board[x][y].equals("|Y|")) {
					win.setText("Y wins!");
					userIn.setEditable(false);
				}
			}
		}
		//When nobody wins
		if(turn==42) {
			System.out.println("It's a draw!");
			userIn.setEditable(false);
		}
	}
	
	public boolean hasNext() {
		if(h<6 && v<7)
			return true;
		return false;
	}

	public String next() {
		String s="";
		while(hasNext()) {
		s+=board[h][v];
		v++;
		if(v==7) {
			v=0;
			h++;
			s+="\n";
		}
		}
		return s;
	}
	//resets the game
	void reset() {
		x=0;
		player=1;
		c=0;
		turn=0;
		newBoard();
		showBoard();
		directions.setText("Enter a column 1 - 7: It's R's turn");
		visual.setText(gb);
		userIn.setEditable(true);
		error.setText("");
		win.setText("");
	}
	//pop up window when a number isn't entered
	void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	

}
