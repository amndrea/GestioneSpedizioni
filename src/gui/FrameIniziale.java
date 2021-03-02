package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import persone.*;
import spedizioni.ListaSpedizioni;
import spedizioni.Spedizione;

import javax.swing.*;

/**
 * Classe che implementa un frame iniziale, in cui scelgo se fare il login come utente,
 * come admin o registrarmi come nuovo utente
 */
public class FrameIniziale extends MyFrame{

    private static final long serialVersionUID = 100L;

    /**
     * lista di utenti
     */
    private ListaUtenti l;

    /*componenti grafici */
    //----------------------//
    private JButton Admin, Utregistrati, UtAccedi;
    private JLabel TestoIniziale, ImmageLabel, Presentazione;
    private JPanel PannelloIniziale, PannelloNord, PannelloCentro, PannelloSud;

    /**
     * metodo costruttode del frame iniziale dove creo la lista di utenti, leggendola da file
     */
    public FrameIniziale() {

        super();

        l = new ListaUtenti();
        l.CaricaLista();

        /*
        for (int i=0; i<l.getNumUtenti(); i++){
            Utente tmp = l.get(i);
            System.out.println("Utente numero "+i);
            ListaSpedizioni tmp2 = tmp.getSpedizioni();
            for (int j=0; j< tmp.getNumSpedizioni(); j++){
                System.out.println("spedizione numero "+j);
                System.out.println(tmp2.get(j).toString());
            }
        }

         */

        PannelloIniziale = new JPanel();
        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();


        PannelloIniziale.setLayout(new BorderLayout());

        Presentazione = new JLabel ("Progetto Spedizioni anno 2020/2021");
        TestoIniziale = new JLabel("Entrare come admin, registrasi come nuovo utente o accedere ");

        Icon Image = new ImageIcon(getClass().getResource("furgone.jpg"));
        ImmageLabel = new JLabel(Image );

        Admin = new JButton ("Admin");
        Admin.addActionListener(this);
        UtAccedi = new JButton("Accesso");
        UtAccedi.addActionListener(this);
        Utregistrati = new JButton("Registazione");
        Utregistrati.addActionListener(this);


        PannelloNord.add(ImmageLabel);
        PannelloNord.add(Presentazione);

        PannelloCentro.add(TestoIniziale);

        PannelloSud.add(Admin);
        PannelloSud.add(UtAccedi);
        PannelloSud.add(Utregistrati);

        PannelloIniziale.add(PannelloNord, BorderLayout.NORTH);
        PannelloIniziale.add(PannelloCentro, BorderLayout.CENTER);
        PannelloIniziale.add(PannelloSud, BorderLayout.SOUTH);
        this.add(PannelloIniziale);
    }


    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

       if (Scelta.equals("Accesso")){
           CambiaSchermata(new FrameLogin(l), this);
       }

       if (Scelta.equals("Registazione")){
           CambiaSchermata(new FrameRegistrazione(l), this);
       }

        if (Scelta.equals("Admin")){
            CambiaSchermata(new FrameLogin(l, true), this);
        }
    }

}