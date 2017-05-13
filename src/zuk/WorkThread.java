package zuk;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**Поток с воспроизведением звука.
 * Ждет ответа сервера 5 секунд.
 * @param vremjaProstoja перерыв между проверками милисекунд.
 * @param vremjaOzhidanija ожидание связи с сервером.
 */
class WorkThread extends Thread implements Runnable {
    static final private String zvukFile = "/рес/Overlord2-OST.wav";
    static final public String hostForPing = "8.8.8.8";
    static final private int vremjaProstoja = 1000;
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
        
        this.setDaemon(true);
    }

    /**
     * Проверяет соединение с сервером.
     * Меняет картинку в зависимости от проверки.
     * Ждет vremjaOzhidanija секунд между проверками.
     */
    @Override
    public void run() {
//        Зависимости
        JLabel font = Gui.mainframe.getfont();
        StartStopButton ststbutton = Gui.mainframe.getStStButton();
        MainFrame mainframe = Gui.mainframe;
        
//        проверка маркера завершения
        if (markerstopthread == threadstatus.stopPotok) {
            return;
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
                    if (Gui.zhdat == true) {
                        if (!music.isPlaying()){
                            music.play(true);
                        }
                    }
                    font.setIcon(new ImageIcon(getClass().getResource("/рес/ZukDa.gif")));
                    mainframe.repaint();
                    Thread.sleep(WorkThread.vremjaProstoja);
                } else {
                    if(music.isPlaying())music.stop();
                    if(Gui.zhdat == true) font.setIcon(new ImageIcon(getClass().getResource("/рес/ZukWait.gif")));
                    else font.setIcon(new ImageIcon(getClass().getResource("/рес/ZukNet.gif")));
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