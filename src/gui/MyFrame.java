package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * Classe che implementa un JFrame modificato, che implementa sia un ActionListener che un
 * WindowListener, ogni classe del pakage gui estende questa classe
 */
public class MyFrame extends JFrame implements ActionListener, WindowListener {

    /**
     * metodo costruttore, alla chiusura del frame termina il programma
     */
    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private static final long serialVersionUID = 1L;


    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * metodo che uso per passare da un Frame ad un' altro
     * rendo visibile il primo frame e chiudo il secondo
     * @param f1 frame da rendere visibile
     * @param f2 frame da chiudere
     */
    public void CambiaSchermata(MyFrame f1, MyFrame f2) {
        f2.setVisible(false);
        f2.dispose();

        f1.setVisible(true);
        f1.pack();
        f1.setLocationRelativeTo(null);


    }

    @Override
    public void windowOpened(WindowEvent e) {}

    /*questo e l' unico che devo implementare nei frame dove voglio terminare l'app e salvare */
    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}