import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PPM extends Image{

    private int maxGreyVal;
    private int[] red;
    private int[] green;
    private int[] blue;

    public PPM(String fileName,String flag, int width, int height, int maxGreyVal, int[] red, int[] green, int[] blue) {
        super(fileName,flag, width, height);
        this.maxGreyVal = maxGreyVal;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getMaxGreyVal() {
        return maxGreyVal;
    }

    public int[] getRed() {
        return red;
    }

    public int[] getGreen() {
        return green;
    }

    public int[] getBlue() {
        return blue;
    }

    public void setRed(int[] red) {
        this.red = red;
    }

    public void setGreen(int[] green) {
        this.green = green;
    }

    public void setBlue(int[] blue) {
        this.blue = blue;
    }

    public void writePPM(String fileName, int width, int height, int[] red, int[] green, int[] blue, int maxGreyVal) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        writer.write("P3");
        writer.newLine();
        writer.write("# new img");
        writer.newLine();
        writer.write(width + " " + height);
        writer.newLine();
        writer.write(maxGreyVal + "");
        writer.newLine();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int pixelIndex = row * width + col;
                writer.write(red[pixelIndex] + " ");
                writer.write(green[pixelIndex] + " ");
                writer.write(blue[pixelIndex] + " ");
            }
            writer.newLine();
        }
    }

    public void grayscale(int[] red, int[] green, int[] blue) {
        int totalPixels = red.length;
        double scale = 255.0 / maxGreyVal;

        for (int i = 0; i < totalPixels; i++) {
            int gray = (int) (0.299 * red[i] + 0.587 * green[i] + 0.114 * blue[i]);
            gray = (int) (gray * scale);

            red[i] = gray;
            green[i] = gray;
            blue[i] = gray;
        }
    }

    public void rotate(String direction)
    {
        if (direction.equals("right"))
        {
            int totalPixels=this.getWidth()*this.getHeight();
            int[] red=new int[totalPixels];
            int[] green=new int[totalPixels];
            int[] blue=new int[totalPixels];

            for (int y=0;y<this.getHeight();y++)
            {
                for (int x=0;x<this.getWidth();x++)
                {
                    int index=y*this.getWidth()+x;
                    int rIndex=x*this.getHeight()+(this.getHeight()-y-1);

                    red[rIndex]=this.getRed()[index];
                    green[rIndex]=this.getGreen()[index];
                    blue[rIndex]=this.getBlue()[index];
                }
            }

            setRed(red);
            setGreen(green);
            setBlue(blue);
        }
        else if (direction.equals("left")) {
            int totalPixels=this.getWidth()*this.getHeight();
            int[] red=new int[totalPixels];
            int[] green=new int[totalPixels];
            int[] blue=new int[totalPixels];

            for (int y=0;y<this.getHeight();y++)
            {
                for (int x=0;x<this.getWidth();x++)
                {
                    int index=y*this.getWidth()+x;
                    int rIndex=(this.getWidth()-x-1)*this.getHeight()+y;

                    red[rIndex]=this.getRed()[index];
                    green[rIndex]=this.getGreen()[index];
                    blue[rIndex]=this.getBlue()[index];
                }
            }

            setRed(red);
            setGreen(green);
            setBlue(blue);
        }
        else System.out.println("Unknown direction. Available: right,left");
    }

    public void invert()
    {
        int[] negRed=this.getRed();
        int[] negGreen=this.getGreen();
        int[] negBlue=this.getBlue();

        int totalPixels=this.getHeight()*this.getWidth();

        for (int i=0;i<totalPixels;i++)
        {
            negRed[i]=this.getMaxGreyVal()-this.getRed()[i];
            negGreen[i]=this.getMaxGreyVal()-this.getGreen()[i];
            negBlue[i]=this.getMaxGreyVal()-this.getBlue()[i];
        }

        setRed(negRed);
        setGreen(negGreen);
        setBlue(negBlue);
    }

    public void monochrome()
    {
        int[] monRed=this.getRed();
        int[] monGreen=this.getGreen();
        int[] monBlue=this.getBlue();

        int totalPixels=this.getHeight()*this.getWidth();

        for (int i=0;i<totalPixels;i++)
        {
            int avg=(monRed[i]+monGreen[i]+monBlue[i])/3;
            int monochromeVal;
            if(avg<128) monochromeVal=0;
            else monochromeVal=255;
            monRed[i]=monochromeVal;
            monGreen[i]=monochromeVal;
            monBlue[i]=monochromeVal;
        }

        this.setRed(monRed);
        this.setGreen(monGreen);
        this.setBlue(monBlue);
    }
}
