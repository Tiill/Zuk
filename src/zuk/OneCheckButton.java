package zuk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 * Класс для кнопки одиночной проверки.
 *
 * @author Тиилл
 */
public class OneCheckButton extends javax.swing.JButton {

    /**
     * Конструктор задает название и слушатель.
     */
    public OneCheckButton() {
        setText("Проверить соединение");
        addActionListener(new actionListenerForOneCheckButton());

    }

    /**
     * Слушатель выполняет разовую проверку соединения, Меняет фон.
     */
    class actionListenerForOneCheckButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

//        проверка соединения и реакция на него
            try {
                boolean pstatusp = Zuk.pingServer(InetAddress.getByName(WorkThread.hostForPing), 53, 1000);
                if (pstatusp) {
                    Gui.mainframe.getBackGround().setImage(ImagePanel.estInternetKartinka);
                    Gui.mainframe.repaint();
                } else {
                    Gui.mainframe.getBackGround().setImage(ImagePanel.netInternetKartinka);
                    Gui.mainframe.repaint();
                }
            } catch (UnknownHostException ey) {
                JOptionPane.showMessageDialog(null, ey, "Внимание! Проблема с Host", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
