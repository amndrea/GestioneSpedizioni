package spedizioni;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che descrive una spedizione
 * @author Andrea Bonfatti
 */
public class Spedizione  {

	/**
	 * nome dell' utente che ha effettuato la spedizione
	 */
	private String NomeUtente;

	/**
	 * codice della spedizione
	 */
	private String Codice;

	/**
	 * destinazione della spedizione
	 */
	private String Destinazione;

	/**
	 * peso della spedizione
	 */
	private int Peso;

	/**
	 * data in cui è stata creata la spedizione
	 */
	private String DataImmissione;

	/**
	 * stato in cui si trova la spedizione
	 */
	private String Stato;
	
	/**
	 * metodo costruttore che uso quando creo un oggetto di tipo spedizione
	 * dalla schermata dell' utente
	 * @param destinazione destinazione della spedizione
	 * @param peso peso della spedizione
	 */
	public Spedizione( String destinazione, int peso) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(new Date());
		setDataImmissione(data);
		setDestinazione(destinazione);
		setPeso(peso);
		setStato("IN PREPARAZIONE");
	}
	
	/**
	 * metodo costruttore che uso quando creo un oggetto di tipo spedizione leggendo i dati
	 * da file
	 * @param Nome nome dell' utente che ha effettuato la spedizione
	 * @param Codice codice della spedizione
	 * @param Destinazione destinazione della spedizione
	 * @param Peso peso della spedizione
	 * @param Data data di creazione della spedizione
	 * @param Stato stato della spedizione
	 */
	public Spedizione(String Nome, String Codice, String Destinazione, int Peso, String Data, String Stato) {
		setNomeUtente(Nome);
		setCodice(Codice);
		setDestinazione(Destinazione);
		setPeso(Peso);
		setDataImmissione(Data);
		setStato(Stato);
	}

	/**
	 * metodo che ritorna il codice della spedizione
	 * @return codice della spedizione
	 */
	public String getCodice() { return Codice; }

	/**
	 * metodo che setta il codice di una spedizione
	 * @param codice nuovo codice della spedizione
	 */
	public void setCodice(String codice) { Codice = codice; }

	/**
	 * metodo che ritorna la data di creazione della spedizione
	 * @return data di creazione della spedizione
	 */
	public String getDestinazione() { return Destinazione; }

	/**
	 * metodo che setta la destinazione della spedizione
	 * @param destinazione destinazione della spedizione
	 */
	public void setDestinazione(String destinazione) { Destinazione = destinazione; }

	/**
	 * metodo che ritorna il peso della spedizione
	 * @return peso della spedizione
	 */
	public int getPeso() { return Peso; }

	/**
	 * metodo che setta il peso della spedizione
	 * @param peso peso della spedizione
	 */
	public void setPeso(int peso) { Peso = peso; }

	/**
	 * metodo che ritorna lo stato della spedizione
	 * @return stato della spedizione
	 */
	public String getStato() { return Stato; }

	/**
	 * metodo che setta lo stato della spedizione
	 * @param stato stato della spedizione
	 */
	public void setStato(String stato) { Stato = stato; }

	/**
	 * metodo che ritorna la data di creazione della spedizione
	 * @return data di immissione della spedizione
	 */
	public String getDataImmissione() { return DataImmissione; }

	/**
	 * metodo che setta la data di creazione della spedizione
	 * @param dataImmissione data di creazione della spedizione
	 */
	public void setDataImmissione(String dataImmissione) { DataImmissione = dataImmissione; }


	//public String toString() { return (Codice+ " "+ Destinazione+" "+ Peso); }

	/**
	 * metodo che ritorna il nome dell' utente che ha effettuato la spedizione
	 * @return nome dell' utente che ha effettuato la spedizione
	 */
	public String getNomeUtente() { return NomeUtente;}

	/**
	 * metodo che setta il nome dell' utente che ha effettuato la spedizione
	 * @param nomeUtente nome dell' utente che ha effettuato la spedizione
	 */
	public void setNomeUtente(String nomeUtente) { NomeUtente = nomeUtente; }


	/**
	 * metodo che uso per decidere se posso richiedere il rimborso di una spedizione
	 * @return true se posso richiedere il rimborso, false altrimenti
	 */
	public boolean PossibileRimborso(){
		return false;
	}

	/**
	 * metodo che uso per verificare se una spedizione si trova in uno stato finale quindi se posso rimuoverla dalla
	 * lista di spedizioni
	 * @return true se la spedizione è in uno stato finale, false altrimenti
	 */
	public boolean StatoFinale(){
		String stato = getStato();
		if (stato.equals("RICEVUTA") || Stato.equals("FALLITA"))
			return true;
		return false;
	}


	public String toString(){
		return ("NORMALE\n"+getNomeUtente()+"\n"+getCodice()+"\n"+getDestinazione()+"\n"+getPeso()+"\n"+
				getDataImmissione()+"\n"+getStato()+"\n");
	}

	public int getValoreAssicurato(){
		return 0;
	}
}