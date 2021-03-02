package gui;

import persone.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe che implementa un frame per il login di un utente
 * @author Andrea Bonfatti
 */
public class FrameLogin extends  MyFrame{


	private static final long serialVersionUID = 1L;
    /**
     * nome dell' amministratore
     */
	private static final String IdAdmin = "Admin";

    /**
     * password dell'amministratore
     */
    private static final String PasswordAdmin = "pogill";

    /**
     * lista di utenti
     */
    private ListaUtenti l;

    /* componenti grafiche */
    private JLabel Messaggio;
    private JLabel UsernameTxt, PasswordTxt;
    private JButton Accedi, Indietro;
    private JTextField Username;
    private JPasswordField Password;
    private JPanel PannelloLogin, PannelloNord, PannelloCentro, PannelloSud;
    /**
     * metodo che verifica se sto facendo il login come admin o come utente
     * @return true se sono admin, false altrimenti
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * metodo che setta la variabile admin per controllare chi fa il login
     * @param admin vero se sono l' admin, false alrimenti
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * variabile che uso per controllare se a fare il login è l'admin o un utente
     */
    private boolean admin;

    /**
     * metodo costruttore del frame per il login dell' utente
     * @param lista lista di utenti
     */
    public FrameLogin(ListaUtenti lista){
        super();

        l = lista;
        this.admin = false;
        PannelloLogin = new JPanel();
        PannelloNord = new JPanel();
        PannelloCentro = new JPanel();
        PannelloSud = new JPanel();

        PannelloLogin.setLayout(new BorderLayout());
        Messaggio = new JLabel("Inserire i campi ");
        UsernameTxt = new JLabel ("Username ");
        PasswordTxt = new JLabel( "Password");
        Username = new JTextField("", 20);
        Password = new JPasswordField("", 20);

        Indietro = new JButton("Indietro");
        Indietro.addActionListener(this);
        Accedi = new JButton ("Accedi");
        Accedi.addActionListener(this);

        PannelloNord.add(Messaggio);

        PannelloCentro.setLayout(new GridLayout(2, 2));
        PannelloCentro.add(UsernameTxt);
        PannelloCentro.add(Username);
        PannelloCentro.add(PasswordTxt);
        PannelloCentro.add(Password);

        PannelloSud.add(Indietro);
        PannelloSud.add(Accedi);

        PannelloLogin.add(PannelloNord, BorderLayout.NORTH);
        PannelloLogin.add(PannelloCentro, BorderLayout.CENTER);
        PannelloLogin.add(PannelloSud, BorderLayout.SOUTH);
        this.add(PannelloLogin);
    }

    /**
     * metodo che uso per costruire il frame del login per l'admin
     * @param l lista di utenti
     * @param admin indicatore che sono l'admin
     */
    public FrameLogin(ListaUtenti l, boolean admin){
        this(l);
        setAdmin(admin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Scelta = e.getActionCommand();

        if (Scelta.equals("Indietro")) {
            CambiaSchermata(new FrameIniziale(), this);
        }

        if (Scelta.equals("Accedi")) {
            String user = "", password = "";
            char[] p;
            user = Username.getText();
            p = Password.getPassword();
            for (int i = 0; i < p.length; i++)
                password = password + p[i];

            /*controllo di aver inserito tutti i dati per il login */
            if (user.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi della registrazione ", "Errore", JOptionPane.ERROR_MESSAGE);
                CambiaSchermata(new FrameLogin(l), this);
               
            }

            /* se sono l' admin controllo di aver inseito username e password corrette*/
            if (isAdmin()){
                if (user.equals(IdAdmin) && password.equals(PasswordAdmin)){
                   CambiaSchermata(new FrameTabella(l), this);
                }
                else{
                	JOptionPane.showMessageDialog(this, "Inserire user e password admin corrette", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }

            /*
             * altrimenti controllo
             * 1) di non aver inseito l' id dell' admin
             * 2) di aver inserito un id di un utente presente
             * 3) che la password corrisponda
             */
            else{
                if (user.equals(IdAdmin)){
                    JOptionPane.showMessageDialog(this, "Inserire username diverso da Admin ", "Errore", JOptionPane.ERROR_MESSAGE);
                    CambiaSchermata(new FrameLogin(l), this);
                }
                else{
                    Utente esistente = new Utente();
                    for (int i=0; i<l.getNumUtenti(); i++){
                        Utente tmp = l.get(i);
                        if (tmp.getUsername().equals(user))
                            esistente = l.get(i);

                    }
                    if (esistente.getUsername().equals("")){
                        JOptionPane.showMessageDialog(this, "Utente Inesistente ", "Errore", JOptionPane.ERROR_MESSAGE);
                        CambiaSchermata(new FrameLogin(l), this);
                    }
                    //se arrivo qui l' utente esiste allora controllo la password
                    else{
                        if (esistente.getPassword(). equals(password)){
                            @SuppressWarnings("unused")
							Utente tmp;
                            for (int i=0; i<l.getNumUtenti(); i++){
                                tmp = l.get(i);
                            }
                            CambiaSchermata(new FrameUtenteLoggato(l, esistente), this);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Password errata ", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

}