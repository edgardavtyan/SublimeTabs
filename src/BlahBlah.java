import com.intellij.diff.tools.util.KeyboardModifierListener;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.KeyboardGestureAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.WeakPropertyChangeAdapter;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlahBlah implements FileEditorManagerListener, ApplicationComponent, TypedActionHandler {
    private MessageBusConnection c;
    private FileEditor prevEditor;
    private VirtualFile prevFile;

    private HashMap<VirtualFile, CustomEditor> editorsFiles;

    public BlahBlah() {
        c = ApplicationManager.getApplication().getMessageBus().connect();
        editorsFiles = new HashMap<>();
    }

    public void onSaveActionPreformed() {
        editorsFiles.get(prevFile).setKeepOpen(true);
    }

    @Override
    public void initComponent() {
        c.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, this);
    }

    @Override
    public void disposeComponent() {
        c.disconnect();
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        if (prevEditor == null || prevFile == null) {
            prevEditor = source.getSelectedEditor();
            prevFile = file;
            return;
        }

        if (prevEditor.isModified()) {
            editorsFiles.get(prevFile).setKeepOpen(true);
        }

        for (CustomEditor value : editorsFiles.values()) {
            if (!value.isKeepOpen()) {
                source.closeFile(value.getFile());
            }
        }

        editorsFiles.put(file, new CustomEditor(file));
        prevEditor = source.getSelectedEditor();
        prevFile = file;
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        editorsFiles.remove(file);
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {

    }
}
