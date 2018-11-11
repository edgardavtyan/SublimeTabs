import com.intellij.openapi.fileEditor.FileEditor;

class CustomEditor {
    private final FileEditor editor;

    private boolean keepOpen;

    public boolean isKeepOpen() {
        return keepOpen;
    }

    public void setKeepOpen(boolean keepOpen) {
        this.keepOpen = keepOpen;
    }

    CustomEditor(FileEditor editor) {
        this.editor = editor;
    }
}
