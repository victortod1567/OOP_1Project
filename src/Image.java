import java.io.*;
import java.util.Scanner;

public class Image {

    private String flag;
    private int width;
    private int height;
    private int[][] pixels;
    private int[][][] ppmPixels;


    public Image(String flag, int width, int height, int[][] pixels) {
        this.flag = flag;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public Image(String flag, int width, int height, int[][][] ppmPixels) {
        this.flag = flag;
        this.width = width;
        this.height = height;
        this.ppmPixels = ppmPixels;
    }

    public Image(String flag, int width, int height) {
        this.flag = flag;
        this.width = width;
        this.height = height;
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

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
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
        int maxGreyVal=0;

        if (flag.equals("P1"))
        {
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

        else if (flag.equals("P2"))
        {
            int[][] pixels = new int[height][width];
            line= reader.readLine();
            maxGreyVal=Integer.parseInt(line);

            int row=0;
            int col=0;
            while((line=reader.readLine())!=null)
            {
                String[] values=line.split(" ");
                for (String value : values) {
                    pixels[row][col]=Integer.parseInt(value);
                    col++;
                    if (col==width)
                    {
                        col=0;
                        row++;
                        if (row==height) break;
                    }
                }
            }

            reader.close();
            return new PGM(flag,width,height,pixels,maxGreyVal);
        }

        else {
            line = reader.readLine();
            maxGreyVal = Integer.parseInt(line);
            int totalPixels=width*height;
            int[] red=new int[totalPixels];
            int[] green=new int[totalPixels];
            int[] blue=new int[totalPixels];

            int count=0;
            while ((line = reader.readLine()) != null && count < totalPixels) {
                String[] values = line.trim().split(" ");
                for (int i = 0; i < values.length; i += 3) {
                    red[count] = Integer.parseInt(values[i]);
                    green[count] = Integer.parseInt(values[i + 1]);
                    blue[count] = Integer.parseInt(values[i + 2]);
                    count++;

                    if (count >= totalPixels) {
                        break;
                    }
                }
            }

            return new PPM(flag,width,height,maxGreyVal,red,green,blue);
        }

    }

    public void invert() throws IOException {
        this.invert();
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