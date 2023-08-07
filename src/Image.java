

import com.sun.media.sound.InvalidFormatException;

import java.io.*;

public abstract class Image implements Cloneable{
    //Общи за форматите полета
    private String fileName;
    private String flag;
    private int width;
    private int height;
    private int[][] pixels;


    public Image(String fileName,String flag, int width, int height, int[][] pixels) {
        this.fileName=fileName;
        this.flag = flag;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public Image(String fileName,String flag, int width, int height) {
        this.fileName=fileName;
        this.flag = flag;
        this.width = width;
        this.height = height;
    }

    @Override
    public Image clone() throws CloneNotSupportedException {
        //методът прави deepCopy на изображение, използвани при backup, за командата undo
        return (Image) super.clone();
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //Методът разпознава формата на изображението и изпълнява съответния алгоритъм за четене
    public static Image read(String fileName) throws IOException
    {
                    BufferedReader reader = new BufferedReader(new FileReader("./resources/" + fileName));
                    String line = reader.readLine();
                    String flag = line; //flag указва формата
                    while (line.startsWith("P") || line.startsWith("#")) //прескачаме коментари ако има такива
                        line = reader.readLine();

                    String[] sizeD = line.split(" "); //прочитаме размерите на изображението и ги записваме в width и height
                    int width = Integer.parseInt(sizeD[0]);
                    int height = Integer.parseInt(sizeD[1]);
                    int maxGreyVal = 0; //максимална стойност на пиксела (Formats.PPM и Formats.PGM)

                    if (flag.equals("P1")) { //Formats.PBM
                        int[][] pixels = new int[height][width]; //инициализираме матрица със съответните размери
                        for (int y = 0; y < height; y++) {       //прочита всеки пиксел по редове и ги записва в матрицата
                            line = reader.readLine();
                            String row[] = line.trim().split(" "); //масив с пикселите от съответния ред
                            for (int x = 0; x < width; x++)
                                pixels[y][x] = Integer.parseInt(row[x]);
                        }
                        reader.close();
                        return new PBM(fileName, flag, width, height, pixels);
                    } else if (flag.equals("P2")) {
                        int[][] pixels = new int[height][width]; //матрица с размерите
                        line = reader.readLine(); //прочита максималната стойност на пикселите и я записва в maxGreyVal
                        maxGreyVal = Integer.parseInt(line);

                        int row = 0; //инициализация за ред и колона
                        int col = 0;
                        while ((line = reader.readLine()) != null) {
                            String[] values = line.split(" "); //масив със стойностите от 1 ред
                            for (String value : values) {
                                pixels[row][col] = Integer.parseInt(value);
                                col++;
                                if (col == width) { //ако col достигне width значи редът е прочетен и преминава на следващия
                                    col = 0;
                                    row++;
                                    if (row == height) break;
                                }
                            }
                        }

                        reader.close();
                        return new PGM(fileName, flag, width, height, pixels, maxGreyVal);
                    } else if (flag.equals("P3")){
                        line = reader.readLine(); //чете максималната стойност на пикселите и я присвоява на maxGreyVal
                        maxGreyVal = Integer.parseInt(line);
                        int totalPixels = width * height;  //общия брой пиксели в изображението
                        int[] red = new int[totalPixels]; //масиви за стойностите на всеки RGB channel
                        int[] green = new int[totalPixels];
                        int[] blue = new int[totalPixels];

                        int count = 0; //брояч за прочетените пиксели
                        while ((line = reader.readLine()) != null && count < totalPixels) {
                            String[] values = line.trim().split(" "); //масивът съдържа стойностите на ред за всеки от трите канала
                            for (int i = 0; i < values.length; i += 3) { //
                                red[count] = Integer.parseInt(values[i]);
                                green[count] = Integer.parseInt(values[i + 1]);
                                blue[count] = Integer.parseInt(values[i + 2]);
                                count++;

                                if (count >= totalPixels) {
                                    break;
                                }
                            }
                        }

                        return new PPM(fileName, flag, width, height, maxGreyVal, red, green, blue);
                    }
                    else throw new InvalidFormatException("File is not Netpbm");

    }

    public void invert() throws IOException {
        try {
            this.invert();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void rotate(String direction)
    {
        this.rotate(direction); //метода е презаписан във всеки от наследниците на Formats.Image и се извиква за съответния формат
    }

    public void write(String fileName) throws IOException {
        try {
            if (this instanceof PBM) ((PBM) this).writePBM(this.getPixels(), fileName);
            else if (this instanceof PGM) ((PGM) this).writePGM(this.getPixels(), fileName);
            else if (this instanceof PPM)
                ((PPM) this).writePPM(fileName, this.getWidth(), this.getHeight(), ((PPM) this).getRed(), ((PPM) this).getGreen(), ((PPM) this).getBlue(), ((PPM) this).getMaxGreyVal());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void monochrome()
    {
        if (this instanceof PBM) return;
            else this.monochrome();
    }

    public Image collage(String direction, Image image1,Image image2,String fileName3) throws IOException {

        if (!image1.getFlag().equals(image2.getFlag()) ||
                image1.getWidth() != image2.getWidth() ||
                image1.getHeight() != image2.getHeight()) {
            throw new IllegalArgumentException("Images must have the same format and size");
        }
        return this.collage(direction,image1,image2,fileName3);
    }



}