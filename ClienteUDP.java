package chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ClienteUDP {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        InetAddress servidorDireccion = InetAddress.getByName("localhost");
        int servidorPuerto = 1234;

        System.out.println("¿Como te llamas?");
        enviarServidor(bufferedReader.readLine(), socket, servidorDireccion, servidorPuerto);
        System.out.println("¿A quien se lo envias?");
        enviarServidor(bufferedReader.readLine(), socket, servidorDireccion, servidorPuerto);

        while (true) {
            System.out.println("¿Qué mensaje quieres enviar?");
            String mensaje = bufferedReader.readLine();
            enviarServidor(mensaje, socket, servidorDireccion, servidorPuerto);

            byte[] buffer = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuesta);

            String mensajeRecibido = new String(respuesta.getData(), 0, respuesta.getLength());
            System.out.println("Servidor: " + mensajeRecibido);
        }
    }

    public static void enviarServidor(String mensaje, DatagramSocket socket, InetAddress direccion, int puerto) throws IOException {
        byte[] buffer = mensaje.getBytes();
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, puerto);
        socket.send(paquete);
    }
}
