package tabella;

import javax.swing.table.AbstractTableModel;
import spedizioni.*;

/**
 * classe che definisce il moodello della tabella, estende la classe AbstractableModel da cui
 * eredita tutte le caratteristiche, e fa override di metodi
 */
public class SpedizioniTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	//intestazione delle colonne
	private String[] ColName= {"Nome", "Codice", "Destinazione", "Peso", "Data", "Stato", "Valore Assicurato"};

	/**
	 * lista di spedizioni con la quale vado a costruire la tabella
	 */
	private ListaSpedizioni l;

	/**
	 * metodo costruttore
	 * @param l lista di spedizioni con la quale costruisco la tabella
	 */
	public SpedizioniTableModel(ListaSpedizioni l) {
		this.l = l;
	}

	/**
	 * metodo che ritorna il numero di colonne della tabella
	 */
	public int getColumnCount() {
		return ColName.length;
	}

	/**
	 * metodo che ritorna il numero di righe della tabella
	 */
	public int getRowCount() {
		return l.gtNumSpedizioni();
	}

	/**
	 * metodo che ritorna il contenuto della cella di indici row/col 
	 */
	public Object getValueAt(int row, int col) {

		/*SELEZIONA LA SPEDIZIONE*/
		Spedizione s = l.get(row);

		/*LA STRINGA CORRISPONDENTE ALLA COLONNA */
		switch (col) {
			case 0:
				return s.getNomeUtente();
			case 1:
				return s.getCodice();
			case 2:
				return s.getDestinazione();
			case 3:
				return s.getPeso();
			case 4:
				return s.getDataImmissione();
			case 5:
				return s.getStato();
				/*
			case 6: {
				if (s instanceof SpedizioneSicura)
					return ((SpedizioneSicura) s).getValoreAssicurato();
				else
					return 0;

			}
			*/
			case 6:
				return s.getValoreAssicurato();
			default:
				return null;
		}
	}

	/**
	 * metodo che ritorna il nome della colonna
	 */
	 public String getColumnName(int col) {
		 return ColName[col];
	 }

	 /**
	  * metodo che stabilisce quali celle sono editabili
	  */
	public boolean isCellEditable(int row, int col) {
		if (col == 5)
			return true;
		return false;
	}

	/**
	 * metodo per settarare lo stato di una spedizione
	 * @param aValue nuovo stato della spedizione
	 * @param rowIndex indice di riga della spedizione
	 * @param columnIndex indice di colonna della spedizione
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String NuovoValore = (String) aValue;
		Spedizione s = l.get(rowIndex);
		s.setStato(NuovoValore);
		fireTableDataChanged();
	}

	/**
	 * @return lista di spedizioni con cui è costrutita la tabella
	 */
	public ListaSpedizioni getLista(){ return l;}



}


