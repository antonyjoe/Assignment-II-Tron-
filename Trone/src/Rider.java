import java.awt.Color;
public class Rider {

    private int x;
    private int y;
    private int dir;
    private Color trailColor;
    private int id;
    private int[][] grid;

    public Rider(int x, int y, int dir, Color trailColor, int id, int[][] grid)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.trailColor = trailColor;
        this.id = id;
        this.grid = grid;
        grid[y][x] = -1*id;
    }

    public int move()
    {
        int[] dX = {1, 0, -1, 0};
        int[] dY = {0, -1, 0, 1};
        int newX = x + dX[dir];
        int newY = y + dY[dir];

        if (newX < 0 || newX >= grid[0].length || newY < 0 || newY >= grid.length)
        {
            System.out.println("Out of bounds.");
            return -3;
        }
        else if (grid[newY][newX] == 0)
        {
            grid[newY][newX] = -1*id;
            grid[y][x] = id;
            x = newX;
            y = newY;
            return 0;
        } else
        {
            System.out.println("Crash.");
            return grid[newY][newX];
        }
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getDir()
    {
        return this.dir;
    }

    public Color getColor()
    {
        return this.trailColor;
    }

    public void setDir(int dir)
    {
        this.dir = dir;
    }
}
