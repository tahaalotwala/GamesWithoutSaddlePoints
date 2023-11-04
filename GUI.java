import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.RandomAccess;

public class GUI extends JFrame implements ActionListener
{
    JPanel selectOrderPanel;
    JPanel SOerrorPanel;
    JPanel input2Panel;
    JPanel o2ErrorPanel;
    JPanel input3Panel;
    JPanel o3ErrorPanel;
    JPanel output2Panel;
    JPanel output3Panel;

    JRadioButton O2Radio;
    JRadioButton O3Radio;

    JButton toMat;
    JButton toRes2;
    JButton toRes3;

    JTextField matData21[][] = new JTextField[2][2];
    JTextField matData22[][] = new JTextField[2][2];
    JTextField matData31[][] = new JTextField[3][3];
    JTextField matData32[][] = new JTextField[3][3];

    int i,j;

    GUI()
    {
        //Selection of order of Payoff Matrices :
        selectOrderPanel = new JPanel(new BorderLayout());
            //Head Panel
            JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
            JLabel headingLabel = new JLabel("Games Without Saddle Points");
            headingLabel.setFont(new Font("Trebuchet MS",Font.BOLD,40));
            headingPanel.add(headingLabel);
            headingPanel.setBackground(new Color(0xFFE5F1));
            selectOrderPanel.add(headingPanel,BorderLayout.NORTH);

            //gridBag Panel :
            JPanel SOcontentPanel = new JPanel(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();
                //Panel For Pls select order text :
                JPanel SOtextPanel = new JPanel(new BorderLayout());
                SOcontentPanel.setBackground(Color.PINK);
                JLabel SOtextLabel = new JLabel("  Select the order of the payoff matrices : ");
                SOtextLabel.setFont(new Font("Trebuchet MS",Font.PLAIN,26));
                SOtextPanel.add(SOtextLabel);
                g.gridx = 0; g.gridy = 0; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5; g.fill = GridBagConstraints.HORIZONTAL; g.weighty = 0.5;
                SOtextPanel.setBackground(new Color(0xFFF8E1));
                SOcontentPanel.add(SOtextPanel,g);

                //Panel for the 2 RadioButtons
                JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,110,0));
                O2Radio = new JRadioButton(" 2 x 2");
                O2Radio.setFont(new Font("Trebuchet MS",Font.PLAIN,25));
                O3Radio = new JRadioButton(" 3 x 3");
                O3Radio.setFont(new Font("Trebuchet MS",Font.PLAIN,25));
                ButtonGroup orderBtnGrp = new ButtonGroup();
                orderBtnGrp.add(O2Radio);
                orderBtnGrp.add(O3Radio);
                O2Radio.setFocusable(false);
                O3Radio.setFocusable(false);
                O2Radio.setBackground(new Color(0xFFF8E1));
                O3Radio.setBackground(new Color(0xFFF8E1));
                radioPanel.add(O2Radio);
                radioPanel.add(O3Radio);
                g.gridx = 0; g.gridy = 1; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5; g.fill = GridBagConstraints.HORIZONTAL;
                radioPanel.setBackground(new Color(0xFFF8E1));
                SOcontentPanel.add(radioPanel,g);

                //Panel for the button that takes to payoff matrices :
                JPanel orderSubPanel = new JPanel();
                toMat = new JButton("Submit");
                toMat.setFocusable(false);
                toMat.setPreferredSize(new Dimension(175,30));
                toMat.addActionListener(this);
                ImageIcon next = new ImageIcon("images/next.png");
                //Image size fix!
                Image img = next.getImage() ;
                Image newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
                next = new ImageIcon( newimg );
                toMat.setIcon(next);
                toMat.setIconTextGap(5);
                toMat.setHorizontalTextPosition(JButton.LEFT);
                toMat.setFont(new Font("Trebuchet MS",Font.BOLD,20));
                toMat.setBackground(new Color(0xADA2FF));
                orderSubPanel.add(toMat);
                g.gridx = 0; g.gridy = 2; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5; g.fill = GridBagConstraints.HORIZONTAL;
                orderSubPanel.setBackground(new Color(0xFFF8E1));
                SOcontentPanel.add(orderSubPanel,g);

