
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MulticastClientClassico {

    private static final String GRUPO = "230.0.0.1";
    private static final int PORTA = 4446;

    public static void main(String[] args) throws IOException {

        InetAddress grupo = InetAddress.getByName(GRUPO);

        try (MulticastSocket socket = new MulticastSocket(PORTA)) {

            socket.joinGroup(grupo);

            System.out.println(
                "Membro do grupo " + GRUPO + ":" + PORTA
            );

            byte[] dados = new byte[1024];
            DatagramPacket pacote =
                new DatagramPacket(dados, dados.length);

            socket.receive(pacote);

            String mensagem = new String(
                pacote.getData(),
                pacote.getOffset(),
                pacote.getLength(),
                StandardCharsets.UTF_8
            );

            System.out.println("Recebido: " + mensagem);

            socket.leaveGroup(grupo);
        }
    }
}
