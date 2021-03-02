package gui;

import javax.swing.*;
import persone.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Classe che implementa un frame in cui un utente aggiunge spedizioni normali o assicurate
 * @author Andrea Bonfatti
 */
public class FrameAggiungiSpedizioni extends MyFrame  {
    private static final long serialVersionUID = 1L;

    /**
     * lista di utenti
     */
    private ListaUtenti l;

    /**
     * utente che sta aggiungendo le spedizioni
     */
    private Utente u;

    /**
     * indica il tipo di spedizioni che sto aggiungendo
     */
    private int TipoSpedizione;

    /*componenti grafici */
    //---------------------//
    private JLabel PesoTxt, IndirizzoTxt, ValTxt, Messaggio;
    private JTextField Peso;
    private JTextField Indirizzo;
    private JTextField ValoreAssicurato;
    private JButton Aggiungi, Indietro;
    private JPanel PannelloAggiugniSpedizione, PannelloNord, PannelloCentro, PannelloSud;


    /**
     * metodo costruttore del frame per l' aggiunta di una nuova spedizione/spedizione assicurata
     * @param val indica il tipo di spedizione che ho deciso di effettuare
     * @param l lista di utenti
     * @param u utente che sta effetuando le spedizioni
     */
    public FrameAggiungiSpedizioni(int val, ListaUtenti l, Utente u) {

        super();

        this.l = l;
        this.u = u;
        TipoSpedizione = val;
        Messaggio = new JLabel("Inserire i dati della spedizione");
        PesoTxt = new JLabel("Peso");
        IndirizzoTxt = new JLabel("Indirizzo");
        ValTxt = new JLabel("Valore assicurato");
        Peso = new JTextField("", 3);
        Indirizzo = new JTextField("", 25);
        ValoreAssicurato = new JTextField ("", 5);
        Aggiungi = new JButton("Aggiungi Spedizione");
        Indietro = new JButton("Indietro");

        ButtonGroup bg = new ButtonGroup();
        bg.add(Aggiungi);
        bg.add(Indietro);


        if (TipoSpedizione  == 1) {
            ValoreAssicurato.setEditable(false);
        }

        PannelloAggiugniSpedizione = new JPanel();
        PannelloAggiugniSpedizione.setLayout(new BorderLayout());

        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloCentro.setLayout(new GridLayout(3, 2));
        PannelloSud = new JPanel();

        PannelloNord.add(Messaggio);
        PannelloCentro.add(IndirizzoTxt);
        PannelloCentro.add(Indirizzo);
        PannelloCentro.add(PesoTxt);
        PannelloCentro.add(Peso);
        PannelloCentro.add(ValTxt);
        PannelloCentro.add(ValoreAssicurato);
        PannelloSud.add(Aggiungi);
        PannelloSud.add(Indietro);

        PannelloAggiugniSpedizione.add(PannelloNord, BorderLayout.NORTH);
        PannelloAggiugniSpedizione.add(PannelloCentro, BorderLayout.CENTER);
        PannelloAggiugniSpedizione.add(PannelloSud, BorderLayout.SOUTH);

        this.add(PannelloAggiugniSpedizione);
        Aggiungi.addActionListener(this);
        Indietro.addActionListener(this);

        this.addWindowListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        String scelta = e.getActionCommand();

        /*
         * Per Aggiungere una nuova spedizione controllo:
         * 1) Il tipo di spedizione, in base al tipo controllo di aver inserito tutti i dati
         * 2) Il peso del pacco che deve essere un intero e maggiore di 0
         * 3) Se è una spedizione assicurata controllo il valore assicurato, intero e maggiore di 0
         *
         */
        if (scelta.equals("Aggiungi Spedizione")) {
            String peso = Peso.getText();
            String indirizzo = Indirizzo.getText();
            String val = ValoreAssicurato.getText();

            if (peso.equals("") || indirizzo.equals("")) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi della spedizione ", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            if (TipoSpedizione == 2) {
                if (val.equals("")) 
                    JOptionPane.showMessageDialog(this, "Inserire tutti i capi della spedizione ", "Errore", JOptionPane.ERROR_MESSAGE);	
            }

            //CONTROLLO IL PESO DEL PACCO CHE DEVE ESSERE UN INTERO E MAGGIORE DI 0
            try {
                @SuppressWarnings("unused")
                int p= Integer.parseInt(peso);
            }
            catch (java.lang.NumberFormatException e1) {
                CambiaSchermata(new FrameAggiungiSpedizioni (TipoSpedizione, l, u), this);
                JOptionPane.showMessageDialog(this, "Peso deve essere di tipo intero ", "Errore", JOptionPane.ERROR_MESSAGE);

            }
            int P = Integer.parseInt(peso);

            if (P <=0 ) {
                CambiaSchermata(new FrameAggiungiSpedizioni (TipoSpedizione, l, u), this);
                JOptionPane.showMessageDialog(this, "Peso deve essere maggiore di 0 ", "Errore", JOptionPane.ERROR_MESSAGE);

            }
            else {
                if (TipoSpedizione == 1) {
                    u.CreaSpedizione(indirizzo, P);
                    JOptionPane.showMessageDialog(this, "Spedizione aggiunta correttamente ", "", JOptionPane.INFORMATION_MESSAGE);
                    CambiaSchermata (new FrameAggiungiSpedizioni(TipoSpedizione, l, u), this);
                }
            }


            //CONTROLLO IL VALORE ASSICURATO CHE DEVE ESSERE UN INTERO E MAGGIORE DI 0
            if (TipoSpedizione == 2) {

                try {
                    @SuppressWarnings("unused")
                    int v = Integer.parseInt(val);
                }
                catch(java.lang.NumberFormatException e2) {
                    CambiaSchermata(new FrameAggiungiSpedizioni(TipoSpedizione, l, u), this);
                    JOptionPane.showMessageDialog(this, "Valore Assicurato deve essere di tipo intero", "Errore", JOptionPane.ERROR_MESSAGE);

                }

                int V = Integer.parseInt(val);
                if (V <= 0) {
                    JOptionPane.showMessageDialog(this, "Valore assicurato deve essere maggiore di 0 ", "Errore", JOptionPane.ERROR_MESSAGE);
                    CambiaSchermata (new FrameAggiungiSpedizioni(TipoSpedizione, l, u), this);

                }
                else {
                    u.CreaSpedizione(indirizzo , P, V);
                    JOptionPane.showMessageDialog(this, "Spedizione assicurata aggiunta correttamente ", "", JOptionPane.INFORMATION_MESSAGE);
                    CambiaSchermata (new FrameAggiungiSpedizioni(TipoSpedizione, l, u), this);

                }
            }
        }
        if (scelta.equals("Indietro")) {
            l.SalvaLista();
            CambiaSchermata( new FrameUtenteLoggato(l, u), this);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        l.SalvaLista();
        System.exit(0);
    }
}
