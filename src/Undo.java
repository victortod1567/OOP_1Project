


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Undo {
    public void undo() throws CloneNotSupportedException {
        Map<Integer, List<Image>> backupCopy = new HashMap<>();
        for (Map.Entry<Integer, List<Image>> entry : Main.backup.entrySet()) {
            int key = entry.getKey();
            List<Image> value = new ArrayList<>();
            for (Image image : entry.getValue()) {
                Image imageCopy = image.clone();
                value.add(imageCopy);
            }
            backupCopy.put(key, value);
        }

        Main.session.setSession(backupCopy);

    }
}