                //Panel for an error message
                SOerrorPanel = new JPanel(new FlowLayout());
                JLabel SOerrorLabel = new JLabel("Please Select an option. ");
                SOerrorLabel.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
                ImageIcon errorImg = new ImageIcon("images/warning.png");
                Image img2 = errorImg.getImage() ;
                Image newimg2 = img2.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ;
                errorImg = new ImageIcon( newimg2 );
                SOerrorLabel.setIcon(errorImg);
                SOerrorPanel.add(SOerrorLabel,BorderLayout.CENTER);
                SOerrorPanel.setVisible(false);
                g.gridx = 0; g.gridy = 3; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5;
                SOerrorPanel.setBackground(new Color(0xFFF8E1));
                SOcontentPanel.add(SOerrorPanel,g);
                SOcontentPanel.setBackground(new Color(0xFFF8E1));
            selectOrderPanel.add(SOcontentPanel);
        add(selectOrderPanel);

        //Panel for input of 2 x 2 matrix :
        input2Panel = new JPanel(new BorderLayout());
        input2Panel.setBackground(Color.PINK);
            //Head Panel :
            JPanel i2HeadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
            JLabel i2HeadLabel = new JLabel("Two player 2 x 2 Game");
            i2HeadLabel.setFont(new Font("Trebuchet MS",Font.BOLD,40));
            i2HeadPanel.setBackground(new Color(0xFFE5F1));
            i2HeadPanel.add(i2HeadLabel);
            input2Panel.add(i2HeadPanel,BorderLayout.NORTH);

            //Content Panel :
            JPanel ip2ContentPanel = new JPanel(new GridBagLayout());
            ip2ContentPanel.setBackground(new Color(0xFFF8E1));
                //panel for enter to matrice text :
                JPanel ip2TextPanel = new JPanel(new BorderLayout());
                ip2TextPanel.setBackground(new Color(0xFFF8E1));
                JLabel ip2TextLabel = new JLabel( "  Enter the payoff matrices of the two players :    ");
                ip2TextLabel.setFont(new Font("Trebuchet MS",Font.PLAIN,26));
                ip2TextPanel.add(ip2TextLabel);
                g.gridx = 0; g.gridy = 0; g.ipady = 42; g.ipadx = 5; g.weightx = 0.5;
                g.fill = GridBagConstraints.HORIZONTAL; g.weighty = 0.5;
                ip2ContentPanel.add(ip2TextPanel,g);

