package main;

import java.util.*;

import model.SalesrepView;

import java.sql.*;

public class App {
	// JDBC driver name and database URL, bytecode che contiene il codice del Driver 
    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    
    // CONNECTION STRING (URL del DB - per conoscerlo tasto dx su DB poi Properties)
    //fino thin è il tipo di Driver poi indirizzo ip, porta, nome del CDB
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:MYDB";

    // Credenziali del Database prima utente (quindi DB in se) poi password
    static final String USER = "SQL_CORSO_DB";
    static final String PASS = "admin";

	public static void main(String[] args) {
		// STEP 1: Registriamo (cioè Installiamo) ORACLE JDBC driver in JVM con il DriverManager
        if(!RegistraOJDBCdriver())
            return;
        
        ExecuteSELECT();

	}

	private static void ExecuteSELECT() {
		// 2) aprire una CONNESSIONE col DB
        // 3) creare una STATEMENT (PREPARED o NO)
        // 4) ESEGUIRE la STATEMENT (invio della ST. al DB)
        // 5) elaborare il RESULT SET
        // 6) chiusura della CONNESSIONE e di tutte le RISORSE utilizzate
		
		// STEP 2: Apriamo una connessione
        System.out.println("Connecting to database...");
        
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
        	System.out.println("Connessione aperta col DB...");

            // STEP 3: Creazione della String SQL (SQL eseguita in modalità EMBEDDED, incorporato)
            String sql = "SELECT name, rep_office, hire_date FROM salesreps";
            
            // STEP 4: Creazione della STATEMENT (oggetto di tipo "Statement")
            //           Semplice Statement (NO parametri)

            System.out.println("Creating statement...");
            Statement stmt = conn.createStatement();
            
            // STEP 5: Eseguiamo la query e sappiamo questa produrrà un ResultSet 
            ResultSet rs = stmt.executeQuery(sql);
            
            // Ora tutti i risultati del ResultSet dovremo inserirli all'interno di una Collection
            // useremo rs come fosse un Cursore, trasformando ogni Record in un oggetto Salesrep
            // devo mappare le colonne sulle proprietà
            
            // STEP 6: Extract data from result set (MAPPING)
            //            MAPPING fra RESULT SET e COLLECTION Java
            //            MAPPING fra un RECORD della TABELLA "salesreps" e un OGGETTO Java
            var salesreps = new ArrayList<SalesrepView>();
            
            // STEP 6.1: MAPPING tra le PROPRIETA' della CLASSE ENTITY (SalesrepView) e i CAMPI
            // del RECORD della TABELLA ENTITY (SALESREPS)

            // rs: CURSORE
            while (rs.next()) {
            	//creo l'oggetto, prendo le proprietà e le uso per inizializzarlo, 
            	//poi lo aggiungo alla collection
            	

            	SalesrepView srepView = new SalesrepView();

                // Recupera dai column name: MAPPING fra entity class e entity table corrispondente
                // ----------- MAPPING ------------------
                srepView.setName(rs.getString("name"));
                srepView.setRep_office(rs.getInt("rep_office"));
                srepView.setHire_date(rs.getDate("hire_date"));

                // Display values
                System.out.println(srepView);

                salesreps.add(srepView);
            }
            
            // STEP 7: chiusura delle RISORSE: CONNESSIONE, STATEMENT, RESULT SET
            //         nell'ordine inverso con cui sono state aperte
            rs.close();
            stmt.close();
        }catch(Exception e) {
        	
        }
		
	}

	private static boolean RegistraOJDBCdriver() {
		try {
			//metodo della classe Class che serve per importare il Driver
			// -> Una chiamata forName("X") comporta che la classe X viene inizializzata
            Class.forName(JDBC_DRIVER); 
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Impossibile registrare la CLASSE del DRIVER");
            return false;
        }
	}
	
}
