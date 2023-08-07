

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PPM extends Image implements Cloneable{

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

    public void setMaxGreyVal(int maxGreyVal) {
        this.maxGreyVal = maxGreyVal;
    }

    @Override
    public Image clone() throws CloneNotSupportedException {
        PPM copyPPM=(PPM) super.clone();
        int totalPixels=this.getHeight()*this.getWidth();
        int[] red = new int[totalPixels];
        int[] green = new int[totalPixels];
        int[] blue = new int[totalPixels];

        for (int i=0;i<totalPixels;i++)
        {
            red[i]=this.getRed()[i];
            green[i]=this.getGreen()[i];
            blue[i]=this.getBlue()[i];
        }

        copyPPM.setRed(red);
        copyPPM.setGreen(green);
        copyPPM.setBlue(blue);
        copyPPM.setMaxGreyVal(this.maxGreyVal);

        return copyPPM;
    }

    public void writePPM(String fileName, int width, int height, int[] red, int[] green, int[] blue, int maxGreyVal) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));//създава FileWriter с аргумент името на файла

        writer.write("P3");//записва флага
        writer.newLine();
        writer.write(width + " " + height);//записва рамерите
        writer.newLine();
        writer.write(maxGreyVal + "");//записва максималната стойност за пиксел
        writer.newLine();

        for (int row = 0; row < height; row++) {    //итерира през всеки ред
            for (int col = 0; col < width; col++) {     //за всеки пиксел от реда
                int pixelIndex = row * width + col;     //се записват съответно стойностите за
                writer.write(red[pixelIndex] + " ");   //червено
                writer.write(green[pixelIndex] + " "); //зелено
                writer.write(blue[pixelIndex] + " "); // и синьо с интервал след всяка стойност
            }
            writer.newLine();
        }
    }

    public void grayscale(int[] red, int[] green, int[] blue) {
        int totalPixels = red.length;
        double scale = 255.0 / maxGreyVal;//пресмята scaling factor като разделя 255 на максималната стойност за пиксел

        for (int i = 0; i < totalPixels; i++) { //за всеки пиксел от изображението
            int gray = (int) (0.299 * red[i] + 0.587 * green[i] + 0.114 * blue[i]);//пресмята grayscale стойността по съответната формула
            gray = (int) (gray * scale);    //и тя се умножава по scale factor-а
                            //записва се за всеки един от трите RGB канала
            red[i] = gray;
            green[i] = gray;
            blue[i] = gray;
        }
    }

    public void rotate(String direction)
    {
        int width=this.getWidth();      //размерите на
        int height=this.getHeight();    // оригиналното изображение
        int totalPixels=width*height;   //общ брой пиксели
        if (direction.equals("right"))  //завъртане надясно
        {
            int[] red=new int[totalPixels];     //масив за всеки
            int[] green=new int[totalPixels];   //трите RGB канала
            int[] blue=new int[totalPixels];

            for (int y=0;y<height;y++)      //итерира всеки от редовете
            {
                for (int x=0;x<width;x++)  //за всеки пиксел от реда
                {
                    int index=y*width+x;    //индекс на пиксела от оригиналното изображение
                    int rIndex=x*height+(height-y-1);   //индекс на завъртяното изображение

                    red[rIndex]=this.getRed()[index];      //за всеки от трите масива
                    green[rIndex]=this.getGreen()[index];  //се записва на новия индекс
                    blue[rIndex]=this.getBlue()[index];    //стойността от оригиналния индекс
                }
            }

            setRed(red);            //записват се новите стойности
            setGreen(green);        //и размери
            setBlue(blue);          //в оригиналното изображение
            this.setHeight(width);
            this.setWidth(height);
        }
        else if (direction.equals("left")) { //завъртане наляво
            int[] red=new int[totalPixels];     //масиви за трите RGB канала
            int[] green=new int[totalPixels];
            int[] blue=new int[totalPixels];

            //алгоритъмът е като този за надясно, единствената разлика е във формулата за индекс
            for (int y=0;y<height;y++)
            {
                for (int x=0;x<width;x++)
                {
                    int index=y*width+x;
                    int rIndex=(width-x-1)*height+y;

                    red[rIndex]=this.getRed()[index];
                    green[rIndex]=this.getGreen()[index];
                    blue[rIndex]=this.getBlue()[index];
                }
            }

            setRed(red);
            setGreen(green);
            setBlue(blue);
            this.setHeight(width);
            this.setWidth(height);;
        }
        else System.out.println("Unknown direction. Available: right,left");
    }

    public void invert()
    {   //негатив на Formats.PPM изображение
        int[] negRed=this.getRed();         //нови масиви за
        int[] negGreen=this.getGreen();     //всеки от трите канала
        int[] negBlue=this.getBlue();

        int totalPixels=this.getHeight()*this.getWidth();   //общ брой пиксели

        for (int i=0;i<totalPixels;i++)     //за всеки пиксел
        {
            negRed[i]=this.getMaxGreyVal()-this.getRed()[i];        //в новия масив съответно за червено,зелено и синьо
            negGreen[i]=this.getMaxGreyVal()-this.getGreen()[i];    //се записва на съответния пиксел
            negBlue[i]=this.getMaxGreyVal()-this.getBlue()[i];      //като от максималната стойност за пиксел извадим текущата от оригиналните масиви
        }

        setRed(negRed);         //новите масиви
        setGreen(negGreen);     //се задават на оригиналното изображение
        setBlue(negBlue);
    }

    public void monochrome()
    {   //преобразува изображението в черно-бяло
        int[] monRed=this.getRed();         //нови масиви за трите канала със
        int[] monGreen=this.getGreen();     //първоначални стойности - тези от оригиналното изображение
        int[] monBlue=this.getBlue();

        int totalPixels=this.getHeight()*this.getWidth(); //общ брой пиксели

        for (int i=0;i<totalPixels;i++)     //за всеки пиксел
        {
            int avg=(monRed[i]+monGreen[i]+monBlue[i])/3;   //пресмята средната стойност от трите калана
            int monochromeVal;  //стойност за монохром
            if(avg<128) monochromeVal=0;    //ако средната стойност на пиксела е <128 - monochromeVal=0, тоест пиксела става черен
            else monochromeVal=255;         //в противен случай се преобразува до бял
            monRed[i]=monochromeVal;        //във всеки от новите масиви се презаписва
            monGreen[i]=monochromeVal;      //пиксела с новата стойност
            monBlue[i]=monochromeVal;
        }

        this.setRed(monRed);        //обновяват се масивите в
        this.setGreen(monGreen);    //оригиналното изображение
        this.setBlue(monBlue);
    }

    @Override
    public Image collage(String direction, Image image1, Image image2, String fileName3) throws IOException {

        if (direction.equals("horizontal")) {   //хоризонтален колаж
            int totalWidth=image1.getWidth()+image2.getWidth(); //нова ширина на изображението
            int height=image1.getHeight();      //височина
            int totalPixels = totalWidth * height; //общ брой пиксели на новото изображение
            int[] red = new int[totalPixels];      //масиви за трите канала
            int[] green = new int[totalPixels];    //с размер на новото изображение
            int[] blue = new int[totalPixels];

            //масивите от първото изображение се добавят в първата половина на новите масиви
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < image1.getWidth(); x++) {
                    int index = y * totalWidth + x;
                    red[index] = ((PPM)image1).getRed()[y * image1.getWidth() + x];
                    green[index] = ((PPM)image1).getGreen()[y * image1.getWidth() + x];
                    blue[index] = ((PPM)image1).getBlue()[y * image1.getWidth() + x];
                }
            }
            //масивите от второто изображение се добавят в останалата половина от новите масиви
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < image2.getWidth(); x++) {
                    int index = y * totalWidth + (x + image1.getWidth());
                    red[index] = ((PPM)image2).getRed()[y * image2.getWidth() + x];
                    green[index] = ((PPM)image2).getGreen()[y * image2.getWidth() + x];
                    blue[index] = ((PPM)image2).getBlue()[y * image2.getWidth() + x];
                }
            }

            return new PPM(fileName3,image1.getFlag(),totalWidth,height,this.maxGreyVal,red,green,blue);  //създава се новото изображение
        }
        else if(direction.equals("vertical")) { //вертикален колаж

            int width=image1.getWidth();                                //ширина
            int totalHeight=image1.getHeight()+ image2.getHeight();     //височина на новото изображение
            int totalPixels = width * totalHeight;      //общ брой пиксели в новото изображение
            int[] red = new int[totalPixels];           //нови масиви за трите канала
            int[] green = new int[totalPixels];
            int[] blue = new int[totalPixels];

            //масивите от първото изображение се добавят по съответните формули. Това изображение се добавя в горната част на колажа
            for (int y = 0; y < image1.getHeight(); y++) {
                for (int x = 0; x < width; x++) {
                    int index = y * width + x;
                    red[index] = ((PPM)image1).getRed()[y * width + x];
                    green[index] = ((PPM)image1).getGreen()[y * width + x];
                    blue[index] = ((PPM)image1).getBlue()[y * width + x];
                }
            }

            //масивите от второто иьображение се добавят с останалата част от колажа
            for (int y = 0; y < image2.getHeight(); y++) {
                for (int x = 0; x < width; x++) {
                    int index = (y+image1.getHeight())*width+x;
                    red[index] = ((PPM)image2).getRed()[y * width + x];
                    green[index] = ((PPM)image2).getGreen()[y * width + x];
                    blue[index] = ((PPM)image2).getBlue()[y * width + x];
                }
            }

            return new PPM(fileName3,image1.getFlag(),width,totalHeight,this.maxGreyVal,red,green,blue);//създава се новото изображение
        }
        //ако посоката е невалидна се хвърля грешка
        else throw new IllegalArgumentException("Unknown direction. Available: horizontal, vertical.");
    }
}

