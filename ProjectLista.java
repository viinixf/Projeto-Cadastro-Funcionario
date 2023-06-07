package miniprojectlista;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProjectLista {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> listaId = new ArrayList<>();
		int idRecebido = 0;

		System.out.println("Quantos funcionários serão registrados? ");
		int qtdFuncinariosRegistrados = sc.nextInt();
		Funcionario[] vetor = new Funcionario[qtdFuncinariosRegistrados];
		for (int i = 0; i < vetor.length; i++) {
			int aux = i + 1;
			System.out.println("Funcionário n° " + aux);
			System.out.println("");
			boolean rpt = true;
			while (rpt) {
				System.out.println("Digite o Id do funcionário:");
				int id = sc.nextInt();
				boolean validaSeIdEstaNaLista = listaId.stream().anyMatch(y -> y == id);
				if (validaSeIdEstaNaLista) {
					System.out.println("O ID já está cadastrado!");
					System.out.println("Lista de ID's que já estão sendo utilizados: ");
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
			String nome = sc.next();
			System.out.println("Digite o salário do funcionário: ");
			double salario = sc.nextDouble();
			vetor[i] = new Funcionario(idRecebido, nome, salario);
			System.out.println("----------------------------------");
		}
		boolean rpt = true;
		while (rpt) {
			System.out.println("Digite o ID do funcionário que irá receber o aumento: ");
			int idFuncionarioAumento = sc.nextInt();
			boolean validaId = listaId.stream().anyMatch(x -> x == idFuncionarioAumento);
			if (validaId) {
				System.out.println("Digite a porcentagem do aumento: ");
				double percent = sc.nextDouble();
				for (int i = 0; i < vetor.length; i++) {
					if (vetor[i].getId() == idFuncionarioAumento) {
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
		for (int i = 0; i < vetor.length; i++) {
			System.out.println(vetor[i]);
		}
	}
}
