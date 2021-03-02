package persone;
import java.io.*;
import java.util.*;
import spedizioni.*;


/**
 * classe che implementa un arrayList di utenti
 * @author Andrea Bonfatti
 *
 */
public class ListaUtenti{
	/**
	 * nome del file in cui scrivo/leggo la lista di utenti e di spedizioni
	 */
	private static final String filename="ListaUtenti.txt";

	/**
	 * lista di utenti
	 */
	private List <Utente> Utenti;
	
	/**
	 * metodo costruttore che crea un nuovo arrayList di utenti 
	 */
	public ListaUtenti() {
		Utenti = new ArrayList<Utente>();
	}
	
	/**
	 * medoto per aggiungere un utente all' interno del vettore
	 * @param u
	 */
	public void  Aggiungi (Utente u) {
		Utenti.add(u);
	}

	
	/**
	 * metodo che restituisce il numero di utenti registrati
	 * @return numero utenti registrati
	 */
	public int getNumUtenti() {
		return Utenti.size();
	}
	
	/**
	 * metodo che restituisce l' utente a un determinato indice nel vettore
	 * @param index indice del vettore da cui voglio ritonrare l' utente
	 * @return utente all' indice
	 */
	public Utente get(int index) {
		return Utenti.get(index);
	}
	
	/**
	 * metodo per stabilire se un Username � gia persente nel vettore 
	 * @param u Utente di cui voglio confrontare l' username
	 * @return vero se l' Username � gia presente nel vettore 
	 */
	public boolean GiaPresente(Utente u) {
		boolean giaPresente = false;
		for (int i=0; i<this.getNumUtenti(); i++) {
			Utente utente = get(i);
			if (utente.getUsername().equals(u.getUsername())) {
				giaPresente = true;
			}
		}
		return giaPresente;
	}
	
	
	/**
	 * metodo per salvare la lista di utenti su file di testo dal nome predefinito.
	 * per ogni utente scrivo "contina" cosi so se continuare a leggere nel caricamento,
	 * poi scrivo i dati relativi all' utente, il numero di spedizioni, e tutte le spedizioni
	 */
	public void SalvaLista ()  {
		File f = new File("");
		System.out.println("Directory Corrente"+ f.getAbsolutePath());
		File file = new File(f+filename);
		
		try {
			FileWriter fw = new FileWriter (file);
			for (int i=0; i<getNumUtenti(); i++) {

				/*scrivo i dati dell' utente */
				Utente u = get(i);
				fw.write("Continua"+"\n");
				fw.write(u.getUsername()+"\n");
				fw.write(u.getPassword()+"\n");
				fw.write(u.getIndirizzo()+"\n");
				fw.write(u.getNumSpedizioni()+"\n");


				/*
				ListaSpedizioni l = u.getSpedizioni();
				for (int j=0; j<l.gtNumSpedizioni(); j++) {

					Spedizione s = l.get(j);
					if (s instanceof SpedizioneSicura) {
						fw.write("sicura"+ "\n");
						fw.write(((SpedizioneSicura) s).getValoreAssicurato()+"\n");
					}

					else {
						fw.write("non assicurata"+"\n");
					}

					//scrivo i dati della spedizione
					fw.write(s.getNomeUtente()+"\n");
					fw.write(s.getCodice()+"\n");
					fw.write(s.getDestinazione()+"\n");
					fw.write(s.getPeso()+"\n");
					fw.write(s.getDataImmissione()+"\n");
					fw.write(s.getStato()+"\n");
				}
				 */
				ListaSpedizioni sped = u.getSpedizioni();
				for (int j=0; j< sped.gtNumSpedizioni(); j++){
					fw.write(sped.get(j).toString());
				}
			}
			fw.close();
			
		}
		catch(IOException e) {
			System.out.println("errore nella scrittura di file ");
			e.printStackTrace();
			System.exit(-100);
		}
	}
	
