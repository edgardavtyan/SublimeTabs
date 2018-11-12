import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;

public class SaveAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Main main = ApplicationManager.getApplication().getComponent(Main.class);
        main.onSaveActionPreformed();
    }
}
