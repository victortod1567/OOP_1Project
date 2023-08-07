

public class Help {
    public void help() {
        System.out.println("Available commands:" +
                "\nload             Loads a new session" +
                "\nopen             Opens a file. If such doesn't exist an empty one is created" +
                "\nclose            Closes a file" +
                "\nsave             Saves a file" +
                "\nsaveAs           Saves a file as. Syntax: saveAs <filename or path>" +
                "\nchangeSession    Changes the current user session" +
                "\nundo             Undo the last change made" +
                "\nnegative         Creates a negative of the image" +
                "\ngrayscale        Make a colored image grayscale" +
                "\nmonochrome       Make an image black and white" +
                "\nrotate           Rotate an image 90 degrees. Syntax: rotate <direction>. Direction: left, right" +
                "\ncollage          Syntax: collage <direction> <image1> <image2> <outimage>. Makes a collage of two images either horizontally or vertically" +
                "\nhelp             Lists available commands" +
                "\nexit             Exits the program");
    }
}
