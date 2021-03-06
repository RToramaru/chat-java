/*
Trabalho Chat java
Alunos: Rafael ALmeida Soares
        Edinilson Cardoso de Oliveira
*/
package service;

import bean.ChatMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael ALmeida Soares
 */
public class ClienteService {

    private Socket socket;
    private ObjectOutputStream output;

    private int porta;
    private String ip;

    public ClienteService(int porta, String ip) {
        this.porta = porta;
        this.ip = ip;
    }

    public ClienteService() {

    }

    public Socket connect() {
        try {
            this.socket = new Socket(ip, porta);
            this.output = new ObjectOutputStream(socket.getOutputStream());
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Nenhum servidor encontrado nesse host");
            return null;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Nenhum servidor encontrado nesse ip/porta");
            return null;
        }

        return socket;
    }

    public void send(ChatMessage message) {
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
