package spedizioni;

/**
 * Classe che implementa una spedizione di tipo assicurato, estende la classe
 * spedizione da cui eredita tutti gli attributi e i metodi, in piu aggiunge l' attributo
 * valore assicurato
 * @author  Andrea Bonfatti
 */
public class SpedizioneSicura extends Spedizione {

	/**
	 * valore assicurato della spedizione
	 */
	private int ValoreAssicurato;
	
	/**
	 * metodo costruttore che uso quando creo un oggetto di tipo spedizione assicurata
	 * @param destinazione destinazione della spedizione
	 * @param peso peso della spedizione
	 * @param Val valore assicurato della spedizione
	 */
	public SpedizioneSicura(String destinazione, int peso, int Val) {
		super(destinazione, peso);
		setValoreAssicurato(Val);
	}

	/**
	 * metodo costruttore che uso quando creo un oggetto di tipo spedizione assicurata leggendo
	 * i dati da file
	 * @param Nome nome dell' utente che ha effettuato la spedizione
	 * @param Codice codice della spedizione assicurata
	 * @param Destinazione destinazione della spedizione assicurata
	 * @param Peso peso della spedizione assicurata
	 * @param Data data di creazione della spedizione assicurata
	 * @param Stato stato della spedizione assicurata
	 * @param val valore assicurato della spedizione
	 */
	public SpedizioneSicura (String Nome, String Codice, String Destinazione, int Peso, String Data, String Stato, int val) {
		super (Nome, Codice, Destinazione, Peso, Data, Stato);
		ValoreAssicurato = val;
		
	}

	/**
	 * metodo che ritorna il valore assicurato della spedizione
	 * @return valore assicurato della spedizione
	 */
	@Override
	public int getValoreAssicurato() { return ValoreAssicurato;}

	/**
	 * medodo che setta il valore assicurato della spedizione
	 * @param val valore assicurato della spedizione
	 */
	public void setValoreAssicurato(int val){
		ValoreAssicurato = val;
	}

	@Override
	public String toString(){
		return ("SICURA"+"\n"+getValoreAssicurato()+"\n"+ getNomeUtente()+"\n"+getCodice()+"\n"+getDestinazione()+"\n"+
				getPeso()+"\n"+getDataImmissione()+"\n"+getStato()+"\n");
	}

	@Override
	public boolean StatoFinale() {
		String stato = this.getStato();
		if (stato.equals("RICEVUTA")|| stato.equals("RIMBORSO EROGATO"))
			return true;
		return false;
	}

	@Override
	public boolean PossibileRimborso(){
		String stato = this.getStato();
		if (stato.equals("FALLITA"))
			return true;
		return false;
	}

}