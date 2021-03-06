package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;

public class B10415011 extends gameView 
{
    
    private Color[][] blockContainer;
    private int score;
    private Point pieceOrigin = new Point(5, 2);
    private gameController gamecontroller;
    private gameModel gamemodel;
    private final Color gamebackground = Color.BLACK;
    private final Color gamewall = Color.BLACK;
    private Block nowBlock;
    private Block nextBlock;
    
    public B10415011() 
    {
        this.gamecontroller = new gameController();
    }

    public void init() 
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(15 * 26 + 10, 30 * 23 + 25);
        frame.setVisible(true);
        frame.add(this);
        frame.getContentPane().add(BorderLayout.SOUTH, jLabel);
        jLabel.setForeground(Color.BLACK);
     }

    public Color getBackgroundColor() 
    {
        return gamebackground;
    }

    public Color getWallColor() 
    {
        return gamewall;
    }

    public void connectModel(gameModel m) 
    {
        gamemodel = m;
        blockContainer = gamemodel.getContainer();
        pieceOrigin = gamemodel.getNowBlockPoint();
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        score = gamemodel.getScore();
    }

    public void update() 
    {
        nowBlock = gamemodel.getNowBlock();
        nextBlock = gamemodel.getNextBlock();
        blockContainer = gamemodel.getContainer();
        pieceOrigin = gamemodel.getNowBlockPoint();
        score = gamemodel.getScore();
        repaint();
    }

    public void connectController(gameController c) 
    {
        gamecontroller = c;
    }
    // Creates a border around the blockContainer and initializes the dropping piece

    private void drawPiece(Graphics g) 
    {
        g.setColor(nowBlock.getColor());//setColor：設定繪製顏色
        Point[][] shape = nowBlock.getShape();
        for (Point p : shape[nowBlock.getRotate()]) 
        {
            g.fillRect((p.x + pieceOrigin.x) * 26+30,
            			(p.y + pieceOrigin.y) * 26+30,
            				25, 25);//fillRect：畫出填滿顏色的長方形
        }
    }

    private void drawNext(Graphics g) 
    {
        g.setColor(nextBlock.getColor());
        Point[][] shape = nextBlock.getShape();
        for (Point p : shape[nextBlock.getRotate()]) 
        {
            g.fillRect((p.x + 5) * 26+30,
                    (p.y + 0) * 26+30,
                    25, 25);
        }
    }
    public void paintComponent(Graphics g) 
    {
        // Paint the blockContainer

        g.fillRect(30, 30, 26 * 12, 26 * 23);
        for (int i = 0; i < 12; i++) 
        {
            for (int j = 0; j < 23; j++) 
            {
                g.setColor(blockContainer[i][j]);
                g.fillRect(26 * i+30, 26 * j+30, 25, 25);
            }
        }
        jLabel.setText("Score:\b" + score);
        drawPiece(g);
        drawNext(g);
    }
}