import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;

public class SaveAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        BlahBlah blah = ApplicationManager.getApplication().getComponent(BlahBlah.class);
        blah.onSaveActionPreformed();
    }
}
