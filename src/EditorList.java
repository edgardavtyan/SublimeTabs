import com.intellij.openapi.vfs.VirtualFile;

import java.util.HashMap;

public class EditorList {
    private HashMap<VirtualFile, CustomEditor> map;

    public EditorList() {
        map = new HashMap<>();
    }


}
