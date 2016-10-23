package zuk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Кнопка для Зук фрейма меняющая поведение после нажатия. Меняется с "Начать
 * отслеживание" на "Остановить отслеживание".
 *
 * @author Тиилл
 */
public class StartStopButton extends javax.swing.JButton {

    volatile statusbutton markerButton;

    /**
     * Конструктор задает название, слушатель и маркер состояния кнопки.
     */
    public StartStopButton() {
        super();
        this.setText("Начать отслеживание");
        this.addActionListener(new actionListenerForThisButton(this));
        this.markerButton = statusbutton.Nachat;
    }

//    Внутренние сущности
//    доступные для пакета
    /**
     * Константы состояния кнопки
     */
    enum statusbutton {

        Ostanovit, Nachat
    }

    /**
     * Слушатель зависит от маркера состояния кнопки. Имеет собственную ссылку
     * на кнопку.
     */
    class actionListenerForThisButton implements ActionListener {

        private StartStopButton button;

        /**
         * Конструктор должен получить ссылку на кнопку.
         *
         * @param button ссылка на кнопку.
         */
        public actionListenerForThisButton(StartStopButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (button.markerButton == StartStopButton.statusbutton.Nachat) {
                setText("Остановить отслеживание");

                ////////////////////////////////////////////////////////////
                Gui.mainthread = new WorkThread();
                Gui.mainthread.markerstopthread = WorkThread.threadstatus.startPotok;
                Gui.mainthread.start();
                    ////////////////////////////////////////////////////////////

                button.markerButton = StartStopButton.statusbutton.Ostanovit;
            } else {
                setText("Начать отслеживание");

                ////////////////////////////////////////////////////////////
                Gui.mainthread.markerstopthread = WorkThread.threadstatus.stopPotok;
                Gui.mainthread.interrupt();
                Gui.mainthread.stopMusic();
                    ////////////////////////////////////////////////////////////

                button.markerButton = StartStopButton.statusbutton.Nachat;
            }
        }
    }
}
