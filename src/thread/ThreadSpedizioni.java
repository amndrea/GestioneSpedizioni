package thread;

import spedizioni.ListaSpedizioni;
import spedizioni.Spedizione;
import tabella.SpedizioniTableModel;
import java.util.Random;

/**
 * classe che implementa un Thread di modifica spedizioni
 */
public class ThreadSpedizioni extends Thread {

    /**
     * lista di spedizioni su cui il thread lavorera
     */
    private ListaSpedizioni listasped;

    /**
     * modello della tabella da cui prendo la lista di spedizioni
     */
    private SpedizioniTableModel tablemodel;

    /**
     * variabide di tipo spedizione che uso per lavorare sulla spedizione che seleziono in modo casuale
     */
    private Spedizione spedizione;

    /**
     * variabile che uso per salvare l'indice della spedizione che ho selezionato in modo casuale
     */
    private int IndiceSped;

    /**
     * variabile che uso per memorizzare lo stato della spedizione che ho selezionato in modo casuale
     */
    private String StatoSpedizione;

    /**
     * variabile che uso per la memorizzare il secondo numero casuale, quello con cui scelgo come
     * modificare lo stato di una spedizione
     */
    private int casual;

    /**
     * variabile di tipo random che uso per generare numeri casuali
     */
    private Random rnd;

    /**
     * metodo costruttore del Thread
     * @param t modello della tabella su cui il thread va a lavorare
     */
    public ThreadSpedizioni(SpedizioniTableModel t){
        listasped = t.getLista();
        tablemodel = t;
    }

    /**
     * Metodo run di un Thread. Genero un numero casuale compreso tra 0 e il numero totale
     * di spedizioni presenti nella tabella, e recupero la spedizione di indice uguale al numero
     * generato. Poi genero un secondo numero casuale compreso tra 0 e 100. Im base allo stato
     * della spedizione e al secondo numero casuale, modifico lo stato della spedizione o lo lascio
     * invariato, e notifico alla tabella il cambiamento dei dati con il metodo fireTableDataChanged
     * per vedere subito sulla tabella le variazioni di stato delle spedizioni.
     */
    @Override
    public void  run() {

        rnd = new Random();

       //SELEZIONO IN MODO CASUALE UNA SPEDIZIONE DI UN QUALSIASI UTENTE
       IndiceSped = rnd.nextInt(listasped.gtNumSpedizioni());
       System.out.println("selezionata la spedizione di indice "+ IndiceSped);
       spedizione = listasped.get(IndiceSped);
       StatoSpedizione = spedizione.getStato();

       //GENERO UN NUMERO CASUALE E IN BASE AL NUMERO E ALLO STATO ATTUALE DELLA SPEDIZIONE LA MODIFICO
       casual = rnd.nextInt(100);
       System.out.println("numero casuale "+ casual);

       //SE IL NUMERO CASUALE E COMPRESO TRA 0 E 30 E LA SPEDIZIONE E "IN PREPARAZIONE" PASSA "IN TRANSITO"
       if (casual < 30){
           if (StatoSpedizione.equals("IN PREPARAZIONE")){
               spedizione.setStato("IN TRANSITO");
                tablemodel.fireTableDataChanged();
           }
       }

       //SE IL NUMERO CASUALE E COMPRESO TRA 31 e 79 E LA SPEDIZIONE E "IN TRANSITO" PASSA "RICEVUTA"
       if (casual > 30 && casual < 80){
           if (StatoSpedizione.equals("IN TRANSITO")){
               spedizione.setStato("RICEVUTA");
               tablemodel.fireTableDataChanged();
           }
       }

       //SE IL NUMERO CASUALE E MAGGIORE DI 80 E LA SPEDIZIONE PUO FALLIRE ALLORA FALLISCE
       if (casual > 80){
           if (StatoSpedizione.equals("IN TRANSITO") || StatoSpedizione.equals("IN PREPARAZIONE")){
               spedizione.setStato("FALLITA");
               tablemodel.fireTableDataChanged();
           }
       }

       //SE IL NUMERO CASUALE E MINORE DI 70 ALLORA LA FACCIO PASSARE IN RIMBORSO EROGATO
       if (casual < 70){
           if (StatoSpedizione.equals("RICHIESTA RIMBORSO")){
               spedizione.setStato("RIMBORSO EROGATO");
               tablemodel.fireTableDataChanged();
           }
       }
   }
}