	/**
	 * metodo per caricare la lista di utenti da file di testo. Se non trova il file su cui leggere
	 * i dati ne crea uno con il metodo SalvaLista
	 */
	public void CaricaLista () {
		File f = new File("");
		File file = new File(f+filename);
		try {
			FileReader filein = new FileReader (file);
		    BufferedReader b=new BufferedReader(filein);
		    String Continua = null;
		    Continua = b.readLine();

	    	if (Continua == null) {
	    		b.close();
	    		System.out.println("nulla letto");
	    	}
	    	else {
	    		while (Continua != null) {
		    		
		    		String user, password, indirizzo, ns;
		    		user = b.readLine();
		    		password = b.readLine();
		    		indirizzo = b.readLine();
		    		ns = b.readLine();
			    	int numsped = Integer.parseInt(ns);
			    	
			    	Utente U = new Utente(user, password, indirizzo);
			    	Aggiungi (U);
			    	
			    	for (int i=0; i<numsped; i++) {
			    		String controllo = b.readLine();
			    		System.out.println(controllo);
			    		System.out.println("La strina letta e di "+ controllo.length());
			    		String nome, codice, destinazione, peso, data, stato;

			    		/*
			    		if (controllo.equals("sicura")) {
			    			String val = b.readLine();
			    			nome = b.readLine();
			    			codice = b.readLine();
			    			destinazione = b.readLine();
			    			peso = b.readLine();
			    			data = b.readLine();
			    			stato = b.readLine();
			    			int Peso = Integer.parseInt(peso);
			    			int Val = Integer.parseInt(val);
			    			SpedizioneSicura s = new SpedizioneSicura(nome, codice, destinazione, Peso, data, stato, Val);
			    			U.AggiungiSpedizione(s);
			    		}
			    		else {
			    			nome = b.readLine();
			    			codice = b.readLine();
			    			destinazione = b.readLine();
			    			peso = b.readLine();
			    			data = b.readLine();
			    			stato = b.readLine();
			    			int Peso = Integer.parseInt(peso);
			    			Spedizione s = new Spedizione(nome, codice, destinazione, Peso, data, stato);
			    			U.AggiungiSpedizione(s);
			    		}
			    		 */
						if (controllo.equals("SICURA")){
							System.out.println("leggo una spedizione sicura");
							String val = b.readLine();
							nome = b.readLine();
							codice = b.readLine();
							destinazione = b.readLine();
							peso = b.readLine();
							data = b.readLine();
							stato = b.readLine();
							int Peso = Integer.parseInt(peso);
							int Val = Integer.parseInt(val);
							SpedizioneSicura s = new SpedizioneSicura(nome, codice, destinazione, Peso, data, stato, Val);
							U.AggiungiSpedizione(s);

						}
						else{
							System.out.println("leggo una spedizione normale");
							nome = b.readLine();
							codice = b.readLine();
							destinazione = b.readLine();
							peso = b.readLine();
							data = b.readLine();
							stato = b.readLine();
							int Peso = Integer.parseInt(peso);
							Spedizione s = new Spedizione(nome, codice, destinazione, Peso, data, stato);
							U.AggiungiSpedizione(s);
						}
			    	}
			    	Continua = b.readLine();
		    	}
		    	b.close();
		    	System.out.println("finito di leggere ");
	    	}
	    	
		}
		/* se non trovo il file uso la funzione salvalista per crearne uno */
		catch(FileNotFoundException e2){
			System.out.println("non ho trovato il file cosi ne creo uno");
			SalvaLista();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(-200);
		}

	}
	
	/**
	 * metodo per salvare sul file della lista di utenti solo un utente aggiungendolo alla fine del file
	 * senza bisongo di riscrivere tutta la lista
	 * @param u utente da aggiungere in fondo al file
	 */
	public void AggiungiUnNuovoUtente(Utente u) {
		System.out.println("sono nel metodo per il salvataggio di un utente singolo");
		File f = new File("");
		System.out.println("Directory Corrente"+ f.getAbsolutePath());
		File file = new File(f.getAbsolutePath()+filename);
		
		try {
			FileWriter fw = new FileWriter (file, true);
			fw.write("Continua"+"\n");
			fw.write(u.getUsername()+"\n");
			fw.write(u.getPassword()+"\n");
			fw.write(u.getIndirizzo()+"\n");
			fw.write(u.getNumSpedizioni()+"\n");
			fw.close();
		}
		catch(IOException e3) {
			e3.printStackTrace();
		}
	}


	/**
	 * metodo che utilizzo per trovare un utente partendo dal suo username
	 * @param nome nome dell' utente che sto cercando
	 * @return l'utente se esiste, null altrimenti
	 */
	public Utente TrovaUtente(String nome){
		for (int i=0; i<getNumUtenti(); i++){
			Utente tmp = get(i);
			if (tmp.getUsername().equals(nome))
				return tmp;
		}
		return null;
	}
}
