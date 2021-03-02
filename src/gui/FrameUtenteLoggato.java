package gui;

import persone.ListaUtenti;
import persone.Utente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa il frame di un utente loggato
 * da qui un utente puo scegliere se aggiungere spedizioni normali, assicurate o
 * visualizzare in formato tabellare le sue spedizioni
 */
public class FrameUtenteLoggato extends MyFrame {
	
	private static final long serialVersionUID = 1L;

    /**
     * lista di utenti
     */
	private ListaUtenti l;

    /**
     * utente loggato
     */
    private Utente u;

    /*componenti grafiche */
    private JLabel NomeUtente, Messaggio, ImageUtente;
    private JButton LogOut, Visualizza, NuovaSpedizione, NuovaSpedizioneSicura;
    private JPanel PannelloUtente, PannelloNord, PannelloCentro, PannelloSud;

    public FrameUtenteLoggato(ListaUtenti lista, Utente ut) {

        super();

        l = lista;
        u = ut;

        PannelloUtente = new JPanel();
        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();

        PannelloUtente.setLayout(new BorderLayout());

        Messaggio = new JLabel("Inssci nuove spedizioni o visualizza spedizioni in corso");
        Icon Image = new ImageIcon(getClass().getResource("immagineutente.jpg"));
        ImageUtente = new JLabel(Image);
        NomeUtente = new JLabel(u.getUsername());

        NuovaSpedizione = new JButton("Nuova Spedizione");
        NuovaSpedizione.addActionListener(this);
        NuovaSpedizioneSicura = new JButton("Nuova Spedizione Sicura");
        NuovaSpedizioneSicura.addActionListener(this);
        LogOut = new JButton("Logout");
        LogOut.addActionListener(this);
        Visualizza = new JButton("Visualizza spedizioni");
        Visualizza.addActionListener(this);

        PannelloNord.add(ImageUtente);
        PannelloNord.add(NomeUtente);
        PannelloNord.add(Messaggio);

        PannelloCentro.add(NuovaSpedizione);
        PannelloCentro.add(NuovaSpedizioneSicura);
        PannelloSud.add(Visualizza);
        PannelloSud.add(LogOut);

        PannelloUtente.add(PannelloNord, BorderLayout.NORTH);
        PannelloUtente.add(PannelloCentro, BorderLayout.CENTER);
        PannelloUtente.add(PannelloSud, BorderLayout.SOUTH);

        this.add(PannelloUtente);
    }


    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

        if (Scelta.equals("Nuova Spedizione")){
            CambiaSchermata(new FrameAggiungiSpedizioni(1, l, u), this);
        }

        if (Scelta.equals("Nuova Spedizione Sicura")){
            CambiaSchermata(new FrameAggiungiSpedizioni(2, l, u), this);
        }

        if (Scelta.equals("Visualizza spedizioni")){
            CambiaSchermata(new FrameTabella (u, l), this);
        }

        if (Scelta.equals("Logout")){
            l.SalvaLista();
            CambiaSchermata(new FrameIniziale(), this);
        }
    }
}

