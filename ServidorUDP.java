package chat2;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1234);

        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);

            String mensajeRecibido = new String(paquete.getData(), 0, paquete.getLength());
            System.out.println("Cliente: " + mensajeRecibido);

            // Echo del mensaje de vuelta al cliente
            InetAddress direccionCliente = paquete.getAddress();
            int puertoCliente = paquete.getPort();
            String mensaje = "Mensaje recibido: " + mensajeRecibido;
            byte[] bufferRespuesta = mensaje.getBytes();
            DatagramPacket respuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, direccionCliente, puertoCliente);
            socket.send(respuesta);
        }
    }
}
