import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Campo_Minado extends JFrame implements ActionListener {

    private JButton[][] botoes;
    private boolean[][] tabuleiro;
    private JTextField textFieldTentativa;
    private JLabel labelResultado;

    private Random random;
    private int tentativaAtual;
    private int z = 0 ;

    public Campo_Minado() {
        setTitle("Campo Minado");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Cria e adiciona os botões à janela
        JPanel panelBotoes = new JPanel(new GridLayout(8, 8));
        botoes = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].addActionListener(this);
                panelBotoes.add(botoes[i][j]);
            }
        }
        add(panelBotoes);


        // Cria e adiciona o rótulo de resultado à janela
        JPanel panelResultado = new JPanel(new FlowLayout());
        labelResultado = new JLabel();
        panelResultado.add(labelResultado);
        add(panelResultado);

        // Cria e adiciona o botão de reset à janela
        JPanel panelReset = new JPanel(new FlowLayout());
        JButton botaoReset = new JButton("Reset");
        botaoReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo();
            }
        });
        panelReset.add(botaoReset);
        add(panelReset);

        // Inicializa as variáveis
        random = new Random();
        tabuleiro = new boolean[8][8];


        // Atribui aleatoriamente as minas ao tabuleiro
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tabuleiro[i][j] = random.nextBoolean();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (z != 1){
        // Verifica se a célula clicada contém uma mina
        JButton botaoClicado = (JButton) e.getSource();
        int linha = -1, coluna = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (botaoClicado == botoes[i][j]) {
                    linha = i;
                    coluna = j;
                    break;
                }
            }
        }
    
        if (tabuleiro[linha][coluna]) {
            labelResultado.setText("Você encontrou uma mina!");
            botaoClicado.setBackground(Color.RED);
            z = 1;
            return;
        } else {
            labelResultado.setText("Não há mina nessa célula!");
            botaoClicado.setBackground(Color.GREEN);
        }}
    

    }
    
    private void reiniciarJogo() {
        // Zera o tabuleiro e reinicia as variáveis
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                botoes[i][j].setBackground(null);
                tabuleiro[i][j] = random.nextBoolean();
            }
        }
        z = 0;
    }
    
    public static void main(String[] args) {
        Campo_Minado campoMinado = new Campo_Minado();
        campoMinado.setVisible(true);
    }}