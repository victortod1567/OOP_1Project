



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Negative implements BackUp {
    public void negative() throws IOException, CloneNotSupportedException {
        backUp();
        List<Image> imgs = Main.session.getSession().get(Main.currentSession);
        for (Image img : imgs) {
            img.invert();
        }

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
