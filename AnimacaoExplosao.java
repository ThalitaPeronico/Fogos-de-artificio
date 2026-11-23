import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimacaoExplosao extends WeilGraficos {

    private ArrayList<Explosao> explosoes;

    public AnimacaoExplosao() {
        explosoes = new ArrayList<>();
    }

    @Override
    public void onClick(int x, int y, int button) {
        // Cada clique cria uma nova explosão na posição do mouse.
        explosoes.add(new Explosao(x, y));
    }

    @Override
    public void onDraw(Graphics2D g, double deltaTime) {
        Iterator<Explosao> it = explosoes.iterator();

        while (it.hasNext()) {
            Explosao explosao = it.next();
            explosao.atualizar(deltaTime);
            explosao.desenhar(g);

            if (explosao.terminou()) {
                it.remove();
            }
        }
    }
}