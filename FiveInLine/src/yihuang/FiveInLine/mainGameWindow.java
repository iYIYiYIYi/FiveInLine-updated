package yihuang.FiveInLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class mainGameWindow{

    private JPanel mainWindowPane;
    private JPanel gamePane;
    private JButton Regret;
    private JButton Back;
    private JLabel gameMode;
    private JLabel Background;
    private Graphics painter;
    private ImageIcon image = new ImageIcon(this.getClass().getResource("title.png"));
    protected static JFrame mainWindow;

    protected void paint(Color color,int x,int y) {
        painter = gamePane.getGraphics();
        painter.setColor(color);
        painter.fillOval(x-7,y-7,14,14);
        if (color == null) {
            gamePane.update(painter);
        }
    }

    private int transformDimension(int x,String D) {
        int width = 37;
        int height = 37;
        switch (D) {
            case "YtoCM":
                return x * height + 1 + 37 + x / 2;
            case "XtoCM":
                return x * width + 8 + 37 + x / 2;
            case "CMtoX":
                return (x - 8 - 22) / width;
            case "CMtoY":
                return (x - 1 - 22) / height;
            default:
                return -1;
        }
    }

    private void repaintMap() {
        gamePane.update(painter);
        for (int j = 0; j < 15; j++) {
            for (int i=0;i<15;i++) {
                if(DataTypes.chessMap[i][j]==chesses.black)
                    paint(Color.black,transformDimension(i,"XtoCM"),transformDimension(j,"YtoCM"));
                if(DataTypes.chessMap[i][j]==chesses.white)
                    paint(Color.LIGHT_GRAY,transformDimension(i,"XtoCM"),transformDimension(j,"YtoCM"));
            }
        }
    }

    private void paintMap(){
        for (int j = 0; j < 15; j++) {
            for(int i = 0; i < 15 ; i++){
                if(DataTypes.chessMap[i][j]==chesses.black)
                    paint(Color.black,transformDimension(i,"XtoCM"),transformDimension(j,"YtoCM"));
                if(DataTypes.chessMap[i][j]==chesses.white)
                    paint(Color.LIGHT_GRAY,transformDimension(i,"XtoCM"),transformDimension(j,"YtoCM"));
//                System.out.print(DataTypes.chessMap[i][j]+" ");
            }
//            System.out.println();
        }
    }

    private void setText(StringBuffer text) {
        if (Judger.gamemode == GameMode.local) {
            text.append("本地人机对战");
        } else if (Judger.gamemode == GameMode.multiple) {
            text.append("本地多人对战");
        } else if (Judger.gamemode == GameMode.online) {
            text.append("在线多人对战");
        }
    }

//    constractive method to create a window to play
    mainGameWindow() {
        StringBuffer text = new StringBuffer();
        setText(text);

        final ArrayList x = new ArrayList();
        final ArrayList y = new ArrayList();
        mainWindow = new JFrame(text.toString());
        mainWindow.add(this.mainWindowPane);

        gameMode.setText(text.toString());
        gamePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX()+" "+e.getY());
                //noinspection unchecked
                x.add(e.getX());
                //noinspection unchecked
                y.add(e.getY());
                int xout = transformDimension((int)x.get(x.size()-1),"CMtoX");
                int yout = transformDimension((int)y.get(y.size()-1),"CMtoY");
                System.out.println(xout+" "+yout);
                if (xout<15&&yout<15&&Judger.run(xout,yout));
                paintMap();
            }
        });

        Regret.addActionListener(e -> {
            repaintMap();
            Judger.Cancel();
        });

        Back.addActionListener(e -> {
            System.out.println(Judger.chesstype+"返回了主菜单");
            startGame.frame.setVisible(true);
            DataTypes.cleanMap();
            mainWindow.dispose();
        });


        mainWindow.setIconImage(image.getImage());
        mainWindow.setSize(720,675);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainWindow.setResizable(false);
    }

}