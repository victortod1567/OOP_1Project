public class PPM extends Image{

    private int maxGreyVal;
    public PPM(String flag, int width, int height, int[][][] ppmPixels,int maxGreyVal) {
        super(flag, width, height, ppmPixels);
        this.maxGreyVal=maxGreyVal;
    }
}
