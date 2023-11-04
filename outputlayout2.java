import java.awt.*;
import javax.swing.*;

public class outputlayout2 extends JFrame {
    JLabel label;
    static JLabel matlabel[];
    JPanel content;
    JPanel Matrix;

    public static void main(String[] args) {
        new outputlayout2();
    }

    public outputlayout2() {
        content = new JPanel(new FlowLayout());
        label = new JLabel("Optimum Strategies for the Players are :");
        label.setFont(new Font("Trebuchet MS",Font.BOLD,40));
//        label.setPreferredSize(new Dimension(350, 50));
        content.setBackground(new Color(0xFFE5F1));

        matlabel = new JLabel[9];

        for (int i = 0; i < 9; i++) {
            matlabel[i] = new JLabel("");
            matlabel[i].setFont(new Font("Trebuchet MS",Font.PLAIN,25));
        }
        matlabel[0].setText("    Game 2 X 2");
        matlabel[1].setText("    Strategy 1");
        matlabel[2].setText("    Strategy 2");
        matlabel[3].setText("    Player 1");
        matlabel[4].setText("    1");
        matlabel[5].setText("    2");
        matlabel[6].setText("    Player 2");
        matlabel[7].setText("    1");
        matlabel[8].setText("    2");

        int index1 = 4, index2 = 7;
        for (int i = 0; i < 2; i++) {
            matlabel[index1++].setText("      " + Backend.resultsP1[i]);
            matlabel[index2++].setText("      " + Backend.resultsP2[i]);
        }

        Matrix = new JPanel();
        Matrix.setLayout(new GridLayout(3, 4, 10, 10));
        Matrix.setBackground(new Color(0xFFF8E1));

        for (int i = 0; i < 9; i++) {
            matlabel[i].setPreferredSize(new Dimension(100, 100));
            matlabel[i].setOpaque(true);
            matlabel[i].setBackground(new Color(0xC0DEFF));
            Matrix.add(matlabel[i]);
        }
        JPanel spacePanelW = new JPanel();
        JPanel spacePanelE = new JPanel();
        JPanel spacePanelS = new JPanel();
        spacePanelE.setBackground(new Color(0xFFF8E1));
        spacePanelW.setBackground(new Color(0xFFF8E1));
        spacePanelS.setBackground(new Color(0xFFF8E1));
        content.add(label,BorderLayout.NORTH);
        this.setTitle("Probabilities");
        this.setLayout(new BorderLayout(10,15));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(575, 500);
        this.add(content,BorderLayout.NORTH);
        this.add(spacePanelW,BorderLayout.WEST);
        this.add(spacePanelE,BorderLayout.EAST);
        this.add(spacePanelS,BorderLayout.SOUTH);
        this.add(Matrix,BorderLayout.CENTER);
        this.getContentPane().setBackground(new Color(0xFFF8E1));
        pack();
        this.setVisible(true);
    }

}