import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.ArrayList;
public class TicTacToe implements ActionListener{
    GridBagConstraints gbc = new GridBagConstraints();
    int rows;
    int cols;
    int winning_conditions;
    int pieces;
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton newGame = new JButton();

    ArrayList<JButton> buttons;

    boolean player1_turn;

    public TicTacToe(int rows, int cols, int winning_conditions){
        frame.setTitle("TicTacToe");
        this.rows = rows;
        this.cols = cols;
        this.winning_conditions = winning_conditions;
        this.pieces = rows*cols;
        buttons = new ArrayList<>(pieces);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(240, 240, 220));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(240,240,220));
        textfield.setForeground(new Color(0, 10, 48, 255));
        textfield.setFont(new Font("Chiller",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        //textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);
        title_panel.setLayout(new GridBagLayout());
        title_panel.setBackground(new Color(210, 200, 160));

        gbc.fill = GridBagConstraints.BOTH;
        newGame.setBounds(0, 0, 300,50);

        button_panel.setLayout(new GridLayout(rows,cols));
        button_panel.setBackground(new Color(150,150,150));

        title_panel.add(textfield);
        frame.getContentPane().add(title_panel, BorderLayout.NORTH);
        frame.getContentPane().add(button_panel);

        firstTurn();

        for(int i=0; i<pieces; i++){
            JButton button = new JButton();
            buttons.add(button);
            button_panel.add(buttons.get(i));
            buttons.get(i).setFont(new Font("Chiller",Font.PLAIN, 4000/pieces));
            buttons.get(i).addActionListener(this);
            buttons.get(i).setBackground(new Color(210, 200, 160));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e){
            for (int i = 0; i < pieces; i++) {
                if (e.getSource() == buttons.get(i)) {
                    if (player1_turn) {
                        if (buttons.get(i).getText().equals("")) {
                            buttons.get(i).setForeground(new Color(126, 0, 0));
                            buttons.get(i).setText("X");
                            player1_turn = false;
                            textfield.setText("O turn");
                            if (check("X")) {
                                for(int p=0; p<pieces; p++){
                                    buttons.get(p).setEnabled(false);

                                }
                                textfield.setText("X Wins!");

                                return;
                            }
                        }
                    } else {
                        if (buttons.get(i).getText().equals("")) {
                            buttons.get(i).setForeground(new Color(20, 71, 0));
                            buttons.get(i).setText("O");
                            player1_turn = true;
                            textfield.setText("X turn");
                            if (check("O")) {
                                for(int p=0; p<pieces; p++){
                                    buttons.get(p).setEnabled(false);
                                }
                                textfield.setText("O Wins!");

                                return;
                            }
                        }
                    }
                }
            }
    }
    public void gameEnd(){
        for(int p=0; p<pieces; p++) {
            buttons.get(p).setBackground(new Color(0x8BBEC8C8, true));
        }
        NewGameButton();
    }
    public void NewGameButton(){
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                TicTacToe newGame = new TicTacToe(rows, cols, winning_conditions);
                frame.dispose();
            }
        });
        gbc.gridy = 1;

        newGame.setText("New Game");
        newGame.setFont(new Font("Chiller", Font.PLAIN, 40));
        //newGame.setBackground(new Color(0x303030));
        newGame.setForeground(new Color(0xCF0D0D));
        title_panel.add(newGame, gbc);
    }
    public void firstTurn(){

        if(random.nextInt(2)==0){
            player1_turn=true;
            textfield.setText("X turn");

        }else{
            player1_turn=false;
            textfield.setText("O turn");
        }


    }
    public boolean check(String symbol){
        //sprawdza x warunki
        //-----------------------funkcja do sprawdzania warunku w kolumnach----------------------------------
        boolean isTrue = checkCols(symbol);
        if(isTrue){return true;}
        //--------------------------funkcja do sprawdzania warunku zwycięstwa w wierszach--------------------------
        isTrue = checkRows(symbol);
        if(isTrue){return true;}
        //---------------------------funkcja do sprawdzania warunku zwycięstwa na ukos w prawo-----------------
        isTrue = checkDiagonalRight(symbol);
        if(isTrue){return true;}
        //---------------------------funkcja do sprawdzania warunku zwyciestwa na ukos w lewo-----------------
        isTrue = checkDiagonalLeft(symbol);
        if(isTrue) {return true;}
            return false;

    }
    public boolean checkCols(String symbol) {
        int liczba_symboli = 0;
        for (int n = 0; n < (rows - winning_conditions+1)*cols; n = n + cols) {
            for (int i = n; i < cols+n; i++) {
                for (int j = 0; j < winning_conditions; j++) {
                    if (buttons.get(i+j*cols).getText().equals(symbol)) {
                        liczba_symboli++;
                    } else {
                        liczba_symboli = 0;
                        break;
                    }
                    if (liczba_symboli == winning_conditions) {
                    }
                }
                if (liczba_symboli == winning_conditions) {
                    gameEnd();
                    for (int g = 0; g < winning_conditions; g++) {
                        buttons.get(i + g*cols).setBackground(Color.GREEN);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkRows(String symbol) {
        int liczba_symboli = 0;
        for (int n = 0; n <= cols - winning_conditions; n++) {
            for (int i = n; i < cols*rows; i = i+cols) {
                for (int j = 0; j < winning_conditions; j++) {
                    if (buttons.get(i+j).getText().equals(symbol)) {
                        liczba_symboli++;
                    } else {
                        liczba_symboli = 0;
                        break;
                    }
                    if (liczba_symboli == winning_conditions) {
                        break;
                    }
                }
                if (liczba_symboli == winning_conditions) {
                    gameEnd();
                    for (int g = 0; g < winning_conditions; g++) {
                        buttons.get(i + g).setBackground(Color.GREEN);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkDiagonalRight(String symbol) {
        int liczba_symboli = 0;
        for (int n = 0; n <= cols - winning_conditions; n++) {
            for (int i = n; i <= (rows-winning_conditions)*cols + cols-winning_conditions; i = i+cols) {
                for (int j = 0; j < winning_conditions; j++) {
                    if (buttons.get(i +cols*j+j).getText().equals(symbol)) {
                        liczba_symboli++;
                    } else {
                        liczba_symboli = 0;
                        break;
                    }
                    if (liczba_symboli == winning_conditions) {
                        break;
                    }
                }
                if (liczba_symboli == winning_conditions) {
                    gameEnd();
                    for (int g = 0; g < winning_conditions; g++) {
                        buttons.get(i+cols*g +g).setBackground(Color.GREEN);
                    }

                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkDiagonalLeft(String symbol) {
        int liczba_symboli = 0;
        for (int n = cols-(cols-winning_conditions)-1; n <= cols-1; n++) {
            for (int i = n; i <= (rows-winning_conditions+1)*cols - 1; i = i+cols) {
                for (int j = 0; j < winning_conditions; j++) {
                    if (buttons.get(i+cols*j-j).getText().equals(symbol)) {
                        liczba_symboli++;
                    } else {
                        liczba_symboli = 0;
                        break;
                    }
                    if (liczba_symboli == winning_conditions) {
                        break;
                    }
                }
                if (liczba_symboli == winning_conditions) {
                    gameEnd();
                    for (int g = 0; g < winning_conditions; g++) {
                        buttons.get(i + cols*g - g).setBackground(Color.GREEN);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}


