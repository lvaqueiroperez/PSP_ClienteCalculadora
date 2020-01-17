package clientecalculadora;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class clienteCalculadora {
    //var global
    public static String operacion = "";

    public static void main(String[] args) {

        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();

            //AQUÍ IRÁ LA INTERFAZ PARA ESCOGER LA OPERACIÓN
            uiOperacion obj = new uiOperacion();
            obj.setVisible(true);

            System.out.println("Estableciendo la conexión al servidor");

            InetSocketAddress addr = new InetSocketAddress("192.168.0.1", 5555);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();
            //ESCRIBIMOS LOS OPERADORES:
            
            
            //ENVIAMOS LOS OPERADORES Y LA OPERACIÓN:
            System.out.println("Enviando mensaje");

            String mensaje = "mensaje desde el cliente";
            os.write(mensaje.getBytes());

            System.out.println("Mensaje enviado");

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
