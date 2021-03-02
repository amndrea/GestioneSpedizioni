package gui;
import persone.*;
import spedizioni.*;
import tabella.RenderCelle;
import tabella.SpedizioniTableModel;
import thread.ThreadSpedizioni;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;


/**
 * Classe che implementa il frame in cui visualizzo in formato tabellare le spedizioni
 */
public  class FrameTabella extends MyFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * lista di utenti
     */
    private ListaUtenti listautenti;

    /**
     * lista di spedizioni
     */
    private ListaSpedizioni listaspedizioni;

    /**
     * utente di cui sto visualizzando le spedizioni
     */
    private Utente utente;

    /**
     * variabile che dice se sono l'admin o sono un utente
     */
    private boolean admin;

    /**
     * variabile che uso per creare Thread che modificano le spedizioni
     */
    private boolean modifiche;

    //-------------------------//
    /* componenti grafiche*/
    private JLabel ImmagineUtente;
    private JLabel MessaggioUtente;
    private JLabel NomeUtente;
    private JLabel MessaggioAdmin;

    private JButton Indietro;
    private JButton CancellaFinale;
    private JButton StartModifiche;
    private JButton Rimborso;

    private JPanel PannelloNord, PannelloCentro, PannelloSud;
    private JPanel PannelloTabella;

    private JTable t;
    private SpedizioniTableModel tablemodel;


    /**
     * Metodo costruttore che uso per costruire una tabella e inizializzare i vari componenti
     * In base a quale tabella costruisco (amministratore o utente) aggiungo solo i componenti
     * che mi servono
     */
    public FrameTabella(){
        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();
        PannelloTabella = new JPanel();
        PannelloTabella.setLayout(new BorderLayout());

        Icon Image = new ImageIcon(getClass().getResource("immagineutente.jpg"));
        ImmagineUtente = new JLabel(Image);
        NomeUtente = new JLabel(" ");
        MessaggioUtente = new JLabel("se una spedizione assicurata fallisce puoi richiedere il rimborso con il pulsante rimborso");

        MessaggioAdmin = new JLabel("Premi Modifica per modificare le spedizioni degli utenti " +"\n"+
                "premi Rimuovi per canellare le spedizioni in stato finale ");


        Indietro = new JButton("Indietro");
        Indietro.addActionListener(this);
        Rimborso = new JButton("Rimborso");
        Rimborso.addActionListener(this);

        CancellaFinale = new JButton("Rimuovi");
        CancellaFinale.addActionListener(this);
        StartModifiche = new JButton ("Modifica");
        StartModifiche.addActionListener(this);

        setModifiche(false);

        PannelloTabella.add(PannelloNord, BorderLayout.NORTH);
        PannelloTabella.add(PannelloCentro, BorderLayout.CENTER);
        PannelloTabella.add(PannelloSud, BorderLayout.SOUTH);
    }

    /**
     * metodo costruttore del frame tabella per un utente
     * @param u utente loggato che sta visualizzando le sue spedizioni
     * @param l lista di utenti a cui appartiene l' utente u
     */
    public FrameTabella (Utente u, ListaUtenti l){
        this();
        utente = u;
        listautenti = l;
        setAdmin(false);

        PannelloNord.add(ImmagineUtente);
        PannelloNord.add(NomeUtente);
        PannelloNord.add(MessaggioUtente);

        tablemodel = new SpedizioniTableModel(utente.getSpedizioni());
        t = new JTable(tablemodel);
        t.setDefaultRenderer(Object.class, new RenderCelle());
        t.setPreferredScrollableViewportSize(t.getPreferredSize());
        JScrollPane scrollpane = new JScrollPane(t);
        PannelloCentro.add(scrollpane);

        PannelloSud.add(Indietro);
        PannelloSud.add(Rimborso);

        this.add(PannelloTabella);
    }


    /**
     * metodo costruttore del frame tabella per l'amministratore
     * @param l lista degli utenti registati
     */
    public FrameTabella(ListaUtenti l){
        this();
        listautenti = l;
        setAdmin(true);

        PannelloNord.add(MessaggioAdmin);

        //creo una lista di spedizioni non di un solo utente ma di tutti gli utenti presenti nella lista di utenti
        listaspedizioni = new ListaSpedizioni();
        for (int i=0; i<l.getNumUtenti(); i++){
            Utente u = l.get(i);
            ListaSpedizioni tmp = u.getSpedizioni();
            for (int j=0; j<tmp.gtNumSpedizioni(); j++)
                listaspedizioni.Aggiungi(tmp.get(j));
        }

        tablemodel = new SpedizioniTableModel(listaspedizioni);
        t = new JTable(tablemodel);
        t.setDefaultRenderer(Object.class, new RenderCelle());
        t.setPreferredScrollableViewportSize(t.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(t);
        PannelloCentro.add(scrollPane);

        PannelloSud.add(Indietro);
        PannelloSud.add(CancellaFinale);
        PannelloSud.add(StartModifiche);

        this.add(PannelloTabella);
    }

    /**
     * metodo che uso per verificare se sono amministratore o utente
     * @return true se sono amministratore, false se sono utente
     */
    public boolean isAdmin() { return admin; }


    /**
     * metodo che uso nel costruttore dei frame per decidere se sono l'amministratore o
     * se sono un utente
     * @param admin booleano che decide se sono admi o utente
     */
    public void setAdmin ( boolean admin){ this.admin = admin; }

    /**
     * metodo che uso per decidere se creare o non create Thread che modificano le spedizioni
     * @return true se voglio modificare le spedizioni, false altrimenti
     */
    public boolean isModifiche() { return modifiche; }

    /**
     * metodo per settare/resettare la variabile che crea/arresta la crazione di Thread per
     * le modifiche delle spedizioni
     * @param modifiche stato della richiesta di modifiche /stop modifiche
     */
    public void setModifiche(boolean modifiche) { this.modifiche = modifiche;}


    @Override
    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

        if (Scelta.equals("Indietro")){

            if (isAdmin()){
                listautenti.SalvaLista();
                CambiaSchermata(new FrameIniziale(), this);

            }
            else {
                listautenti.SalvaLista();
                CambiaSchermata(new FrameUtenteLoggato(listautenti, utente), this);
            }
        }

        /*
        seleziono una spedizione, se posso richiedere il rimborso modifico il suo stato,
        altimenti segnalo un messaggio di errore e non faccio niente
         */
        if (Scelta.equals("Rimborso")){
            ListaSpedizioni sped = utente.getSpedizioni();
            int numrimborsi = 0;

            for (int i=0; i<t.getRowCount(); i++){
                Spedizione s = sped.get(i);
                if (s.PossibileRimborso()) {
                    t.setValueAt("RICHIESTA RIMBORSO", i, 5);
                    numrimborsi++;
                }
            }
            if (numrimborsi == 0)
                JOptionPane.showMessageDialog(this, "Nessun rimborso richiesto ", "", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "richiesti "+numrimborsi+" rimborsi", "", JOptionPane.INFORMATION_MESSAGE);
        }

        /*
          seleziono una spedizione da rimuovere, se è in uno stato finale la cancello, alrimenti
          segnalo un errore e non faccio niente
         */
        if (Scelta.equals("Rimuovi")){
            ListaSpedizioni sped = tablemodel.getLista();
            Spedizione s = sped.get(t.getSelectedRow());

            if (s.StatoFinale()){

                String nome = s.getNomeUtente();
                Utente u = listautenti.TrovaUtente(nome);
                ListaSpedizioni ListaSpedUtente = u.getSpedizioni();

                ListaSpedUtente.Cancella(s);
                u.dec();
                sped.Cancella(sped.get(t.getSelectedRow()));
                tablemodel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Spedizione eliminata ","", JOptionPane.INFORMATION_MESSAGE);

            }
            else
                JOptionPane.showMessageDialog(this, "Spedizione non in stato finale", "", JOptionPane.ERROR_MESSAGE);
            //listautenti.SalvaLista();
        }
        
        /*
         se scelgo di modificare le spedizioni, creo e faccio partire i Thread delle modifiche
         fino a che non premo nuovamente il pulsante modifica
         */
        if (Scelta.equals("Modifica")) {

            setModifiche(!isModifiche());
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Thread t = new Thread();

                    while (isModifiche()) {
                        t = new ThreadSpedizioni(tablemodel);
                        t.start();

                        try {Thread.sleep(4000); } catch (InterruptedException e) {e.printStackTrace(); }
                        t.interrupt();
                    }
                }
            };
            new Thread(r).start();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        listautenti.SalvaLista();
        System.exit(0);
    }
}