package uk.alij.tanks;

/**
 * Created by Ali J on 4/29/2015.
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class TankMenu extends JFrame {

    private ImagePanel contentPane;
    private JTextField txtPlayername;
    private JLabel name;

    /**
     * Create the frame.
     */
    public TankMenu() {
        setResizable(false);
        setForeground(Color.WHITE);
        setTitle("BTanks - Ali J.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 240, 340);
        contentPane = new ImagePanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        name = new JLabel("NAME:");
        txtPlayername = new JTextField();
        final JButton btnEasy = new ImageButton("Easy");
        final JButton btnMedium = new ImageButton("Medium");
        final JButton btnHard = new ImageButton("Hard");
        txtPlayername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtPlayername.selectAll();
            }
        });
        txtPlayername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnEasy.setFocusable(true);
                    btnEasy.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    btnHard.setFocusable(true);
                    btnHard.requestFocus();
                }
            }
        });
        name.setForeground(Color.yellow);
        name.setBounds(5, 15, 40, 40);
        contentPane.add(name);
        txtPlayername.setBackground(new Color(51, 153, 204));
        txtPlayername.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayername.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        txtPlayername.setBounds(50, 20, 120, 30);
        txtPlayername.setText("PlayerName");
        contentPane.add(txtPlayername);
        txtPlayername.setColumns(10);
        btnEasy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_DOWN){
                    btnMedium.setFocusable(true);
                    btnMedium.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    dispose();
                    new GamePanel(btnEasy.getText());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    txtPlayername.setFocusable(true);
                    txtPlayername.requestFocus();
                }
            }
        });
        btnEasy.setBackground(new Color(51, 153, 204));
        btnEasy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GamePanel("easy");
            }
        });
        btnEasy.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                btnEasy.setForeground(Color.RED);
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnEasy.setForeground(Color.BLACK);
            }
        });
        btnEasy.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnEasy.setLocation(7, 65);
//        btnEasy.setBounds(65, 80, 90, 25);
        contentPane.add(btnEasy);
        btnMedium.setBackground(new Color(51, 153, 204));
        btnMedium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GamePanel("medium");
            }
        });
        btnMedium.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                btnMedium.setForeground(Color.RED);
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnMedium.setForeground(Color.BLACK);
            }
        });
        btnMedium.setFont(new Font("Century Gothic", Font.PLAIN, 12));
//        btnMedium.setBounds(65, 130, 90, 25);
        btnMedium.setLocation(82, 65);
        contentPane.add(btnMedium);
        btnMedium.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    btnHard.setFocusable(true);
                    btnHard.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                    new GamePanel(btnHard.getText());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    btnEasy.setFocusable(true);
                    btnEasy.requestFocus();
                }
            }
        });
        btnHard.setBackground(new Color(51, 153, 204));
        btnHard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GamePanel("hard");
            }
        });
        btnHard.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                btnHard.setForeground(Color.RED);
            }

            @Override
            public void focusLost(FocusEvent e) {
                btnHard.setForeground(Color.BLACK);
            }
        });
        btnHard.setFont(new Font("Century Gothic", Font.PLAIN, 12));
//        btnHard.setBounds(65, 180, 90, 25);
        btnHard.setLocation(157, 65);
        contentPane.add(btnHard);
        btnHard.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_DOWN){
                    txtPlayername.setFocusable(true);
                    txtPlayername.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    dispose();
                    new GamePanel(btnHard.getText());
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    btnMedium.setFocusable(true);
                    btnMedium.requestFocus();
                }
            }
        });
    }


    /**
     * Launch the game.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TankMenu frame = new TankMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class ImagePanel extends JPanel{
        private BufferedImage image;

        public ImagePanel() {
            try {
                image = ImageIO.read(new File("res/images/menu/background.gif"));
            } catch (IOException ex) {
                System.out.println("Menu Image Not Found!");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    }
    private class ImageButton extends JButton{
        private Vector<ImageIcon> images;
        private String difficulty;

        public ImageButton(String difficulty) {
            this.difficulty = difficulty;
            setSize(new Dimension(70, 87));
            setResizable(false);
            images = new Vector<ImageIcon>(3);
            for (int i = 1; i < 4; i++){
                ImageIcon a =  new ImageIcon("res/images/menu/" + i + ".png");
                images.add(a);
            }
            if (difficulty.equalsIgnoreCase("easy")){
                this.setIcon( images.get(0));
            }
            if (difficulty.equalsIgnoreCase("medium")){
                this.setIcon( images.get(1));
            }
            if (difficulty.equalsIgnoreCase("hard")){
                this.setIcon( images.get(2));
            }
        }

    }
}