package miniprojectlista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectLista {
	public static void main(String[] args) {
		// Scanner para entrada de dados do usuário
		Scanner entradaDeDados = new Scanner(System.in);
		// Lista que armazena os id's cadastrados
		List<Integer> listaId = new ArrayList<>();
		// Variável que armazena o id recebido para poder usá-lo de forma global
		int idRecebido = 0;
		System.out.println("Quantos funcionários serão registrados? ");
		int qtdFuncinariosRegistrados = entradaDeDados.nextInt();
		// Vetor que armazenará os dados do funcionário
		Funcionario[] vetor = new Funcionario[qtdFuncinariosRegistrados];
		// Percorre o vetor de acordo com o seu tamanho e cadastra os funcionários
		for (int i = 0; i < vetor.length; i++) {
			int nroDoFuncionario = i + 1;
			System.out.println("Funcionário n° " + nroDoFuncionario);
			System.out.println("");
			boolean rpt = true;
			/*
			 * Laço de repetição não determinado. Enquanto não digitar um id válido o
			 * usuário não conseguirá dar continuidade no cadastro
			 */
			while (rpt) {
				System.out.println("Digite o Id do funcionário:");
				int id = entradaDeDados.nextInt();
				/*
				 * Expressão lambda que percorre a lista de id e identifica se o Id digitado
				 * pelo usuário já está cadastrado
				 */
				boolean validaSeIdEstaNaLista = listaId.stream().anyMatch(y -> y == id);
				/*
				 * Se a expressão ser verdadeira, o id já está sendo utilizado e cairá na
				 * primeira condição, dessa forma, retorna ao início do laço
				 */
				if (validaSeIdEstaNaLista) {
					System.out.println("O ID já está cadastrado!");
					System.out.println("Lista de ID's que já estão sendo utilizados: ");
					/*
					 * For each que percorre a lista de id's já cadastrados e mostra para o usuário
					 * quais não podem ser utilizados
					 */
					for (Integer idUtilizado : listaId) {
						System.out.println(idUtilizado);
					}
					System.out.println("");
					continue;
				} else {
					idRecebido = id;
					listaId.add(id);
					break;
				}
			}
			System.out.println("Digite o nome do funcionário: ");
			String nome = entradaDeDados.next();
			System.out.println("Digite o salário do funcionário: ");
			double salario = entradaDeDados.nextDouble();
			/*
			 * Armazena no índice do vetor um novo funcionário, passando os dados recebidos
			 * pelo usuário no parâmetro do construtor
			 */
			vetor[i] = new Funcionario(idRecebido, nome, salario);
			System.out.println("----------------------------------");
		}
		// Laço de repetição que executa até o usuário digitar um Id que existe.
		boolean rpt = true;
		while (rpt) {
			System.out.println("Digite o ID do funcionário que irá receber o aumento: ");
			int idFuncionarioAumento = entradaDeDados.nextInt();
			// Expressão lambda que valida se o id consta na lista de id's
			boolean validaId = listaId.stream().anyMatch(x -> x == idFuncionarioAumento);
			if (validaId) {
				System.out.println("Digite a porcentagem do aumento: ");
				double percent = entradaDeDados.nextDouble();
				/*
				 * Percorre os funcionários no vetor até identificar o id do funcionário que
				 * receberá o aumento.
				 */
				for (int i = 0; i < vetor.length; i++) {
					if (vetor[i].getId() == idFuncionarioAumento) {
						// Método responsável por aumentar salário
						vetor[i].aumentaSalario(percent);
					}
				}
				break;
			} else {
				System.out.println("O ID digitado não existe. Tente novamente.");
				continue;
			}
		}

		System.out.println("Lista de funcionários: ");
		// Percorre o vetor e printa os funcionários
		for (int i = 0; i < vetor.length; i++) {
			System.out.println(vetor[i]);
		}
	}
}
