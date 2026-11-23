import java.awt.Color;
import java.awt.Graphics2D;

public class Particula {

    private double x;
    private double y;
    private double xAnterior;
    private double yAnterior;
    private double vx;
    private double vy;
    private float vida;
    private float vidaMaxima;
    private Color cor;

    public Particula(double x, double y, double vx, double vy,
                     float vida, Color cor) {
        this.x = x;
        this.y = y;
        this.xAnterior = x;
        this.yAnterior = y;
        this.vx = vx;
        this.vy = vy;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.cor = cor;
    }

    public void atualizar(double deltaTime) {
        xAnterior = x;
        yAnterior = y;

        vida -= deltaTime;

        // A gravidade puxa a partícula para baixo.
        vy += 180 * deltaTime;

        x += vx * deltaTime;
        y += vy * deltaTime;
    }

    public boolean estaViva() {
        return vida > 0;
    }

    public void desenhar(Graphics2D g) {
        float transparencia = vida / vidaMaxima;
        if (transparencia > 1) {
            transparencia = 1;
        }

        int alpha = (int) (255 * transparencia);
        int tamanho = 6;

        // Rastro simples da partícula.
        g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), alpha));
        g.drawLine((int) xAnterior, (int) yAnterior, (int) x, (int) y);

        // Círculos maiores e transparentes criam o efeito de brilho.
        g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), alpha / 4));
        g.fillOval((int) x - tamanho, (int) y - tamanho,
                   tamanho * 2, tamanho * 2);

        g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), alpha));
        g.fillOval((int) x - 2, (int) y - 2, 4, 4);
    }
}