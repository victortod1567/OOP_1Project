import java.io.*;

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

            for (int y = 0; y < height; y++) {
                line = reader.readLine();
                String row[] = line.trim().split(" ");
                for (int x = 0; x < row.length; x++) {
                    pixels[y][x] = (int) (255.0 * Double.parseDouble(row[x]) / maxGreyVal);
                }
            }
            reader.close();
            return new PGM(flag,width,height,pixels,maxGreyVal);
        }

        else {
            int[][][] ppmPixels = new int[height][width][3];
            line = reader.readLine();
            maxGreyVal = Integer.parseInt(line);

            for (int y=0; y<height; y++) {
                line = reader.readLine();
                String row[] = line.trim().split(" ");
                for (int x=0; x<row.length; x++) {
                    if (x*3+2<row.length) {
                        ppmPixels[y][x][0] = (int) (255.0 * Double.parseDouble(row[x * 3]) / maxGreyVal);
                        ppmPixels[y][x][1] = (int) (255.0 * Double.parseDouble(row[x * 3 + 1]) / maxGreyVal);
                        ppmPixels[y][x][2] = (int) (255.0 * Double.parseDouble(row[x * 3 + 2]) / maxGreyVal);
                    }
                }
            }

            return new PPM(flag,width,height,ppmPixels,maxGreyVal);
        }

    }

    public void invert()
    {
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