package update.updatebutton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Класс кнопки обновления.
 * Для использования нужно заменить статические константы и передать
 * главное окно в конструктор.
 * @author Тиилл
 */
public class UpdateButton extends JButton {
    static final String nameprogramm = "zuk";
    static final double version = 1.7;
    static final String serverip = "192.168.1.139";
    static final int serverport = 732;
    


    public UpdateButton(JFrame mainFrame) {
        setIcon(new ImageIcon(this.getClass().getResource("/рес/UpdateIcon.png")));
        setToolTipText("Обновление");
        setEnabled(false);
        addActionListener(new UpdateButtonClickListener(mainFrame));
        CheckUpdateThread ucthread = new CheckUpdateThread(this, mainFrame);
        ucthread.start();
    }

    public static double getVersion() {
        return version;
    }
}

class UpdateButtonClickListener implements ActionListener{
    private final JFrame mainframe;

    public UpdateButtonClickListener(JFrame mainframe) {
        this.mainframe = mainframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new DownloadDialog(mainframe);
    }
}    