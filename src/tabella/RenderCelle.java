package tabella;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe che estende DefaultTableCellRenderer, la uso per colorare le celle della tabella
 * in base allo stato della spedizione.
 */
public class RenderCelle extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;

	/**
	 * Metodo che sovrascrivo dalla clase DefaultTableCellRenderer
	 * @param table tabella
	 * @param value valore della cella
	 * @param isSelected indica se la cella è selezionata
	 * @param hasFocus indica se la cella ha il focus
	 * @param row indice di riga della cella
	 * @param column indice di colonna della cella
	 * @return il componente colorato e centrato rispetto alla cella della tabella
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected,boolean hasFocus,int row,int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setHorizontalAlignment(JLabel.CENTER);

		/* setta il colore in base allo stato della spedizione */
		String stato = (String) table.getValueAt(row, 5);
		switch(stato) {
		
			case "IN PREPARAZIONE":{
				setBackground(Color.ORANGE);
				break;
			}
			case "IN TRANSITO":{
				setBackground(Color.PINK);
				break;
			}
			case "RICEVUTA":{
				setBackground(Color.GREEN);
				break;
			}
			case "FALLITA":{
				setBackground(Color.RED);
				break;
			}
			case "RICHIESTA RIMBORSO":{
				setBackground(Color.ORANGE);
				break;
			}
			case "RIMBORSO EROGATO":{
				setBackground(Color.GREEN);
				break;
			}
			/* questo non si deve verificare */
			default: {
				setBackground(Color.BLUE);
				break;
			}
		}
	return this;
	}

}


