package io;

import modelo.Enfermaria;
import modelo.EnfermariaCuidadosIntensivos;
import modelo.EnfermariaGeral;
import modelo.EnfermariaPsiquiatrica;
import modelo.Episodio;
import modelo.Hospital;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela leitura e validação de ficheiros CSV.
 */
public class GestorFicheiros {

    /** Caminho do ficheiro de log. */
    private static final String FICHEIRO_LOG = "erros_validacao.log";

    /** Pasta de dados padrão. */
    private static final String PASTA_DADOS = "data";

    /** Capacidade mínima válida para uma enfermaria. */
    private static final int CAPACIDADE_MINIMA = 1;

    /**
     * Limpa o ficheiro de log.
     *
     * @throws IOException se ocorrer erro no acesso ao ficheiro
     */
    public static void limparLog() throws IOException {
        PrintWriter escritor = new PrintWriter(new FileWriter(FICHEIRO_LOG, false));
        escritor.close();
    }

    /**
     * Regista uma mensagem de erro no ficheiro de log.
     *
     * @param mensagem mensagem de erro
     * @throws IOException se ocorrer erro na escrita
     */
    private static void logErro(String mensagem) throws IOException {
        PrintWriter escritor = new PrintWriter(new FileWriter(FICHEIRO_LOG, true));
        escritor.println("[ERRO] " + LocalDate.now() + ": " + mensagem);
        escritor.close();
    }

    /**
     * Resolve um caminho para ficheiro.
     *
     * @param caminho caminho pedido
     * @return ficheiro resolvido
     */
    private static File resolverFicheiro(String caminho) {
        File direto = new File(caminho);
        if (direto.exists()) {
            return direto;
        }
        return new File(PASTA_DADOS, caminho);
    }
