package zuk;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Класс изменяюзщегося фона.
 * Адрес картинки фона хранятся как public поле.
 * @author Тиилл
 */
public class ImagePanel extends javax.swing.JPanel {
    static final public String privetKartinka = "/рес/ZukOsnov.jpg";
    static final public String estInternetKartinka = "/рес/ZukDa.jpg";
    static final public String netInternetKartinka = "/рес/ZukNet.jpg";
    static final public String ozhidanie = "/рес/ZukOzhidan.jpg";
    
    
    private Image image;

      /**
     * Конструктор - задает начальную картинку фон.
     * @param imgfile адресс картинки внутри класса.
     */
    ImagePanel(String imgfile) {
        image = new ImageIcon(getClass().getResource(imgfile)).getImage();
    }
    
    /**
    * Изменяет картинку.
    */
    void setImage(String imgfile) {
        image = new ImageIcon(getClass().getResource(imgfile)).getImage();
    }

    /**
    * Добавлена отрисовка картинки
    */
    @Override
    public void paintComponent(Graphics g) {
//        private File imgfile;
//                    Image im = null;
//        try {
//            im = ImageIO.read(imgfile);
//        } catch (IOException e) {JOptionPane.showMessageDialog(null, e, "Внимание! Проблема с файлом фона!", javax.swing.JOptionPane.ERROR_MESSAGE);}
//        g.drawImage(im, 0, 0, null);
        
        
//            Image bImage = new ImageIcon(getClass().getResource(imgfile)).getImage();
            g.drawImage(image, 0, 0, null);
    }
    
}
