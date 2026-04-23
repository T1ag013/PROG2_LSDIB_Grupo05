package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *Representa uma enfermaria geral, com suporte a acompanhantes e lista de recursos disponíveis.
 *Estende {@link Enfermaria} com atributos específicos de uma unidade geral.
 */
public class EnfermariaGeral extends Enfermaria {

  /** Número máximo de acompanhantes permitidos por paciente. */
  private int limiteAcompanhantes;
  
  /** Horário de visitas da enfermaria. */
  private String horarioVisitas;

  /** Lista de recursos disponiveis na enfermaria. */
  private List<String> recursosDisponiveis;

  /**
   * Cria uma nova enfermaria geral.
   *
   * @param id                  identificador único de enfermaria
   * @param camas               número toal de camas
   * @param limiteAcompanhantes número máximo de acompanhantes por paciente
   * @param horarioVisitas      hor+ário de visitas permitido
   */
  public EnfermariaGeral(String id, int camas, int limiteAcompanhantes, String horarioVisitas) {
    super (id, camas);
    this.limiteAcompanhantes = limiteAcompanhantes;
    this.horarioVisitas = horarioVisitas;
    this.recursosDisponiveis = new ArrayList<>();
  }

  /**
   * Adiciona um recurso à lista de recursos disponíveis na enfermaria.
   * Ignora valores nulos ou me branco.
   *
   * @param recurso nome do recurso a adicionar
   */
  public void adicionarRecurso(String recurso){
    if (recurso != null && !recurso.isBlank()) {
      recursosDisponiveis.add(recurso);
    }
  }
  
  /**
   * Remove um recurso da linha de recursos disponíveis.
   * 
   * @param recurso nome do recurso a remover
   * @return {@code true} se removido com sucesso, {@code false} se não existia
   */
  public boolean removerRecurso(String recurso){
    return recursosDisponieis.remove(recurso);
  }
}
