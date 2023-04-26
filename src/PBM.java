import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PBM extends Image {

    public PBM(int width, int height, int[][] pixels) {
        super(width, height, pixels);
    }


    public static Image read(String fileName) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./resources/" + fileName));

            String line = reader.readLine();
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
            return new PBM(width, height, pixels);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
}