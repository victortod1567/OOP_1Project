import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Image {

    private String flag;
    private int width;
    private int height;
    private int[][] pixels;


    public Image(String flag, int width, int height, int[][] pixels) {
        this.flag = flag;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public String getFlag() {
        return flag;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public static Image read(String fileName) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("./resources/" + fileName));
        String line = reader.readLine();
        String flag=line;
        while ( line.startsWith("P") || line.startsWith("#"))
            line= reader.readLine();
        String size;

        String[] sizeD = line.split(" ");
        int width = Integer.parseInt(sizeD[0]);
        int height = Integer.parseInt(sizeD[1]);
        int[][] pixels = new int[height][width];


        for (int y=0;y<height;y++)
        {
            line= reader.readLine();
            String row[]=line.trim().split(" ");
            for (int x=0;x<width;x++)
                pixels[y][x]=Integer.parseInt(row[x]);
        }
        reader.close();
        return new PBM(flag,width,height,pixels);
    }

    public void invert()
    {
        int invertedPixels[][]=new int[this.getWidth()][this.getHeight()];

        for (int x=0;x<this.getWidth();x++)
        {
            for (int y=0;y<this.getHeight();y++)
            {
                if(this.getPixels()[x][y]==0)
                    invertedPixels[x][y]=1;
                else invertedPixels[x][y]=0;
            }
        }

    }

    //public abstract void write(Image img, String fileName);

    /*
    BufferedReader reader = new BufferedReader(new FileReader("./resources/" + fileName));
        String line = reader.readLine();
        String flag=line;
        while ( line.startsWith("P") || line.startsWith("#"))
            line= reader.readLine();
        String size;

        String[] sizeD = line.split(" ");
        int width = Integer.parseInt(sizeD[0]);
        int height = Integer.parseInt(sizeD[1]);
        int[][] pixels = new int[width][height];


        for (int y=0;y<height;y++)
        {
            line= reader.readLine();
            String row[]=line.trim().split(" ");
            for (int x=0;x<width;x++)
                pixels[x][y]=Integer.parseInt(row[x]);
        }
        reader.close();
        return new PBM(flag,width,height,pixels);
     */
}