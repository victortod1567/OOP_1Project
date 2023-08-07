

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PGM extends Image implements Cloneable{

    private int maxGreyVal;

    public PGM(String fileName,String flag, int width, int height, int[][] pixels, int maxGreyVal) {
        super(fileName,flag, width, height, pixels);
        this.maxGreyVal=maxGreyVal;
    }

    public int getMaxGreyVal() {
        return maxGreyVal;
    }

    public void setMaxGreyVal(int maxGreyVal) {
        this.maxGreyVal = maxGreyVal;
    }

    @Override
    public Image clone() throws CloneNotSupportedException {
        PGM copyPGM= (PGM) super.clone();
        int[][] clonedPixels = new int[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++) {
            clonedPixels[i] = this.getPixels()[i].clone();
        }
        copyPGM.setPixels(clonedPixels);
        copyPGM.setMaxGreyVal(this.getMaxGreyVal());

        return copyPGM;
    }

    public void invert() {  //негатив на изображението
        int width=this.getWidth(); //размери
        int height=this.getHeight();
        int[][] pixels=this.getPixels(); //матрица с пикселите от оригиналното изображение

        int [][] negativePixels=new int[height][width]; //матрица за новото изображение със същите размери
        for (int y=0;y<height;y++)         //матрицата се обхожда по всеки ред
        {
            for (int x=0;x<width;x++)
            {
                negativePixels[y][x]=maxGreyVal-pixels[y][x]; //за всеки пиксел от реда се изважда оригиналната стойност от максималната
            }                                                 //резултатът се записва в новата матрица
        }
        this.setPixels(negativePixels);  //новата матрица се записва в оригиналното изображение
    }

    public void writePGM(int[][] pixels,String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename)); //създава FileWriter, който приема като аргумент името на файла
        writer.write("P2\n");       //записва флага
        writer.write(pixels[0].length + " " + pixels.length + "\n"); //записваме размерите
        writer.write(maxGreyVal + "\n"); //записва максималната стойност на пикселите
        for (int[] row : pixels) { //масив за редовете от матрицата
            for (int pixel : row) { //прочитат се един по един всеки пиксел от реда
                writer.write(pixel + " ");  //и се записват
            }
            writer.write("\n"); //при край на реда се записва знам за нов ред
        }
        writer.close();
    }

    public void rotate(String direction)
    {
        int width=this.getWidth();      //размери на оригиналното изображение
        int height=this.getHeight();
        if (direction.equals("right")) //завъртане на дясно
        {
            int[][] rotatedP=new int[width][height]; //матрица за новото изображение
            for (int y=0;y<height;y++)
            {
                for (int x=0;x<width;x++)
                    rotatedP[x][height-y-1]=this.getPixels()[y][x]; //разменят се x и y и се записва новата матрица

            }
            this.setPixels(rotatedP); //записва новата матрица в оригиналното изображение
            this.setHeight(width);    //задава новите размери
            this.setWidth(height);
        }

        else if (direction.equals("left")) //завъртане наляво
        {
            int[][] rotatedP=new int[width][height];
            for (int y=0;y<height;y++)
            {
                for (int x=0;x<width;x++)
                    rotatedP[width-x-1][y]=this.getPixels()[y][x]; //"първи" ред става "първа" колона от долу на горе
            }
            this.setPixels(rotatedP);   //записва новата матрица в оригиналното изображение
            this.setHeight(width);      //задава новите размери
            this.setWidth(height);
        }
        else System.out.println("Unknown direction. Available: right,left"); //ако посоката е невалидна се извежда съобщението
    }

    public void monochrome()
    {
        int monochromeVal=this.maxGreyVal/2;  //monochrome стойност, която се получава от максималната за пикселите стойност/2
        int[][] pixels=this.getPixels(); //нова матрица със стойностите на оригиналната

        for (int row=0;row<this.getHeight();row++)      //цикълът обхожда матрицата по редовв
            for (int col=0;col<this.getWidth();col++)
            {
                int pixel=pixels[row][col]; //присвоява се текущия пиксел
                if (pixel<monochromeVal) pixels[row][col]=0;    //ако е < от monochrome стойността става 0(черен)
                else pixels[row][col]=this.maxGreyVal;          //в противен слувай става бял
            }

        this.setPixels(pixels);     //новата матрица се записва в изображението
    }

    @Override
    public Image collage(String direction, Image image1, Image image2, String fileName3) throws IOException {

        if (direction.equals("horizontal")) { //хоризонтален колаж
            int totalWidth = image1.getWidth() + image2.getWidth(); //нова ширина на изображението
            int[][] collagePixels = new int[image1.getHeight()][totalWidth];//нова матрица за изображението

            for (int y = 0; y < image1.getHeight(); y++) {     //обхожда се матрицата на първото изображение и се записва в новата матрица
                for (int x = 0; x < image1.getWidth(); x++) {
                    collagePixels[y][x] = image1.getPixels()[y][x];
                }
            }

            for (int y = 0; y < image2.getHeight(); y++) {  //матрицата на второто изображение се записва в останалата част от новата матрица
                for (int x = 0; x < image2.getWidth(); x++) {
                    collagePixels[y][x + image1.getWidth()] = image2.getPixels()[y][x];
                }
            }
            return new PGM(fileName3, image1.getFlag(), totalWidth, image1.getHeight(), collagePixels, this.maxGreyVal);//създава се новото изображение
        }
        else if(direction.equals("vertical")) { //вертикален колаж
            int totalHeight=image1.getHeight() + image2.getHeight(); //нова височина на изображението
            int[][] collagePixels = new int[totalHeight][image1.getWidth()]; //нова матрица

            for (int y = 0; y < image1.getHeight(); y++) {      //матрицата на първото изображение се записва
                for (int x = 0; x < image1.getWidth(); x++) {   //в горната половина на новата матрица
                    collagePixels[y][x] = image1.getPixels()[y][x];
                }
            }

            for (int y = 0; y < image2.getHeight(); y++) {      //матрицата на второто изображение се записва
                for (int x = 0; x < image2.getWidth(); x++) {   //в останалата половина от новата  матрица
                    collagePixels[y + image1.getHeight()][x] = image2.getPixels()[y][x];
                }
            }

            return new PGM(fileName3, image1.getFlag(), image1.getWidth(), totalHeight,collagePixels,this.maxGreyVal);//създава се новото изображение
        }
        else throw new IllegalArgumentException("Unknown direction. Available: horizontal, vertical.");//ако посоката е невалидна се хвърля грешка
    }
    }

