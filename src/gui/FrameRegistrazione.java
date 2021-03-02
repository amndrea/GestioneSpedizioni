package gui;

import persone.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa un frame per la registrazione di un nuovo utente
 */
public class FrameRegistrazione extends MyFrame {

	private static final long serialVersionUID = 1L;

    /**
     *lista di utenti
     */
	private ListaUtenti l;

	//---------------------//
	/*componenti grafiche */
    private JLabel Messaggio;
    private JLabel UsernameTxt, PasswordTxt, ConfermaTxt, IndirizzoTxt;
    private JTextField Username, Indirizzo;
    private JPasswordField Password, Conferma;
    private JButton Accedi, Indietro;
    private JPanel PannelloRegistrazione, PannelloNord, PannelloCentro, PannelloSud;

    /**
     * Username per accedere come amministratore
     */
    @SuppressWarnings("unused")
	private static final String UserAdmin = "Admin";
    
    /**
     * Password per accedere come amministratore 
     */
    @SuppressWarnings("unused")
	private static final String PasswordAdmin = "pogill";
    public FrameRegistrazione(ListaUtenti listautenti){

        super();
        this.l = listautenti;

        PannelloRegistrazione = new JPanel();
        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();

        PannelloRegistrazione.setLayout(new BorderLayout());
        Messaggio = new JLabel("Inserire i campi ");
        IndirizzoTxt = new JLabel("Indirizzo");
        UsernameTxt = new JLabel ("Username ");
        PasswordTxt = new JLabel( "Password");
        ConfermaTxt = new JLabel ("Conferma password");

        Username = new JTextField("", 20);
        Indirizzo = new JTextField("", 20);
        Password = new JPasswordField("", 20);
        Conferma = new JPasswordField("", 20);


        Indietro = new JButton("Indietro");
        Indietro.addActionListener(this);
        Accedi = new JButton ("Accedi");
        Accedi.addActionListener(this);

        PannelloNord.add(Messaggio);

        PannelloCentro.setLayout(new GridLayout(5, 2));

        PannelloCentro.add(UsernameTxt);
        PannelloCentro.add(Username);
        PannelloCentro.add(PasswordTxt);
        PannelloCentro.add(Password);
        PannelloCentro.add(ConfermaTxt);
        PannelloCentro.add(Conferma);
        PannelloCentro.add(IndirizzoTxt);
        PannelloCentro.add(Indirizzo);

        PannelloSud.add(Indietro);
        PannelloSud.add(Accedi);

        PannelloRegistrazione.add(PannelloNord, BorderLayout.NORTH);
        PannelloRegistrazione.add(PannelloCentro, BorderLayout.CENTER);
        PannelloRegistrazione.add(PannelloSud, BorderLayout.SOUTH);

        this.add(PannelloRegistrazione);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

        if (Scelta.equals("Indietro")){
            CambiaSchermata(new FrameIniziale(), this);
        }

        if (Scelta.equals("Accedi")){
            String user = "", password = "", conferma = "", indirizzo = "";
            char[] c;
            char[] p;
            user = Username.getText();
            indirizzo = Indirizzo.getText();
            p = Password.getPassword();
            c = Conferma.getPassword();

            for (int i = 0; i < p.length; i++)
                password = password + p[i];
            for (int i = 0; i < c.length; i++)
                conferma = conferma + c[i];

            /* controllo che tutti i campi siano inseriti */
            if (user.equals("") || password.equals("") || conferma.equals("") || indirizzo.equals("")) {
                CambiaSchermata(new FrameRegistrazione(l), this);
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi della registrazione ", "Errore", JOptionPane.ERROR_MESSAGE);
            } else{
                /*controllo che l' username inserito non sia quello dell' admin */
                if (user.equals("Admin")) {
                    CambiaSchermata(new FrameRegistrazione(l), this);
                    JOptionPane.showMessageDialog(this, "Inserire un username diverso da Admin", "Errore", JOptionPane.ERROR_MESSAGE);
                } else{
                    /*controllo che entrambe le password inserite corrispondano */
                    if (!password.equals(conferma)) {
                        CambiaSchermata( new FrameRegistrazione(l), this);
                        JOptionPane.showMessageDialog(this, "Conferma password diversa da password ", "Errore", JOptionPane.ERROR_MESSAGE);

                    } else{
                        Utente nuovoutente = new Utente(user, password, indirizzo);
                        /*controllo che l' utente inserito non sia gia presente nella lista */
                        if (l.GiaPresente(nuovoutente)) {
                            CambiaSchermata( new FrameRegistrazione(l), this);
                            JOptionPane.showMessageDialog(this, "Username gia in uso ", "Errore", JOptionPane.ERROR_MESSAGE);

                        } else {
                            l.Aggiungi(nuovoutente); //aggiungo l'utente
                            l.AggiungiUnNuovoUtente(nuovoutente); //salvo la lista con append
                            CambiaSchermata(new FrameUtenteLoggato(l, nuovoutente), this);
                        }
                    }
                }
            }

        }
    }
}