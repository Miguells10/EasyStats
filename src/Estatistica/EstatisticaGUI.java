package Estatistica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstatisticaGUI extends JFrame {

    private JTextField inputField; // Campo de entrada de dados
    private JTextArea outputArea;  // Área para exibir os resultados
    private JButton restartButton; // Botão de reiniciar

    public EstatisticaGUI() {
        super("Cálculos Estatísticos"); // Define o título da janela

        // Configurações da janela
        setSize(1200, 600); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout()); // Define o layout da janela

        // Painel superior (entrada)
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel inputLabel = new JLabel("Digite os dados separados por espaços:");
        inputField = new JTextField(30);
        JButton calculateButton = new JButton("Calcular");

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(calculateButton);

        // Área central (resultados)
        outputArea = new JTextArea();
        outputArea.setEditable(false); // A área de texto é apenas para leitura
        JScrollPane scrollPane = new JScrollPane(outputArea); // Adiciona rolagem

        // Painel inferior (botão de reiniciar)
        JPanel bottomPanel = new JPanel(new FlowLayout());
        restartButton = new JButton("Reiniciar");
        bottomPanel.add(restartButton);

        // Adicionar componentes à janela
        add(inputPanel, BorderLayout.NORTH); // Adiciona o painel de entrada na parte superior
        add(scrollPane, BorderLayout.CENTER); // Adiciona a área de texto no centro
        add(bottomPanel, BorderLayout.SOUTH); // Adiciona o painel inferior na parte inferior

        // Evento do botão "Calcular"
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarCalculos();
            }
        });

        // Evento do botão "Reiniciar"
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarPrograma();
            }
        });
    }

    private void executarCalculos() {
        String input = inputField.getText(); // Lê o texto do campo de entrada
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String[] numerosString = input.split(" ");
            double[] numeros = new double[numerosString.length];

            for (int i = 0; i < numerosString.length; i++) {
                numeros[i] = Double.parseDouble(numerosString[i]);
            }

            // Usar a classe de cálculos
            CalculosEstatisticos calculos = new CalculosEstatisticos();
            String resultados = calculos.executarCalculos(numeros);

            // Atualizar a área de saída com os resultados
            outputArea.setText(resultados);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira apenas números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reiniciarPrograma() {
        // Limpa os campos de entrada e saída
        inputField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EstatisticaGUI frame = new EstatisticaGUI();
            frame.setVisible(true);
        });
    }
}
