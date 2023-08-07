import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collage implements BackUp {
    public void collage(String direction, String fileName1,String fileName2,String fileName3) throws IOException, CloneNotSupportedException {
        backUp();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        Image img1=null;
        Image img2=null;
        for (Image img : imgs) {
            if (img.getFileName().equals(fileName1)) img1=img;
            if (img.getFileName().equals(fileName2)) img2=img;
        }

        if (img1!=null && img2!=null)
        {
            Main.session.getSession().get(Main.id).add(img1.collage(direction,img1,img2,fileName3));
        }

        else throw new IllegalArgumentException("No such file found");

    }

    @Override
    public void backUp() throws CloneNotSupportedException {
        Map<Integer, List<Image>> sessionCopy = new HashMap<>();
        for (Map.Entry<Integer, List<Image>> entry : Main.session.getSession().entrySet()) {
            int key = entry.getKey();
            List<Image> value = new ArrayList<>();
            for (Image image : entry.getValue()) {
                Image imageCopy = image.clone();
                value.add(imageCopy);
            }
            sessionCopy.put(key, value);
        }

        Main.backup = new HashMap<>(sessionCopy);
    }

}
