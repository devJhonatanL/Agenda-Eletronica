package agenda;

//import para funcionamento 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	

	// Main da agenda

	public static void main(String[] args) {
		List<String[]> compromissos = new ArrayList<>();
		// criação do scanner coletor para aplicar as opções

		Scanner input = new Scanner(System.in);

		// analisa os dados ja existentes

		leituraDosDados(compromissos);

		// variaveis de funcionamento e coleta de opções
		
		int escolhaDoUsuario;
		boolean funcionamento;
		funcionamento = true;

		while (funcionamento == true) {
			System.out.println("\n************ AGENDA ************\n");
			System.out.println("1 - Cadastrar um novo compromisso");
			System.out.println("2 - Editar um compromisso");
			System.out.println("3 - Remover um compromisso");
			System.out.println("4 - Listar compromissos futuros");
			System.out.println("5 - Listar compromissos passados");
			System.out.println("6 - Listar todos os compromissos");
			System.out.println("7 - Buscar compromissos (DATA)");
			System.out.println("8 - Encerrar AGENDA");
			System.out.println("\n*******************************\n");
			escolhaDoUsuario = input.nextInt();

			// pula a linha
			input.nextLine();

			switch (escolhaDoUsuario) {
			case 1:
				cadastro(input, compromissos);
				registrarCompromissos(compromissos);
				break;
			case 2:
				editar(input, compromissos);
				registrarCompromissos(compromissos);
				break;
			case 3:
				remover(input, compromissos);
				registrarCompromissos(compromissos);
				break;
			case 4:
				compromissosFuturos(compromissos);
				break;
			case 5:
				buscaPorData(input, compromissos);
				break;
			case 8:
				System.out.println("Encerrando a Agenda");
				funcionamento = false;

				break;
			default:
				System.out.println("Opção inválida. Tente novamente!");
			}
		}
	}

	// Cadastrando o compromisso na agenda
	public static void cadastro(Scanner input, List<String[]> compromissos) {
		String loc, data, desc, hr;

		System.out.println("Data do compromisso: [dd/mm/aaaa]");
		data = input.nextLine();

		System.out.println("Digite o horario: [HH:MM]");
		hr = input.nextLine();

		System.out.println("Digite a  descrição do compromisso:");
		desc = input.nextLine();

		System.out.println("Digite o local do compromisso: ");
		loc = input.nextLine();
		String[] compromisso = { data, hr, desc, loc };
		compromissos.add(compromisso);

		System.out.println("Cadastrado");

	}
	// Editando um compromisso da agenda

	public static void editar(Scanner input, List<String[]> compromissos) {
		listarTodosCompromissos(compromissos);
		String data, hr, desc, loc;
		int i;

		System.out.println("Digite a posição do compromisso que deseja editar:");
		i = input.nextInt();
		i = i - 1;
		if (i >= 0 && i < compromissos.size()) {
			System.out.println("Nova Data do compromisso: [dd/mm/aaaa]");
			data = input.nextLine();

			System.out.println("Digite o novo horario: [HH:MM]");
			hr = input.nextLine();

			System.out.println("Digite a nova descrição do compromisso:");
			desc = input.nextLine();

			System.out.println("Digite o novo local do compromisso: ");
			loc = input.nextLine();
			String[] compromisso = { data, hr, desc, loc };
			compromissos.set(i, compromisso);

		}
	}

	// Removendo um compromisso da agenda
	public static void remover(Scanner input, List<String[]> compromissos) {
		listarTodosCompromissos(compromissos);
		int i;
		System.out.println("Digite a posição do compromisso que deseja remover:");
		i = input.nextInt();
		i = i - 1;
		if (i >= 0 && i < compromissos.size())
			;
		compromissos.remove(i);

	}

	// Função para listar compromissos futuros - ABNER
	public static void compromissosFuturos(List<String[]> TodosCompromissos) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Compromissos futuros:");

		for (int i = 0; i < TodosCompromissos.size(); i++) {

			String[] compromissoIteracao = TodosCompromissos.get(i);

			LocalDate dataCompromissofuturoEncontrado = LocalDate.parse(compromissoIteracao[0], formatoData);

			// verifica se o compromisso da iteração atual é futuro e printa ele
			if (dataCompromissofuturoEncontrado.isAfter(LocalDate.now())) {

				System.out.println("Data: " + compromissoIteracao[0] + " Hora: " + compromissoIteracao[1]
						+ " Descrição: " + compromissoIteracao[2] + " Local: " + compromissoIteracao[3]);

			}
		}
	}

	// Função para buscar data específica inserida pelo usuário e exibir em tela
	public static void buscaPorData(Scanner scanner, List<String[]> todosCompromissos) {

		// Solicitando valor
		System.out.println("Digite a data para busca (dd/mm/aaaa): ");
		String dataFuturaBusca = scanner.nextLine();

		// verificando os compromissos com base na data
		for (int i = 0; i < todosCompromissos.size(); i++) {

			String[] compromissoAtual = todosCompromissos.get(i);
			String dataCompromissoAtual = compromissoAtual[0];

			// verifica se a data de busca é igual a data do "compromissoAtual"
			if (dataFuturaBusca.equals(dataCompromissoAtual)) {

				System.out.printf("Data: %s | Hora: %s | Descrição: %s | Local: %s%n", compromissoAtual[0],
						compromissoAtual[1], compromissoAtual[2], compromissoAtual[3]);

			}
		}
	}

	// Função para listar todos os compromissos - Abner
	public static void listarTodosCompromissos(List<String[]> todosCompromissos) {

		// verificando todos os compromissos com base nos cadastrados
		for (int i = 0; i < todosCompromissos.size(); i++) {

			// pegando compromisso da iteração atual
			String[] compromissoAtual = todosCompromissos.get(i);

			// exibindo compromissos cadastrados
			System.out.printf("%d. Data: %s | Hora: %s | Descrição: %s | Local: %s%n", i, compromissoAtual[0],
					compromissoAtual[1], compromissoAtual[2], compromissoAtual[3]);

		}
	}

	// Jhonatan

	// função que le os compromissos registrados em um arquivo ja existente na raiz
	// do codigo (no caso do nosso codigo, fica localizado na mesma pasta do codigo.

	// Base lista 9 Natan

	public static void leituraDosDados(List<String[]> compromissos) {

		try (BufferedReader leitor = new BufferedReader(new FileReader("compromissos.txt"))) {
			String linha;
			while ((linha = leitor.readLine()) != null) {
				String[] compromisso = linha.split(",");
				compromissos.add(compromisso);
			}
			System.out.println("Informações carregados com sucesso.");
		} catch (IOException e) {
			System.out.println("Nenhum registro encontrado.Tente novamente!");
		}
	}

	// Função para salvar os compromissos no arquivo criado ou ja existente.

	// Base lista 9 Natan

	public static void registrarCompromissos(List<String[]> compromissos) {

		// cria um leitor para ler o arqurivo compromissos
		try (FileWriter registrador = new FileWriter("compromissos.txt")) {
			for (int i = 0; i < compromissos.size(); i++) {
				String[] compromisso = compromissos.get(i);
				registrador.write(String.join(",", compromisso));
				registrador.write(System.lineSeparator());
			}
			System.out.println("Informações registradas com sucesso.");
		} catch (IOException e) {
			System.out.println("Erro ao registrar as informações. Tente novamente!");
		}

	}

}
