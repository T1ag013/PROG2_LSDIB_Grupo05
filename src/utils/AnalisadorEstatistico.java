package utils;

import modelo.Enfermaria;

import java.time.LocalDate;
import java.util.List;

/**
 * Fornece cálculos estatísticos simples.
 */
public class AnalisadorEstatistico {

    /**
     * Representa um resumo estatístico do LoS.
     */
    public static class SumarioLoS {

        /** Número de episódios considerados. */
        private int totalEpisodios;

        /** Média do LoS. */
        private double media;

        /** Desvio Padrão do LoS. */
        private double desvioPadrao;

        /** Valor mínimo do LoS. */
        private long minimo;

        /** Valor máximo do LoS. */
        private long maximo;

        /**
         * Cria um resumo de LoS.
         *
         * @param totalEpisodios número de episódios
         * @param media média
         * @param desvioPadrao desvio padrão
         * @param minimo mínimo
         * @param maximo máximo
         */
        public SumarioLoS(int totalEpisodios, double media, double desvioPadrao, long minimo, long maximo) {
            this.totalEpisodios = totalEpisodios;
            this.media = media;
            this.desvioPadrao = desvioPadrao;
            this.minimo = minimo;
            this.maximo = maximo;
        }

        /**
         * Devolve o número de epis´dios.
         *
         * @return número de episódios
         */
        public int getTotalEpisodios() {
            return totalEpisodios;
        }

        /**
         * Devolve a média.
         *
         * @return média
         */
        public double getMedia() {
            return media;
        }

        /**
         * Devolve o desvio padrão.
         *
         * @return desvio padrão
         */
        public double getDesvioPadrao() {
            return desvioPadrao;
        }

        /**
         * Devolve o mínimo.
         *
         * @return mínimo
         */
        public long getMinimo() {
            return minimo;
        }

        /**
         * Devolve o máximo.
         *
         * @return máximo
         */
        public long getMaximo() {
            return maximo;
        }

        @Override
        public String toString(){
            if (totalEpisodios == 0){
                return "Sem episódios com alta";
            }
            return String.format("n=%d | media=%.2f | dp=%.2f | min=%d | max=%d",
                    totalEpisodios, media, desvioPadrao, minimo, maximo);
        }
    }

    /**
     * Calcula estatística de LoS de uma enfermaria.
     *
     * @param enfermaria enfermaria a analisar
     * @return resumo estatístico
     */
    public static SumarioLoS calculasEstatisticaLoS(Enfermaria enfermaria) {
        List<Long> valores = enfermaria.getValoresLoS();
        if (valores.isEmpty()) {
            return new SumarioLoS(0, 0.0, 0.0, 0, 0);
        }

        long minimo = valores.get(0);
        long maximo = valores.get(0);
        double soma = 0.0;
        for (Long valor : valores) {
            soma += valor;
            if (valor < minimo) minimo = valor;
            if (valor > maximo) maximo = valor;
        }

        double media = soma / valores.size();
        double somaQuadrados = 0.0;
        for (Long valor : valores) {
            double diferenca = valor - media;
            somaQuadrados += diferenca * diferenca;
        }
        double desvioPadrao = Math.sqrt(somaQuadrados / valores.size());

        return new SumarioLoS(valores.size(), media, desvioPadrao, minimo, maximo);
    }
}

