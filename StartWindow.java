import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class StartWindow implements ActionListener {
    public int width;
    public int height;
    public int sequence;
    JButton accept = new JButton();
    JFrame frame = new JFrame();
    JLabel TitleField = new JLabel();
    JLabel TitleField2 = new JLabel();
    JLabel Columns = new JLabel();
    JLabel Rows = new JLabel();
    JLabel Winning = new JLabel();
    JPanel title = new JPanel();
    JPanel columnsPanel = new JPanel();
    JPanel rowsPanel = new JPanel();
    JPanel winningPanel = new JPanel();
    JPanel acceptPanel = new JPanel();
    JTextField setColumns = new JTextField(20);
    JTextField setRows = new JTextField(20);
    JTextField setWinning = new JTextField(20);
    JLabel ok = new JLabel();

    StartWindow() {
        int width = 700;
        int height = 600;
        frame.setTitle("TicTacToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.getContentPane().setBackground(new Color(210, 200, 150));
        frame.setLayout(new GridLayout(5, 1));
        frame.setVisible(true);
// -----------dodanie paneli do ramki-----------------------
        frame.add(title);
        frame.add(columnsPanel);
        frame.add(rowsPanel);
        frame.add(winningPanel);
        frame.add(acceptPanel);

        //frame.add(Box.createVerticalStrut(90));
//---------------ustawienia paneli---------------------------
        title.setBackground(new Color(210, 200, 160));
        columnsPanel.setBackground(new Color(210, 200, 160));
        columnsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //columnsPanel.setLayout(new BorderLayout());
        //columnsPanel.setBounds(0,0,width,height/4);
        rowsPanel.setBackground(new Color(210, 200, 160));
        rowsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //rowsPanel.setLayout(new BorderLayout());
        //rowsPanel.setBounds(0,0,width,height/4);
        winningPanel.setBackground(new Color(210, 200, 160));
        winningPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // winningPanel.setLayout(new BorderLayout());
        //winningPanel.setBounds(0,0,width,height/4);
        acceptPanel.setBackground(new Color(210, 200, 160));
//-----------------ustawienia przycisku----------------------------
        accept.setFont(new Font("ISOCPEUR", Font.BOLD, 100));
        accept.addActionListener(this);


//------------ustawienia panelu tekstowego---------------
        title.add(TitleField, BorderLayout.CENTER);
        TitleField.setFont(new Font("ISOCPEUR", Font.PLAIN, 20));
        TitleField.setText("Wprowadź parametry gry w kółko i krzyżyk");
        TitleField2.setFont(new Font("ISOCPEUR", Font.PLAIN, 20));
        winningPanel.add(Winning, BorderLayout.CENTER);
        Winning.setFont(new Font("ISOCPEUR", Font.PLAIN, 30));
        Winning.setText("liczba symboli wygrywających: ");
        rowsPanel.add(Rows, BorderLayout.CENTER);
        Rows.setFont(new Font("ISOCPEUR", Font.PLAIN, 30));
        Rows.setText("liczbę wierszy: ");
        columnsPanel.add(Columns, BorderLayout.CENTER);
        Columns.setFont(new Font("ISOCPEUR", Font.PLAIN, 30));
        Columns.setText("liczbę kolumn: ");

//-------------dodaje pola tekstowe--------------
        winningPanel.add(setWinning, BorderLayout.CENTER);
        columnsPanel.add(setColumns, BorderLayout.SOUTH);
        rowsPanel.add(setRows, BorderLayout.SOUTH);
//--------------dodaje przycisk--------------
        acceptPanel.add(accept);
        accept.add(ok);
        ok.setText("Akceptuj");
        ok.setFont(new Font("ISOCPEUR", Font.PLAIN, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accept) {
            // Retrieve text from text fields
            String colsText = setColumns.getText();
            String rowsText = setRows.getText();
            String winText = setWinning.getText();

            this.width = Integer.parseInt(colsText);
            this.height = Integer.parseInt(rowsText);
            this.sequence = Integer.parseInt(winText);

            if(sequence > width || sequence > height){
                setColumns.setBackground(new Color(237, 55, 55, 255));
                setRows.setBackground(new Color(237, 55, 55, 255));
                setWinning.setBackground(new Color(237, 55, 55, 255));
                TitleField.setText("Liczba wygrywających symboli musi być mniejsza lub równa ");
                title.add(TitleField2);
                TitleField2.setText("szerokości oraz wysokości proszę podać poprawne wartości.");

            } else{TicTacToe ticTacToe = new TicTacToe(height, width, sequence);}
        }
    }
}
