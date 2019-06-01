import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class Game {

    private int[][] grid;
    private Rider rider1;
    private Rider rider2;
    private Board board;
    private GameFrame frame;
    private int medDir1;
    private int medDir2;
    private int timeDelay = 50;
    private boolean pause = false;
    private boolean isRunning = false;

    public Game()
    {

        init();

        //set up the frame and board
        board = new Board(grid, rider1, rider2);
        frame = new GameFrame(board, this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(board);
        frame.pack();
        frame.validate();
        frame.setVisible(true);
        board.update();
        frame.repaint();

        //set up listeners
        board.setFocusable(true);
        board.requestFocusInWindow();
        board.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                //System.out.println("Please do something.");
                int z = e.getKeyCode();
                if (z == KeyEvent.VK_W) medDir1 = 1;
                else if (z == KeyEvent.VK_A) medDir1 = 2;
                else if (z == KeyEvent.VK_S) medDir1 = 3;
                else if (z == KeyEvent.VK_D) medDir1 = 0;

                else if (z == KeyEvent.VK_UP) medDir2 = 1;
                else if (z == KeyEvent.VK_LEFT) medDir2 = 2;
                else if (z == KeyEvent.VK_DOWN) medDir2 = 3;
                else if (z == KeyEvent.VK_RIGHT) medDir2 = 0;

                else if (z == KeyEvent.VK_P && isRunning)
                {
                    if (!pause)
                    {
                        pause = true;
                        frame.getProgressLabel().setText("Paused.");
                    }
                    else
                    {
                        pause = false;
                        frame.getProgressLabel().setText("Good luck!");
                    }
                }
            }
        });
    }

    public void play()
    {
        frame.getPlayButton().setEnabled(false);
        clear();
        GameSwingWorker gameLoop = new GameSwingWorker();
        gameLoop.execute();
    }

    public class GameSwingWorker extends SwingWorker<Integer, Void>
    {
        @Override
        protected Integer doInBackground() throws Exception {
            frame.getProgressLabel().setText("3");
            Thread.sleep(500);
            frame.getProgressLabel().setText("2");
            Thread.sleep(500);
            frame.getProgressLabel().setText("1");
            Thread.sleep(500);
            frame.getProgressLabel().setText("Good luck!");
            isRunning = true;
            while (true)
            {
                if (!pause)
                {
                    board.setFocusable(true);
                    board.requestFocusInWindow();


                    if ((medDir1 + 2) % 4 != rider1.getDir()) rider1.setDir(medDir1);
                    if ((medDir2 + 2) % 4 != rider2.getDir()) rider2.setDir(medDir2);


                    int z1 = rider1.move();
                    int z2 = rider2.move();


                    if (z1 == -1 || z2 == -1) return -1;
                    else if (z1 == -3) return 1;
                    else if (z2 == -3) return 0;
                    else if (z1 > 0) return 1;
                    else if (z2 > 0) return 0;


                    board.update();
                    frame.repaint();
                    Thread.sleep(timeDelay);
                }
            }
        }

        protected void done()
        {
            int status = -99999;
            try { status = get(); } catch (Exception e) {}
            System.out.println(status);
            if (status == -1) frame.getProgressLabel().setText("Tie!");
            else if (status == 1) frame.getProgressLabel().setText("Player 2 wins!");
            else if (status == 0) frame.getProgressLabel().setText("Player 1 wins!");
            else frame.getProgressLabel().setText("What happened?");
            frame.getPlayButton().setText("Play Again?");
            frame.getPlayButton().setEnabled(true);
            isRunning = false;
        }
    }

    public void init()
    {
        grid = new int[100][100];
        rider1 = new Rider(grid[0].length / 2, 10, 3, Color.GREEN, 1, grid);
        rider2 = new Rider(grid[0].length / 2, grid.length - 10, 1, Color.BLUE, 2, grid);
        medDir1 = rider1.getDir();
        medDir2 = rider2.getDir();
    }

    public void clear()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                grid[i][j] = 0;
            }
        }
        rider1 = null;
        rider1 = new Rider(grid[0].length / 2, 10, 3, Color.GREEN, 1, grid);
        rider2 = null;
        rider2 = new Rider(grid[0].length / 2, grid.length - 10, 1, Color.BLUE, 2, grid);
        medDir1 = rider1.getDir();
        medDir2 = rider2.getDir();
        board.update();
        frame.repaint();
    }

    public void print()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] > 0) System.out.print(grid[i][j]);
                else if (grid[i][j] < 0) System.out.print("#");
                else System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
