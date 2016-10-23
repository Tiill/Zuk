package zuk;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**Поток с воспроизведением звука.
 * Ждет ответа сервера 5 секунд.
 * @param vremjaProstoja перерыв между проверками милисекунд.
 * @param vremjaOzhidanija ожидание связи с сервером.
 */
class WorkThread extends Thread implements Runnable {
    static final private String zvukFile = "/рес/Overlord2-OST.wav";
    static final public String hostForPing = "8.8.8.8";
    static final private int vremjaProstoja = 5000;
    static final private int vremjaOzhidanija = 2000;


    volatile threadstatus markerstopthread;                                     //Маркер остановки потока
    Sound music;                                                                //Переменная воспроизведения звука


    /**
     * Останавливает воспроизведение звука
     */
    public void stopMusic() {
        music.stop();
    }
 
    /**
     * Файл для музыки уже вписан в конструкторе
     */
    public WorkThread() {
        music = new Sound(WorkThread.zvukFile);
        
        markerstopthread = threadstatus.startPotok;
    }

    /**
     * Проверяет соединение с сервером.
     * Меняет картинку в зависимости от проверки.
     * Ждет vremjaOzhidanija секунд между проверками.
     */
    @Override
    public void run() {
//        Зависимости
        ImagePanel background = Gui.mainframe.getBackGround();
        StartStopButton ststbutton = Gui.mainframe.getStStButton();
        MainFrame mainframe = Gui.mainframe;
        
//        проверка маркера завершения
        if (markerstopthread == threadstatus.stopPotok) {
            return;
        }

//        проверка соединения и реакция на него
        try {
            boolean pstatusp = Zuk.pingServer(InetAddress.getByName(WorkThread.hostForPing), 53, 1000);
            if (pstatusp) {
                background.setImage(ImagePanel.estInternetKartinka);
                ststbutton.markerButton = StartStopButton.statusbutton.Nachat;
                ststbutton.setText("Начать отслеживание");
                mainframe.repaint();
                return;
            }  else {
                    background.setImage(ImagePanel.ozhidanie);
                    mainframe.repaint();
                }
        } catch (UnknownHostException ey) {
            JOptionPane.showMessageDialog(null, ey, "Внимание! Проблема с Host", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

//        цикл проверки
        while (true) {
//        проверка маркера завершения
            if (markerstopthread == threadstatus.stopPotok) {
                return;
            }
            
//        проверка соединения и реакция на него
            try {
                boolean pstatus = Zuk.pingServer(InetAddress.getByName(WorkThread.hostForPing), 53, vremjaOzhidanija);
                if (pstatus) {
                    background.setImage(ImagePanel.estInternetKartinka);
                    mainframe.repaint();
                    music.play(true);
                    return;
                } else {
                    background.setImage(ImagePanel.ozhidanie);
                    mainframe.repaint();
                    Thread.sleep(WorkThread.vremjaProstoja);
                }
            } catch (UnknownHostException ex) {
                JOptionPane.showMessageDialog(null, ex, "Внимание! Проблема с Host", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (InterruptedException ex) {
                return;
            }
        }

    }

    /**
     * Константы для управления потоком,
     * через markerstopthread.
     */
    enum threadstatus {

        startPotok, stopPotok
    }
}