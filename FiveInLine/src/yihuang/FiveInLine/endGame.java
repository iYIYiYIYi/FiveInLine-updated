package yihuang.FiveInLine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class endGame {
    private JPanel end;
    private JLabel endgame;
    private JButton sure;
    private JFrame frame = new JFrame("游戏结束");
    protected ImageIcon image = new ImageIcon(this.getClass().getResource("title.png"));

    public endGame(String text) {
        frame.setContentPane(end);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(233,130);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(image.getImage());

        endgame.setText(text);
        System.out.println(text);
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame.frame.setVisible(true);
                mainGameWindow.mainWindow.dispose();
                DataTypes.cleanMap();
                frame.dispose();
            }
        });
    }
}
