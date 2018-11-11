import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.vfs.VirtualFile;

class CustomFile {
    private final VirtualFile file;

    private boolean keepOpen;

    CustomFile(VirtualFile file) {
        this.file = file;
    }

    public boolean isKeepOpen() {
        return keepOpen;
    }

    public void setKeepOpen(boolean keepOpen) {
        this.keepOpen = keepOpen;
    }

    public VirtualFile getFile() {
        return file;
    }
}
