
import java.io.*;

public class PBM extends Image implements Cloneable{

    public PBM(String fileName,String flag, int width, int height, int[][] pixels) {
        super(fileName,flag, width, height, pixels);
    }


    public void invert() //негатив на изображението
    {
        int invertedPixels[][] = new int[this.getHeight()][this.getWidth()]; //нова матрица с размерите на оригиналното изображение

        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                if (this.getPixels()[y][x] == 0)       //обхожда се матрицата по редове и
                    invertedPixels[y][x] = 1;          // всяка стойност от оригиналната се записва в invertedPixels с
                else invertedPixels[y][x] = 0;         // противоположна стойност
            }
        }
        this.setPixels(invertedPixels); //задава новата матрица на изображението
    }
    public void writePBM(int[][] pixels,String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(getFlag()+"\n"); //записва флага
        writer.write(pixels[0].length + " " + pixels.length + "\n"); //записва размера
        for (int y = 0; y < pixels.length; y++) { //итерира през матрицата
            for (int x = 0; x < pixels[y].length; x++) {
                writer.write(pixels[y][x] + " "); //записва всеки пиксел
            }
            writer.write("\n"); //край на реда
        }

        writer.close();


    }

    public void rotate(String direction)
    {

        int width=this.getWidth();   //размерите на изображението, което се завърта
        int height=this.getHeight();
        if (direction.equals("right")) {  //завъртане на дясно
            int[][] rotatedP = new int[width][height]; //матрица с размерите на оригиналното изображение
            for (int y = 0; y < height; y++) {                             //всеки от пикселите се слага на новата позиция
                for (int x = 0; x < width; x++)
                    rotatedP[x][height - y - 1] = this.getPixels()[y][x];  //"първият" ред става "последна" колона от горе на долу
            }
            this.setPixels(rotatedP); //обновяваме оригиналната матрица с новата
            this.setHeight(width);    // задаваме новите размери
            this.setWidth(height);
        }
        else if (direction.equals("left"))
        {
            int[][] rotatedP = new int[width][height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++)
                    rotatedP[width-x-1][y] = this.getPixels()[y][x]; //"първия" ред става "първа" колона от долу на горе
            }
            this.setPixels(rotatedP);
            this.setHeight(width);
            this.setWidth(height);
        }
        else System.out.printf("Unknown direction. Available: right,left");
        }

    @Override
    public Image clone() throws CloneNotSupportedException {
        PBM copyPBM= (PBM) super.clone();
        int[][] clonedPixels = new int[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++) {
            clonedPixels[i] = this.getPixels()[i].clone();
        }
            copyPBM.setPixels(clonedPixels);

        return copyPBM;
    }


    @Override
    public Image collage(String direction,Image image1, Image image2, String fileName3) throws IOException {

        if (direction.equals("horizontal")) { //хоризонтален колаж
            int width1 = image1.getWidth(); //размери
            int width2 = image2.getWidth();
            int height = image1.getHeight();
            int totalWidth = width1 + width2;

            int[][] collagePixels = new int[height][totalWidth]; //матрица за новото изображение с височината и новата ширина

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width1; x++) {                   // матрицата на първото изображение се поставя
                    collagePixels[y][x] = image1.getPixels()[y][x]; // в първата половина от новата матрица
                }
            }

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width2; x++) {                            // матрицата на второто изображение се поставя
                    collagePixels[y][x + width1] = image2.getPixels()[y][x];  // в другата половина на новата матрица
                }
            }
            return new PBM(fileName3, this.getFlag(), totalWidth, height, collagePixels); //създава се обект с новото изображение
        }
        else if(direction.equals("vertical")) {

            int width = image1.getWidth();      //размери
            int height1 = image1.getHeight();
            int height2 = image2.getHeight();
            int totalHeight = height1 + height2; //височина на колажа

            int[][] collagePixels = new int[totalHeight][width]; //нова матрица

            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width; x++) {                    // горната половина на матрицата се запълва
                    collagePixels[y][x] = image1.getPixels()[y][x]; // с матрицата от първото изображение
                }
            }

            for (int y = 0; y < height2; y++) {
                for (int x = 0; x < width; x++) {                               //останалата част се запълва с матрицата от
                    collagePixels[y + height1][x] = image2.getPixels()[y][x];   // изображение 2
                }
            }
            return new PBM(fileName3,this.getFlag(),width,totalHeight,collagePixels);
        }
        else throw new IllegalArgumentException("Unknown direction. Available: horizontal, vertical.");
        }
    }


