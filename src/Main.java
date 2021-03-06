import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Main implements FileEditorManagerListener, ApplicationComponent {
    private MessageBusConnection connection;
    private FileEditor savedEditor;
    private VirtualFile savedFile;

    private HashMap<VirtualFile, CustomFile> filesMap;

    public Main() {
        connection = ApplicationManager.getApplication().getMessageBus().connect();
        filesMap = new HashMap<>();
    }

    public void onSaveActionPreformed() {
        filesMap.get(savedFile).setKeepOpen(true);
    }

    @Override
    public void initComponent() {
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, this);
    }

    @Override
    public void disposeComponent() {
        connection.disconnect();
    }

    @Override
    public void fileOpenedSync(
            @NotNull FileEditorManager source,
            @NotNull VirtualFile file,
            @NotNull Pair<FileEditor[],
            FileEditorProvider[]> editors) {
        if (savedEditor == null || savedFile == null) {
            savedEditor = source.getSelectedEditor(file);
            savedFile = file;
            return;
        }

        if (savedEditor.isModified()) {
            if (filesMap.containsKey(savedFile)) {
                filesMap.get(savedFile).setKeepOpen(true);
            }
        }

        for (CustomFile customFile : filesMap.values()) {
            if (!customFile.isKeepOpen()) {
                source.closeFile(customFile.getFile());
            }
        }

        filesMap.put(file, new CustomFile(file));
        savedEditor = source.getSelectedEditor(file);
        savedFile = file;
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        filesMap.remove(file);
    }
}
