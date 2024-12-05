package Estatistica;

import java.util.*;

public class CalculosEstatisticos {

    public String executarCalculos(double[] numeros) {
        Arrays.sort(numeros);

        double media = calcularMedia(numeros);
        double mediana = calcularMediana(numeros);
        double variancia = calcularVariancia(numeros, media);
        double desvioPadrao = Math.sqrt(variancia);
        double coeficienteVariacao = (desvioPadrao / media) * 100;
        double primeiroQuartil = calcularQuartil(numeros, 0.25);
        double terceiroQuartil = calcularQuartil(numeros, 0.75);

        // Montar os resultados como string
        StringBuilder resultados = new StringBuilder();
        resultados.append("Números ordenados: ").append(Arrays.toString(numeros)).append("\n");
        resultados.append("Média: ").append(media).append("\n");
        resultados.append("Mediana: ").append(mediana).append("\n");
        resultados.append("Variância: ").append(variancia).append("\n");
        resultados.append("Desvio Padrão: ").append(desvioPadrao).append("\n");
        resultados.append("Coeficiente de Variação: ").append(coeficienteVariacao).append("%\n");
        resultados.append("Primeiro Quartil: ").append(primeiroQuartil).append("\n");
        resultados.append("Terceiro Quartil: ").append(terceiroQuartil).append("\n");

        return resultados.toString();
    }

    private double calcularMedia(double[] numeros) {
        double somaTotal = 0;
        for (double numero : numeros) {
            somaTotal += numero;
        }
        return somaTotal / numeros.length;
    }

    private double calcularMediana(double[] numeros) {
        int meio = numeros.length / 2;
        if (numeros.length % 2 == 0) {
            return (numeros[meio - 1] + numeros[meio]) / 2.0;
        } else {
            return numeros[meio];
        }
    }

    private double calcularVariancia(double[] numeros, double media) {
        double somaNumeros = 0.0;
        for (double numero : numeros) {
            somaNumeros += Math.pow(numero - media, 2);
        }
        return somaNumeros / (numeros.length - 1);
    }

    private double calcularQuartil(double[] numeros, double percentil) {
        double indice = (numeros.length) * percentil;
        if (indice % 1 == 0 || indice % 2 == 0) {
            return (numeros[(int) indice - 1] + numeros[(int) indice]) / 2;
        } else {
            return numeros[(int) indice];
        }
    }
}
