package li1.plp.imperative1.command;

import li1.plp.expressions2.expression.Expressao;
import li1.plp.expressions2.expression.ValorBooleano;
import li1.plp.expressions2.memory.IdentificadorJaDeclaradoException;
import li1.plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;
import li1.plp.imperative1.memory.EntradaVaziaException;
import li1.plp.imperative1.memory.ErroTipoEntradaException;

public class Repeat implements Comando {
	private Comando comando;
	private Expressao expressao;

	public Repeat(Comando comando, Expressao expressao) {
		this.comando = comando;
		this.expressao = expressao;
	}

	/**
	 * Implementa o comando <code>repeat</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execucaoo.
	 * 
	 * @return o ambiente depois de modificado pela execucao do comando
	 *         <code>repeat</code>.
	 * @throws ErroTipoEntradaException 
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException, ErroTipoEntradaException {
		
		do {
			ambiente = comando.executar(ambiente);
		} while (((ValorBooleano) expressao.avaliar(ambiente)).valor());
		
		
		return ambiente;
	}

	/**
	 * Realiza a verificacao de tipos da expressao e dos comandos do comando
	 * <code>repeat</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compilacao.
	 * @return <code>true</code> se os comando sao bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return expressao.checaTipo(ambiente)
				&& expressao.getTipo(ambiente).eBooleano()
				&& comando.checaTipo(ambiente);
	}

}
