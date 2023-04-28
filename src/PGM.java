public class PGM extends Image {

    private int maxGreyVal;

    public PGM(String flag, int width, int height, int[][] pixels, int maxGreyVal) {
        super(flag, width, height, pixels);
        this.maxGreyVal=maxGreyVal;
    }
}
