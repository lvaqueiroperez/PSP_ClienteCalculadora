package clientecalculadora;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//CALCULADORA SIMPLE
//USAREMOS HILOS PARA IMPEDIR QUE EL PROGRAMA AVANCE SIN QUE SE HAYA ESCOGIDO LA OPERACIÓN???? yield???
//SE HA MODIFICADO EL PROTOCOLO, DE MANERA QUE AHORA NO SE UTILIZAN TANTAS VARIABLES STRING
public class clienteCalculadora {

    public static void main(String[] args) throws InterruptedException {

        try {

            System.out.println("***** Creando socket cliente *****");
            Socket clienteSocket = new Socket();
            System.out.println("***** Estableciendo la conexión *****");

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            clienteSocket.connect(addr);

            //OPERACIÓN
            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

            System.out.println("***** Enviando operación *****");

            int operacion = Integer.parseInt(JOptionPane.showInputDialog("INTRODUCE LA OPERACIÓN A REALIZAR\n1) +\n2) -\n3) *\n4) /"));
            //(podríamos comprobar que se ha introducido el dato correcto con un bucle etc etc etc)

            //MANDAMOS EL MENSAJE AL SERVIDOR A TRAVÉS DE "write()",  SE MANDARÁ EN FORMATO DE BYTES 
            //"write()" PUEDE ENVIAR MENSAJES TANTO EN UN ARRAY BYTES COMO EN UN SIMPLE INT
            os.write(operacion);

            System.out.println("***** Mensaje enviado *****");

            //OJO!!! DEJAMOS UN MARGEN DE SEGUNDOS PARA QUE SE ENVÍE EL MENSAJE Y NO HAYA ERRORES
            sleep(2000);

            //ENVIAMOS LOS OPERADORES TAMBIÉN COMO INTEGERS
            //OPERADOR 1
            System.out.println("***** Enviando operador 1 *****");

            int operador1 = Integer.parseInt(JOptionPane.showInputDialog("ESCRIBE EL OPERADOR 1: (INT)"));
            //COMO EN ESTE CASO ESTAMOS ENVIANDO UN STRING, TENEMOS QUE CONVERTIRLO A BYTES
            os.write(operador1);

            System.out.println("***** Operador 1 enviado *****");

            //OPERADOR 2
            sleep(2000);

            System.out.println("***** Enviando operador 2 *****");
            int operador2 = Integer.parseInt(JOptionPane.showInputDialog("ESCRIBE EL OPERADOR 2: (INT)"));

            os.write(operador2);

            System.out.println("***** Operador 2 enviado *****");

            //ESPERAMOS AL RESULTADO DEL SERVIDOR: (ESTARÁ EN BYTES PERO PODEMOS PASARLO DIRECTAMENTE A INT CON EL MÉTODO DE INPUT STREAM)
            int resultado = is.read();
            //PARSEAMOS EL STRING DE BYTES A STRING
            System.out.println("***** ESPERANDO RESULTADO *****");
            System.out.println("Mensaje recibido: EL RESULTADO ES = " + resultado);

            //CERRAMOS EL SOCKET DEL CLIENTE
            System.out.println("***** Cerrando el socket cliente *****");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
