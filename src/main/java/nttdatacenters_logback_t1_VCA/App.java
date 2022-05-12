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
 * Hello world!
 *
 */
public class App 
{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
	public static Map<Integer, User> listaUsuarios = new TreeMap<>();
	
    public static void main( String[] args )
    {
        registrarUsuario(new User(1, "Juan", "Alvarez", "Calle Avila", 679872123));
        for(int i = 1; i<=100; i++) {
        	registrarUsuario(new User(i));
        }
    }
    
    /**
     * Registro de usuario y guardado en fichero
     * @param user
     */
    public static void registrarUsuario(User user) {
    	logger.info("Resgistro del usuario {}", user.getId());
    	//Llamo a comprobarUsuario para verificar si ha sido intorducido en el fichero
    	if(comprobarUsuario(user)) {
    		logger.error("\tEl usuario ya esta registrado.");
    	}else {
    		listaUsuarios.put(user.getId(), user);
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
     * Comprobacion de existencia del usuario en fichero "users.info"
     * @param user
     * @return boolean
     */
    private static boolean comprobarUsuario(User user) {
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
    
    @SuppressWarnings("unused")
	private static String getIdAtLine (int numeroLinea) {
    	int contador = 1;
    	int posInLine = 3;
    	String text = "";
    	String item;
    	try(BufferedReader in = new BufferedReader(new FileReader(".\\\\src\\\\Files\\\\users.info"))){
    		item = in.readLine();
    		contador++;
    		while(item!=null&&contador<=numeroLinea) {
    			item = in.readLine();
    			contador++;
    		}
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