                //panel for the 2 matrices :
                JPanel mat2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));
                mat2Panel.setBackground(new Color(0xFFF8E1));
                JPanel mat21Panel = new JPanel(new GridBagLayout());
                mat21Panel.setBackground(new Color(0xFFF8E1));
                GridBagConstraints g2 = new GridBagConstraints();

                for(i=0;i<2;i++)
                {
                    for(j=0;j<2;j++)
                    {
                        matData21[i][j] = new JTextField(5);
                        matData21[i][j].setFont(new Font("Helvetica", Font.BOLD, 20));
                        g2.gridx = j; g2.gridy = i; g2.weightx = 0.5; g2.weighty = 0.5;
                        mat21Panel.add(matData21[i][j],g2);
                    }
                }
                JPanel o2p1LabPanel = new JPanel(new FlowLayout());
                o2p1LabPanel.setBackground(new Color(0xFFF8E1));
                JLabel o2p1Label = new JLabel("Player 1");
                o2p1Label.setFont(new Font("Helvetica", Font.PLAIN, 15));
                g2.gridx = 0; g2.gridy = 2; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 2;
                o2p1LabPanel.add(o2p1Label);
                mat21Panel.add(o2p1LabPanel,g2);
                mat2Panel.add(mat21Panel);

                JPanel mat22Panel = new JPanel(new GridBagLayout());
                mat22Panel.setBackground(new Color(0xFFF8E1));

                for(i=0;i<2;i++)
                {
                    for(j=0;j<2;j++)
                    {
                        matData22[i][j] = new JTextField(5);
                        matData22[i][j].setFont(new Font("Helvetica", Font.BOLD, 20));
                        g2.gridx = j; g2.gridy = i; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 1;
                        mat22Panel.add(matData22[i][j],g2);
                    }
                }
                JPanel o2p2LabPanel = new JPanel(new FlowLayout());
                o2p2LabPanel.setBackground(new Color(0xFFF8E1));
                JLabel o2p2Label = new JLabel("Player 2");
                o2p2Label.setFont(new Font("Helvetica", Font.PLAIN, 15));
                g2.gridx = 0; g2.gridy = 2; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 2;
                o2p2LabPanel.add(o2p2Label);
                mat22Panel.add(o2p2LabPanel,g2);
                mat2Panel.add(mat22Panel);

                g.gridx = 0; g.gridy = 1; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5;
                g.fill = GridBagConstraints.HORIZONTAL; g.weighty = 0.5;
                ip2ContentPanel.add(mat2Panel,g);

                //Submit button Panel :
                JPanel o2DataSubPanel = new JPanel();
                toRes2 = new JButton("Submit");
                toRes2.setFocusable(false);
                toRes2.setPreferredSize(new Dimension(175,30));
                toRes2.addActionListener(this);
                next = new ImageIcon("images/next.png");
                //Image size fix!
                img = next.getImage() ;
                newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
                next = new ImageIcon( newimg );
                toRes2.setIcon(next);
                toRes2.setIconTextGap(5);
                toRes2.setHorizontalTextPosition(JButton.LEFT);
                toRes2.setFont(new Font("Trebuchet MS",Font.BOLD,20));
                toRes2.setBackground(new Color(0xADA2FF));
                o2DataSubPanel.add(toRes2);
                g.gridx = 0; g.gridy = 2; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5; g.fill = GridBagConstraints.HORIZONTAL;
                o2DataSubPanel.setBackground(new Color(0xFFF8E1));
                ip2ContentPanel.add(o2DataSubPanel,g);

                //Panel for an error message
                o2ErrorPanel = new JPanel(new FlowLayout());
                JLabel o2ErrLabel = new JLabel("Invalid Data Entered. ");
                o2ErrLabel.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
                errorImg = new ImageIcon("images/warning.png");
                img2 = errorImg.getImage() ;
                newimg2 = img2.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ;
                errorImg = new ImageIcon( newimg2 );
                o2ErrLabel.setIcon(errorImg);
                o2ErrorPanel.add(o2ErrLabel,BorderLayout.CENTER);
                o2ErrorPanel.setVisible(false);
                g.gridx = 0; g.gridy = 3; g.ipady = 15; g.ipadx = 5; g.weightx = 0.5;
                o2ErrorPanel.setBackground(new Color(0xFFF8E1));
                ip2ContentPanel.add(o2ErrorPanel,g);
            input2Panel.add(ip2ContentPanel);
        input2Panel.setVisible(true);

        //Panel for input of 3 x 3 matrix :
        input3Panel = new JPanel(new BorderLayout());
            //Head Panel :
            JPanel i3HeadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
            JLabel i3HeadLabel = new JLabel("Two player 3 x 3 Game");
            i3HeadLabel.setFont(new Font("Trebuchet MS",Font.BOLD,40));
            i3HeadPanel.setBackground(new Color(0xFFE5F1));
            i3HeadPanel.add(i3HeadLabel);
            input3Panel.add(i3HeadPanel,BorderLayout.NORTH);

        //Content Panel :
        JPanel ip3ContentPanel = new JPanel(new GridBagLayout());
        ip3ContentPanel.setBackground(new Color(0xFFF8E1));
            //panel for enter to matrice text :
            JPanel ip3TextPanel = new JPanel(new BorderLayout());
            ip3TextPanel.setBackground(new Color(0xFFF8E1));
            JLabel ip3TextLabel = new JLabel( "  Enter the payoff matrices of the two players :    ");
            ip3TextLabel.setFont(new Font("Trebuchet MS",Font.PLAIN,26));
            ip3TextPanel.add(ip3TextLabel);
            g.gridx = 0; g.gridy = 0; g.ipady = 42; g.ipadx = 5; g.weightx = 0.5;
            g.fill = GridBagConstraints.HORIZONTAL; g.weighty = 0.5;
            ip3ContentPanel.add(ip3TextPanel,g);

            //panel for the 2 matrices :
            JPanel mat3Panel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));
            mat3Panel.setBackground(new Color(0xFFF8E1));
            JPanel mat31Panel = new JPanel(new GridBagLayout());
            mat31Panel.setBackground(new Color(0xFFF8E1));

        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                matData31[i][j] = new JTextField(5);
                matData31[i][j].setFont(new Font("Helvetica", Font.BOLD, 20));
                g2.gridx = j; g2.gridy = i; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 1;
                mat31Panel.add(matData31[i][j],g2);
            }
        }
            JPanel o3p1LabPanel = new JPanel(new FlowLayout());
            o3p1LabPanel.setBackground(new Color(0xFFF8E1));
            JLabel o3p1Label = new JLabel("Player 1");
            o3p1Label.setFont(new Font("Helvetica", Font.PLAIN, 15));
            g2.gridx = 0; g2.gridy = 3; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 3;
            o3p1LabPanel.add(o3p1Label);
            mat31Panel.add(o3p1LabPanel,g2);
            mat3Panel.add(mat31Panel);

            JPanel mat32Panel = new JPanel(new GridBagLayout());
            mat32Panel.setBackground(new Color(0xFFF8E1));

            for(i=0;i<3;i++)
            {
                for(j=0;j<3;j++)
                {
                    matData32[i][j] = new JTextField(5);
                    matData32[i][j].setFont(new Font("Helvetica", Font.BOLD, 20));
                    g2.gridx = j; g2.gridy = i; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 1;
                    mat32Panel.add(matData32[i][j],g2);
                }
            }
            JPanel o3p2LabPanel = new JPanel(new FlowLayout());
            o3p2LabPanel.setBackground(new Color(0xFFF8E1));
            JLabel o3p2Label = new JLabel("Player 2");
            o3p2Label.setFont(new Font("Helvetica", Font.PLAIN, 15));
            g2.gridx = 0; g2.gridy = 3; g2.weightx = 0.5; g2.weighty = 0.5; g2.gridwidth = 3;
            o3p2LabPanel.add(o3p2Label);
            mat32Panel.add(o3p2LabPanel,g2);
            mat3Panel.add(mat32Panel);

            g.gridx = 0; g.gridy = 1; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5;
            g.fill = GridBagConstraints.HORIZONTAL; g.weighty = 0.5;
            ip3ContentPanel.add(mat3Panel,g);

            //Submit button Panel :
            JPanel o3DataSubPanel = new JPanel();
            toRes3 = new JButton("Submit");
            toRes3.setFocusable(false);
            toRes3.setPreferredSize(new Dimension(175,30));
            toRes3.addActionListener(this);
            next = new ImageIcon("images/next.png");
            //Image size fix!
            img = next.getImage() ;
            newimg = img.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
            next = new ImageIcon( newimg );
            toRes3.setIcon(next);
            toRes3.setIconTextGap(5);
            toRes3.setHorizontalTextPosition(JButton.LEFT);
            toRes3.setFont(new Font("Trebuchet MS",Font.BOLD,20));
            toRes3.setBackground(new Color(0xADA2FF));
            o3DataSubPanel.add(toRes3);
            g.gridx = 0; g.gridy = 2; g.ipady = 25; g.ipadx = 5; g.weightx = 0.5; g.fill = GridBagConstraints.HORIZONTAL;
            o3DataSubPanel.setBackground(new Color(0xFFF8E1));
            ip3ContentPanel.add(o3DataSubPanel,g);

            //Panel for an error message
            o3ErrorPanel = new JPanel(new FlowLayout());
            JLabel o3ErrLabel = new JLabel("Invalid Data Entered. ");
            o3ErrLabel.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
            errorImg = new ImageIcon("images/warning.png");
            img2 = errorImg.getImage() ;
            newimg2 = img2.getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ;
            errorImg = new ImageIcon( newimg2 );
            o3ErrLabel.setIcon(errorImg);
            o3ErrorPanel.add(o3ErrLabel,BorderLayout.CENTER);
            o3ErrorPanel.setVisible(false);
            g.gridx = 0; g.gridy = 3; g.ipady = 15; g.ipadx = 5; g.weightx = 0.5;
            o3ErrorPanel.setBackground(new Color(0xFFF8E1));
            ip3ContentPanel.add(o3ErrorPanel,g);
            input3Panel.add(ip3ContentPanel);
        input3Panel.setVisible(true);

        //Main Frame
        setTitle("Mini Project 1A Team-4");
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == O2Radio || e.getSource() == O3Radio)
        {
            SOerrorPanel.setVisible(false);
            pack();
        }
        if(e.getSource() == toMat)
        {
            if(O2Radio.isSelected())
            {
                Backend.m = Backend.n = 3;
                selectOrderPanel.setVisible(false);
                add(input2Panel);
                pack();
            }
            else if (O3Radio.isSelected())
            {
                Backend.m = Backend.n = 4;
                selectOrderPanel.setVisible(false);
                add(input3Panel);
                pack();
            }
            else
            {
                SOerrorPanel.setVisible(true);
                pack();
            }
        }
        Backend game = new Backend();
        if (e.getSource() == toRes2)
        {
            for (i = 1; i < Backend.m; i++) {
                for (j = 1; j < Backend.n; j++) {
                    try {
                        Backend.p1[i][j] = Integer.parseInt(matData21[i - 1][j - 1].getText());
                    } catch (Exception exc) {
                        o2ErrorPanel.setVisible(true);
                    }

                }
            }
            for (i = 1; i < Backend.m; i++) {
                for (j = 1; j < Backend.n; j++) {
                    try {
                        Backend.p2[i][j] = Integer.parseInt(matData22[i - 1][j - 1].getText());
                    } catch (Exception exc) {
                        o2ErrorPanel.setVisible(true);
                    }
                }
            }
            try {
                game.calcNashEqmO2();
                Backend.displayNashEqmO2();
                new outputlayout2();
                this.dispose();
            } catch (Exception exc) {
                o2ErrorPanel.setVisible(true);
            }
        }

        if (e.getSource() == toRes3) {
            for (i = 1; i < Backend.m; i++) {
                for (j = 1; j < Backend.n; j++) {
                    try {
                        Backend.p1[i][j] = Integer.parseInt(matData31[i - 1][j - 1].getText());
                    } catch (Exception exc) {
                        o3ErrorPanel.setVisible(true);
                        return;
                    }
                }
            }
            for (i = 1; i < Backend.m; i++) {
                for (j = 1; j < Backend.n; j++) {
                    try {
                        Backend.p2[i][j] = Integer.parseInt(matData32[i - 1][j - 1].getText());
                    } catch (Exception exc) {
                        o3ErrorPanel.setVisible(true);
                        return;
                    }
                }
            }
            game.checkDominance();
            if (Backend.m == 3 && Backend.n == 3) {
                try {
                    game.calcNashEqmO2();
                    Backend.displayNashEqmO2();
                    new outputlayout();
                    this.dispose();
                } catch (Exception exc) {
                    o3ErrorPanel.setVisible(true);
                    return;
                }
            } else if (Backend.m == 4 && Backend.n == 4) {
                try {
                    game.calcNashEqmO3();
                    Backend.displayNashEqmO3();
                    new outputlayout();
                    this.dispose();
                } catch (Exception exc) {
                    o3ErrorPanel.setVisible(true);
                    return;
                }
            }
        }
    }
}

