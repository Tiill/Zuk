package zuk;

import java.awt.HeadlessException;
import java.net.MalformedURLException;

/**
 *
 * @author Тиилл
 */
public class Gui {

    static MainFrame mainframe;
    static WorkThread mainthread;
    static boolean zhdat;

    public Gui() throws HeadlessException, MalformedURLException {
        mainframe = new MainFrame();
    }

}
