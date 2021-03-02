package spedizioni;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa una lista di spedizioni, usando i generics può contenere
 * sia spedizioni, sia spedizioni assicurate
 */
public class ListaSpedizioni implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * lista di spedizioni parametrizzata su Spedizione
	 */
	private List <Spedizione> Spedizioni;

	/**
	 * metodo costruttore che crea e ritorna un ArrayList di spedizioni vuoto
	 */
	public ListaSpedizioni() {
		Spedizioni = new ArrayList<Spedizione>();
	}

	/**
	 * medoto che ritorna la spedizione memorizzata all' indice passato come parametro
	 * @param index indice della spedizione da ritornare
	 * @return la spedizione memorizzata all' indice index
	 */
	public Spedizione get(int index) {
		return Spedizioni.get(index);
	}

	/**
	 * metodo che ritorna il numero di spedizioni memorizzate nell' ArrayList Spedizioni
	 * @return numero di spedizioni momorizzate nell' ArrayList Spedizioni
	 */
	public int gtNumSpedizioni() {
		return Spedizioni.size();
	}

	/**
	 * metodo che aggiunge una nuova spedizione all' ArrayList Spedizioni
	 * @param s spedizione da aggiungere
	 */
	public void  Aggiungi (Spedizione s) {
		Spedizioni.add(s);
	}

	/**
	 * metodo che cancella una spedizione dall' ArrayList Spedizioni
	 * @param s spedizione da cancellare
	 */
	public void  Cancella (Spedizione s){ Spedizioni.remove(s); }

}
