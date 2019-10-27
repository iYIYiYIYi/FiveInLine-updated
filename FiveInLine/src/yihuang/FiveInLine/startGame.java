package yihuang.FiveInLine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class startGame {

    private JPanel BasePanel;
    private JButton 开始游戏Button;
    private JButton 退出游戏Button;
    private JRadioButton 人机对战后手;
    private JRadioButton 人机对战先手;
    private JRadioButton 多人本地对战;
    private JRadioButton 多人联机对战;
    private JPanel buttoms;
    Judger judger = new Judger();
    protected static JFrame frame = new JFrame("开始游戏");
    protected ImageIcon image = new ImageIcon(this.getClass().getResource("title.png"));

    public startGame() {

        frame.setContentPane(BasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage(image.getImage());
        frame.setSize(581,343);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        开始游戏Button.addActionListener(e -> {
            if (Judger.gamemode != null) {
                mainGameWindow newGame = new mainGameWindow();
                frame.setVisible(false);
            } else {
                System.out.println("请选择游戏模式");
            }
        });
        退出游戏Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ButtonGroup radiobuttons = new ButtonGroup();
        radiobuttons.add(多人本地对战);
        radiobuttons.add(多人联机对战);
        radiobuttons.add(人机对战先手);
        radiobuttons.add(人机对战后手);

        ItemListener listener1 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("人机对战");
                judger.setGameMode(GameMode.local);
                judger.setChessType(chesses.white);
            }
        };
        ItemListener listener2 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("多人本地对战");
                judger.setGameMode(GameMode.multiple);
            }
        };
        ItemListener listener3 = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("多人联机对战");
                judger.setGameMode(GameMode.online);
            }
        };

        人机对战先手.addItemListener(listener1);
        多人本地对战.addItemListener(listener2);
        多人联机对战.addItemListener(listener3);
    }

    public static void main(String[] args) {
        startGame game = new startGame();
    }
}