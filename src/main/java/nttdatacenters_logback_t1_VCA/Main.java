package nttdatacenters_logback_t1_VCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.LoggerFactory;

/**
 * Formacion Dual SEL4J && logback
 * @author Victor Carrasco
 *
 */
public class Main 
{
	/**
	 * logger
	 */
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
	
	/**
	 * userList
	 */
	public static Map<Integer, User> userList = new TreeMap<>();
	
	/**
	 * main
	 * @param args
	 */
    public static void main( String[] args )
    {
        registerUser(new User(1, "Juan", "Alvarez", "Calle Avila", 679872123));
        for(int i = 1; i<=100; i++) {
        	registerUser(new User(i));
        }
    }
    
    /**
     * Register User
     * @param user
     */
    public static void registerUser(User user) {
    	
    	logger.info("Resgistro del usuario {}", user.getId());
    	
    	//Llamo a checkUser para verificar si ha sido intorducido en el fichero
    	if(checkUser(user)) {
    		logger.error("\tEl usuario ya esta registrado.");
    	}else {
    		userList.put(user.getId(), user);
    		try(BufferedWriter out = new BufferedWriter(new FileWriter(".\\src\\Files\\users.info", true))){
    			out.write(user.toString());
    			out.newLine();
        		logger.info("\tUsuario registrado con exito.");
    		} catch (IOException e) {
    			logger.error(e.toString());
			}
    	}
    }
    
    /**
     * checkUser
     * @param user
     * @return
     */
    private static boolean checkUser(User user) {
    	String item = null;
    	boolean existeUsuario = false;
    	int numeroLinea = 1;
    	try(BufferedReader in = new BufferedReader(new FileReader(".\\\\src\\\\Files\\\\users.info"))){
    		item = in.readLine();
    		while(item!=null&&!existeUsuario) {
    			logger.debug("\tid con el que se compara: {}", getIdAtLine(numeroLinea));
    			
    			//Se lee el fichero linea a linea hasta que en la posicion 4 de cada linea encuentre el id
    			//y lo compare con el id del usuario pasado por parametro
    			if(getIdAtLine(numeroLinea).equals(Integer.toString(user.getId()))) {
    				logger.debug("\t\texisteUsuario=true");
    				existeUsuario = true;
    			}
    			item = in.readLine();
    			numeroLinea++;
    		}
    	} catch (FileNotFoundException e) {
    		logger.error("\t\tNo existe ningun archivo con registros de usuarios....CREANDO");
    		existeUsuario = false;
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return existeUsuario;
    }
    
    /**
     * getIdAtLine
     * @param numeroLinea
     * @return
     */
    @SuppressWarnings("unused")
	private static String getIdAtLine (int numeroLinea) {
    	int contador = 1;
    	int posInLine = 3;
    	String text = "";
    	String item;
    	try(BufferedReader in = new BufferedReader(new FileReader(".\\\\src\\\\Files\\\\users.info"))){
    		item = in.readLine();
    		contador++;
    		//lee las lineas hasta llegar a la linea donde se desea obtener el id
    		while(item!=null&&contador<=numeroLinea) {
    			item = in.readLine();
    			contador++;
    		}
    		//se obtiene el id completo, sabiendo que este se encuentra a partir de la posicion 
    		//3 y hasta que aparezaca una coma
    		while(item.charAt(posInLine)!=',') {
    			text += item.charAt(posInLine);
    			posInLine++;
    		}
    		
    	} catch (FileNotFoundException e) {
    		logger.error("\tNo existe ningun archivo con registros de usuarios....CREANDO");
		} catch (IOException e) {
			logger.error(e.toString());
		}
    	return text;
    }
}
