package zuk;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Проверяет соединение с интернетом если нет то проверяет бесконечно через
 * определенные промежутки времени.
 *
 * Две константы определены. Версия и имя прог.
 * 
 * @version 0.7
 * @author Тиилл
 */
public final class Zuk {

    private static final double version = 0.7;
    private static final String nameOfProgram = "zuk";

    public static double getVersion() {
        return version;
    }

    public static String getNameOfProgram() {
        return nameOfProgram;
    }

    /**
     * Основная функция.
     */
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//        ЛАФ должен быть первый или обновиться(порядок назначения вида и создания элементов имеет значение)
                try {
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    JOptionPane.showMessageDialog(Gui.mainframe, "Проблема с Look and Feel.", "Ошибка!", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
                try {
                    //                MainFrame.setDefaultLookAndFeelDecorated(true);
                    new Gui();
                } catch (HeadlessException ex) {
                    Logger.getLogger(Zuk.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Zuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Дополнительная функция проверяющий соединение с сервером.
     *
     * @param timeout время ожидания ответа (1000 = 1 сек.) Взят из интрернета.
     * @return логическое да если связь есть.
     */
    public static boolean pingServer(InetAddress addr, int port, int timeout) {
        Socket socket = new Socket();
        Exception exception = null;
        try {
            socket.connect(new InetSocketAddress(addr, port), timeout);
        } catch (IOException e) {
            exception = e;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
        return exception == null;
    }

}
