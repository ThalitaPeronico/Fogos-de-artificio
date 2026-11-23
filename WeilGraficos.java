import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class WeilGraficos extends JPanel {

    private long tempoAnterior;

    protected WeilGraficos() {
        setBackground(java.awt.Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onClick(e.getX(), e.getY(), e.getButton());
            }
        });
    }

    // Método chamado várias vezes por segundo para desenhar a tela.
    public abstract void onDraw(Graphics2D g, double deltaTime);

    // Método chamado quando o usuário clica com o mouse.
    public void onClick(int x, int y, int button) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long agora = System.nanoTime();
        double deltaTime = 0;

        if (tempoAnterior != 0) {
            deltaTime = (agora - tempoAnterior) / 1_000_000_000.0;
        }
        tempoAnterior = agora;

        onDraw((Graphics2D) g, deltaTime);
    }

    // Cria a janela e inicia a atualização da animação.
    public void iniciarGraficos() {
        JFrame janela = new JFrame("Explosão de Partículas");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(800, 600);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.add(this);
        janela.setVisible(true);

        Timer timer = new Timer(16, e -> repaint());
        timer.start();
    }
}