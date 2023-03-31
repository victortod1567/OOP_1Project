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

            String format = reader.readLine();
            String check=reader.readLine();
            String size;
            if (check.charAt(0)=='#') {
                String comment = check;
                size = reader.readLine();
            }
            else size = check;

            String[] sizeD = size.split(" ");
            int width = Integer.parseInt(sizeD[0]);
            int height = Integer.parseInt(sizeD[1]);
            int[][] pixels = new int[width][height];

            int row = 0;
            int column = 0;
            int value;
            while ((value = reader.read()) != -1) {
                if (value == 0 || value == 1) {
                    pixels[column][row] = value - 0;
                    column++;

                    if (column == width) {
                        column = 0;
                        row++;
                    }
                } else if (value == '\n' || value == '\r') {
                }
            }
            reader.close();
            return new PBM(width, height, pixels);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void invert(PBM img) throws IOException {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (img.getPixels()[i][j]==0) img.getPixels()[i][j]=1;
                else img.getPixels()[i][j]=0;
            }
        }

        // write the inverted image to a new file
        FileWriter writer = new FileWriter("./resources/inverted-image.pbm");
        writer.write("P1" + "\n");
        writer.write(img.getWidth() + " " + img.getHeight() + "\n");
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                writer.write(img.getPixels()[j][i] + " ");
            }
            writer.write("\n");
        }
        writer.close();
    }
    }

