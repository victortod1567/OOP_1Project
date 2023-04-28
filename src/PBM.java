import java.io.*;

public class PBM extends Image {

    public PBM(String flag, int width, int height, int[][] pixels) {
        super(flag, width, height, pixels);
    }


    public void invert()
    {
        int invertedPixels[][] = new int[this.getHeight()][this.getWidth()];

        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                if (this.getPixels()[y][x] == 0)
                    invertedPixels[y][x] = 1;
                else invertedPixels[y][x] = 0;
            }
        }
        this.setPixels(invertedPixels);
    }
    public void writePBM(int[][] pixels,String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(getFlag()+"\n");
        writer.write(pixels[0].length + " " + pixels.length + "\n");
        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                writer.write((pixels[y][x] == 1 ? 0 : 1) + " ");
            }
            writer.write("\n");
        }

        writer.close();


    }
    /*public static Image read(String fileName) throws IOException {
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
*/
   /* public void invert()
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
    */

}