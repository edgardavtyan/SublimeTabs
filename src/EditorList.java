import com.intellij.openapi.vfs.VirtualFile;

import java.util.HashMap;

public class EditorList {
    private HashMap<VirtualFile, CustomFile> map;

    public EditorList() {
        map = new HashMap<>();
    }


}
