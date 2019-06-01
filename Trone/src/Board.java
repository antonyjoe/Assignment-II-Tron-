import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Board extends JPanel {

    private int[][] grid;
    private Rider rider1;
    private Rider rider2;
    private JLabel[][] labelGrid;

    public Board(int[][] grid, Rider rider1, Rider rider2)
    {
        this.grid = grid;
        this.rider1 = rider1;
        this.rider2 = rider2;
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));
        labelGrid = new JLabel[grid.length][grid[0].length];
        setLayout(new GridLayout(grid.length, grid[0].length));
        for (int i = 0; i < labelGrid.length; i++)
        {
            for (int j = 0; j < labelGrid[0].length; j++)
            {
                labelGrid[i][j] = new JLabel();
                labelGrid[i][j].setOpaque(true);
                labelGrid[i][j].setBackground(Color.BLACK);
                add(labelGrid[i][j]);
            }
        }
        repaint();
        validate();
    }

    public void update()
    {
        for (int i = 0; i < labelGrid.length; i++)
        {
            for (int j = 0; j < labelGrid[i].length; j++)
            {
                if (grid[i][j] == 1) labelGrid[i][j].setBackground(rider1.getColor());
                else if (grid[i][j] == 2) labelGrid[i][j].setBackground(rider2.getColor());
                else if (grid[i][j] < 0) labelGrid[i][j].setBackground(Color.WHITE);
                else labelGrid[i][j].setBackground(Color.BLACK);
            }
        }
        repaint();
    }
}
