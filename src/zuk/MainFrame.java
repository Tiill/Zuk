package zuk;

import update.updatebutton.UpdateButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Класс переопределен для добавления в название ип,
 * а также выполняет инициализацию граф интерфейса.
 * @author Тиилл
 */
public class MainFrame extends JFrame {
    private final StartStopButton ststbutton;
    private final ImagePanel background;

    /**
    * конструктор вызывает метод изменения имени.
    */
    public MainFrame() throws HeadlessException {
        
//        Добавление фона
        background = new ImagePanel(ImagePanel.privetKartinka); setContentPane(background);
        
//        Свойства главного окна
                doName();
                setSize(400, 400);
                setResizable(false);
                setLayout(null);
                setLocationByPlatform(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Добавление иконки главного окна
                setIconImage(new ImageIcon(this.getClass().getResource("/рес/ZukIcon.png")).getImage());
                
//        Добавление кнопки разовой проверки
                OneCheckButton button1 = new OneCheckButton();
                button1.setBounds(0, 292, 250, 40);
                button1.setFont(new Font("Serif", Font.BOLD, 15));
                button1.setFocusPainted(false);
                add(button1);

//        Добавление кнопки СтартСтоп
                ststbutton = new StartStopButton();
                ststbutton.setBounds(0, 332, 250, 40);
                ststbutton.setFont(new Font("Serif", Font.BOLD, 15));
                ststbutton.setFocusPainted(false);
                add(ststbutton);

//        Добавление кнопки справка и действия
                JButton spravka = new JButton(new ImageIcon(this.getClass().getResource("/рес/SpravkaIcon.png")));
                spravka.setBounds(354, 332, 40, 40);
                spravka.setFocusPainted(false);
                spravka.setToolTipText("Справка");
                spravka.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(Gui.mainframe, "Программа Зук проверяет наличие доступа в интернет.\n"
                                + "\"Проверить соединение\" единоразово проверяет доступ в интернет.\n"
                                + "\"Начать ожидание\" ждет пока интернет не будет подключен.\n"
                                + "Когда доступ в интернет появиться Зук известит вас музыкой.", "Справка. Версия " + Zuk.getVersion(), javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                add(spravka);
                
//        Добавление кнопки обновления
                UpdateButton buttonupdate = new UpdateButton(this);
                buttonupdate.setIcon(new ImageIcon(this.getClass().getResource("/рес/UpdateIcon.png")));
                buttonupdate.setToolTipText("Обновление");
                buttonupdate.setBounds(354, 292, 40, 40);
                buttonupdate.setFont(new Font("Serif", Font.BOLD, 15));
                buttonupdate.setFocusPainted(false);
                add(buttonupdate);

//        Делает окно видимым
                setVisible(true);
            }
    
    
    /**
    * Метод получения имени програмы + ип
    */
    private void doName(){
        try {
            setTitle("Зук:     " + InetAddress.getLocalHost().toString());
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, ex, "Внимание! Проблема с ip", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
    * Переопределен ради вызова doName()
    */
    @Override
    public void repaint() {
        doName();
        super.repaint();
    }

    public StartStopButton getStStButton() {
        return ststbutton;
    }

    public ImagePanel getBackGround() {
        return background;
    }
    
    

}