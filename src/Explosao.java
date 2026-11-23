import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Explosao {

    private ArrayList<Particula> particulas;
    private Random random;

    public Explosao(int x, int y) {
        particulas = new ArrayList<>();
        random = new Random();

        criarParticulas(x, y);
    }

    private void criarParticulas(int x, int y) {
        int quantidade = 100;

        for (int i = 0; i < quantidade; i++) {
            double angulo = random.nextDouble() * Math.PI * 2;
            double velocidade = 100 + random.nextDouble() * 250;

            double vx = Math.cos(angulo) * velocidade;
            double vy = Math.sin(angulo) * velocidade;

            float vida = 0.8f + random.nextFloat() * 0.8f;

            // Escolhe uma cor para cada partícula.
            Color[] cores = {
                Color.CYAN,
                Color.BLUE,
                Color.MAGENTA,
                Color.PINK,
                Color.WHITE
            };
            Color cor = cores[random.nextInt(cores.length)];

            particulas.add(new Particula(x, y, vx, vy, vida, cor));
        }
    }

    public void atualizar(double deltaTime) {
        Iterator<Particula> it = particulas.iterator();

        while (it.hasNext()) {
            Particula particula = it.next();
            particula.atualizar(deltaTime);

            if (!particula.estaViva()) {
                it.remove();
            }
        }
    }

    public void desenhar(Graphics2D g) {
        for (Particula particula : particulas) {
            particula.desenhar(g);
        }
    }

    public boolean terminou() {
        return particulas.isEmpty();
    }}
