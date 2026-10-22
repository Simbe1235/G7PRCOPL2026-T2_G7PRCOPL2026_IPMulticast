
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MulticastServerClassico {

    private static final String GRUPO = "230.0.0.1";
    private static final int PORTA = 4446;

    public static void main(String[] args) throws IOException {

        InetAddress grupo = InetAddress.getByName(GRUPO);

        String mensagem = "Ola, membros do grupo multicast!";
        byte[] dados = mensagem.getBytes(StandardCharsets.UTF_8);

        try (MulticastSocket socket = new MulticastSocket()) {

            DatagramPacket pacote = new DatagramPacket(
                dados,
                dados.length,
                grupo,
                PORTA
            );

            socket.send(pacote);

            System.out.println(
                "Mensagem enviada para " + GRUPO + ":" + PORTA
            );
        }
    }
}
