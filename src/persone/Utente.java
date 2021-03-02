package persone;
import spedizioni.*;

/**
 * Classe che implementa un utente
 * @author Andrea Bonfatti
 */
public class Utente {

	/**
	 * username dell' utente
	 */
	private String Username;

	/**
	 * Password dell' utente
	 */
	private String Password;

	/**
	 * indirizzo dell' utente
	 */
	private String Indirizzo;

	/**
	 * lista di spedizioni dell' utente
	 */
	private ListaSpedizioni Spedizioni;

	/**
	 * numero di spedizioni effettuate
	 */
	private int numSpedizioniEffettuate;

	/**
	 * metodo costruttore di un utente
	 * @param Username username dell' utente
	 * @param Password password dell' utente
	 * @param Indirizzo indirizzo dell' utente
	 */
	public Utente(String Username, String Password, String Indirizzo) {
		this.Username = Username;
		this.Password = Password;
		this.Indirizzo = Indirizzo;
		this.Spedizioni = new ListaSpedizioni();
		this.numSpedizioniEffettuate = 0;
	}

	/**
	 * metodo costruttore che crea un utente senza dati
	 */
	public Utente() {
		this("", "", "");
	}

	/**
	 * metodo che ritorna l' username dell' utente
	 * @return username dell' utente
	 */
	public String getUsername() {
		return Username;
	}

	/**
	 * metodo che ritorna la password dell' utente
	 * @return password dell' utente
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * metodo che ritorna l' indirizzo dell' utente
	 * @return indirizzo dell' utente
	 */
	public String getIndirizzo() {
		return Indirizzo;
	}

	/**
	 * metodo che ritorna il numero di spedizioni effettuate dall' utente
	 * @return numero di spedizioni effettuate dall' utente
	 */
	public int getNumSpedizioni() {
		return numSpedizioniEffettuate;
	}

	/**
	 * metodo che ritorna la la lista di spedizioni dell' utente
	 * @return lista di spedizioni dell' utente
	 */
	public ListaSpedizioni getSpedizioni() {
		return Spedizioni;
	}

	/**
	 * metodo che setta la lista di spedizioni dell' utente
	 * @param s nuova lista di spedizioni dell' utente
	 */
	public void setSpedizioni(ListaSpedizioni s){ this.Spedizioni = s; }

	/**
	 * metodo che incrementa il numero di spedizioni effettuate
	 */
	public void inc() {
		numSpedizioniEffettuate += 1;
	}

	/**
	 * metodo che decrementa il numero di spedizioni effettuate
	 */
	public void dec(){
		if (numSpedizioniEffettuate > 0)
			numSpedizioniEffettuate -= 1;

	}

	/**
	 * metodo che aggiunge la spedizione s alla lista di spedizioni dell' utente,
	 * la uso quando carico le spedizioni leggendo da file
	 * @param s spedizione da aggiungere alla lista delle spedizioni
	 */
	public void AggiungiSpedizione(Spedizione s) {
		Spedizioni.Aggiungi(s);
		inc();
	}

	/**
	 * metodo che crea e aggiunge una spedizione alla lista spedizioi dell' utente
	 * @param Destinazione destinazione della spedizione
	 * @param Peso peso della spedizione
	 */
	public void CreaSpedizione(String Destinazione, int Peso) {
		Spedizione s = new Spedizione(Destinazione, Peso);

		String codice = getUsername() + numSpedizioniEffettuate + s.getDestinazione().charAt(0) + s.getPeso();
		codice = codice.toUpperCase();
		s.setCodice(codice);
		s.setNomeUtente(this.Username);
		inc();

		Spedizioni.Aggiungi(s);
	}

	/**
	 * metodo che crea e aggiunge una spedizione assicurata alla lista di spedizioni dell' utente
	 * @param Destinazione destinazione della spedizione
	 * @param Peso peso della spedizione
	 * @param val valore assicurato della spedizione
	 */
	public void CreaSpedizione(String Destinazione, int Peso, int val) {
		SpedizioneSicura s = new SpedizioneSicura(Destinazione, Peso, val);

		String codice = getUsername() + numSpedizioniEffettuate + s.getDestinazione().charAt(0) + s.getPeso();
		codice = codice.toUpperCase();
		s.setCodice(codice);
		s.setNomeUtente(this.Username);
		inc();

		Spedizioni.Aggiungi(s);
	}
}
