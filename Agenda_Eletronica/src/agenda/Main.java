package agenda;

//import para funcionamento 

import java.time.LocalDate;
import java.time.LocalTime;
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
				compromissosPassados(compromissos);
				break;
			case 7:
				buscaPorData(input, compromissos);
				break;
			case 6:
				listarTodosCompromissos(compromissos);
				break;
			case 8:
				System.out.println("**Encerrando a Agenda**");
				funcionamento = false;

				break;
			default:
				System.out.println("**Opção inválida. Tente novamente!**");
			}
		}
	}

	// Cadastrando o compromisso na agenda
	public static void cadastro(Scanner input, List<String[]> compromissos) {
		String loc, data, desc, hr;

		System.out.println("\nData do compromisso: [dd/mm/aaaa]:\n");
		data = input.nextLine();
		data = formatoData(input, data);

		System.out.println("\nDigite o horario: [HH:MM]:\n");
		hr = input.nextLine();
		hr = formatoHora(input, hr);

		// Verifica se já existe um compromisso com a mesma data e hora
		for (int i = 0; i < compromissos.size(); i++) {
			String[] compromisso = compromissos.get(i);

			// se já existir, ele exibe para o usuário e retorna na função
			if (compromisso[0].equals(data) && compromisso[1].equals(hr)) {
				System.out.println("\n **Já existe um compromisso com essa data e hora**\n");

				cadastro(input, compromissos); // Chama novamente o cadastro
				return; // Interrompe a execução da função atual para evitar continuar
			}
		}

		System.out.println("\nDigite a  descrição do compromisso:\n");
		desc = input.nextLine();

		System.out.println("\nDigite o local do compromisso: \n");
		loc = input.nextLine();
		String[] compromisso = { data, hr, desc, loc };
		compromissos.add(compromisso);

		// System.out.println("**Cadastrado**");

	}
	// Editando um compromisso da agenda

	public static void editar(Scanner input, List<String[]> compromissos) {
		listarTodosCompromissos(compromissos);
		String data, hr, desc, loc;
		int i;

		System.out.println("\nDigite o ID do compromisso que deseja editar:");
		i = input.nextInt();
		i = i - 1;

		if (i >= 0 && i < compromissos.size()) {
			input.nextLine(); // Consumir o \n que sobrou do nextInt()

			System.out.println("\nNova Data do compromisso: [dd/mm/aaaa] (deixe em branco não quiser alterar)\n");
			data = input.nextLine();
			if (data.isBlank()) {
				data = compromissos.get(i)[0]; // Mantém o valor anterior se for enviado um espaço em branco
			}
			data = formatoData(input, data);

			System.out.println("\nDigite o novo horário: [HH:MM] (deixe em branco não quiser alterar)\n");
			hr = input.nextLine();
			if (hr.isBlank()) {
				hr = compromissos.get(i)[1]; // Mantém o valor anterior se for enviado um espaço em branco
			}
			hr = formatoHora(input, hr);

			System.out.println("\nDigite a nova descrição do compromisso: (deixe em branco não quiser alterar)\n");
			desc = input.nextLine();
			if (desc.isBlank()) {
				desc = compromissos.get(i)[2]; // Mantém o valor anterior se for enviado um espaço em branco
			}

			System.out.println("\nDigite o novo local do compromisso: (deixe em branco não quiser alterar)\n");
			loc = input.nextLine();
			if (loc.isBlank()) {
				loc = compromissos.get(i)[3]; // Mantém o valor anterior se for enviado um espaço em branco
			}

			String[] compromisso = { data, hr, desc, loc };
			compromissos.set(i, compromisso);

		} else {
			System.out.println("\n **ID invalido, digite  novamente**\n");
			editar(input, compromissos);

		}

	}

	// Removendo um compromisso da agenda
	public static void remover(Scanner input, List<String[]> compromissos) {
		listarTodosCompromissos(compromissos);
		int i;
		System.out.println("\nDigite o ID do compromisso que deseja remover:\n");
		i = input.nextInt();
		i = i - 1;
		if (i >= 0 && i < compromissos.size()) {

			compromissos.remove(i);
		} else {
			System.out.println("\n **ID invalido, digite  novamente**\n");
			remover(input, compromissos);
		}
	}

	// Função para listar compromissos futuros 
	public static void compromissosFuturos(List<String[]> compromissos) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatohora = DateTimeFormatter.ofPattern("HH:mm");

		System.out.println("\nCompromissos futuros:\n");

		for (int i = 0; i < compromissos.size(); i++) {

			String[] compromissoIteracao = compromissos.get(i);

			LocalDate dataCompromissofuturoEncontrado = LocalDate.parse(compromissoIteracao[0], formatoData);
			LocalTime horaComprimissofuturoEncontrado = LocalTime.parse(compromissoIteracao[1], formatohora);
			// verifica se o compromisso da iteração atual é futuro e printa ele
			if (dataCompromissofuturoEncontrado.isAfter(LocalDate.now()) || dataCompromissofuturoEncontrado.isEqual(LocalDate.now()) && horaComprimissofuturoEncontrado.isAfter(LocalTime.now()))  {

				System.out.println("Data: " + compromissoIteracao[0] + " Hora: " + compromissoIteracao[1]
						+ " Descrição: " + compromissoIteracao[2] + " Local: " + compromissoIteracao[3]);

			}
		}
	}

	// faz a mesma coisa da função listar compromisso futuro porem muda a condição da data
	public static void compromissosPassados(List<String[]> compromissos) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatohora = DateTimeFormatter.ofPattern("HH:mm");

		System.out.println("\nCompromissos Passados:\n");

		for (int i = 0; i < compromissos.size(); i++) {

			String[] compromissoIteracao = compromissos.get(i);

			LocalDate dataCompromissoPassadoEncontrado = LocalDate.parse(compromissoIteracao[0], formatoData);
			LocalTime horaComprimissoPassadoEncontrado = LocalTime.parse(compromissoIteracao[1], formatohora);

			// alteração de verificação da data
			if (dataCompromissoPassadoEncontrado.isBefore(LocalDate.now())
					|| dataCompromissoPassadoEncontrado.isEqual(LocalDate.now())
							&& horaComprimissoPassadoEncontrado.isBefore(LocalTime.now())) {

				System.out.println("Data: " + compromissoIteracao[0] + " Hora: " + compromissoIteracao[1]
						+ " Descrição: " + compromissoIteracao[2] + " Local: " + compromissoIteracao[3]);

			}
		}
	}

	// Função para buscar data específica inserida pelo usuário e exibir em tela
	public static void buscaPorData(Scanner scanner, List<String[]> todosCompromissos) {

		// Solicitando valor
		System.out.println("\nDigite a data para busca (dd/mm/aaaa): \n");
		String dataBusca = scanner.nextLine();

		// verificando os compromissos com base na data
		for (int i = 0; i < todosCompromissos.size(); i++) {

			String[] compromissoAtual = todosCompromissos.get(i);
			String dataCompromissoAtual = compromissoAtual[0];

			// verifica se a data de busca é igual a data do "compromissoAtual"
			if (dataBusca.equals(dataCompromissoAtual)) {

				System.out.printf("Data: %s | Hora: %s | Descrição: %s | Local: %s%n", compromissoAtual[0],
						compromissoAtual[1], compromissoAtual[2], compromissoAtual[3]);

			}

		}

	}

	// Função para listar todos os compromissos 
	public static void listarTodosCompromissos(List<String[]> compromissos) {

		// verificando todos os compromissos com base nos cadastrados
		for (int i = 0; i < compromissos.size(); i++) {

			// pegando compromisso da iteração atual
			String[] compromissoAtual = compromissos.get(i);

			// exibindo compromissos cadastrados
			System.out.printf("\n%d. Data: %s | Hora: %s | Descrição: %s | Local: %s%n", i + 1, compromissoAtual[0],
					compromissoAtual[1], compromissoAtual[2], compromissoAtual[3]);

		}
	}


	// função que le os compromissos registrados em um arquivo ja existente na raiz
	// do codigo (no caso do nosso codigo, fica localizado na mesma pasta do codigo.

	// Base lista 9 Natan
	
	// coverte a string em um array

	public static void leituraDosDados(List<String[]> compromissos) {
 
		try (BufferedReader leitor = new BufferedReader(new FileReader("compromissos.txt"))) {
			String linha;
			while ((linha = leitor.readLine()) != null) {
				String[] compromisso = linha.split(",");
				compromissos.add(compromisso);
			}
			System.out.println("\n**Informações carregados com sucesso.**\n");
		} catch (IOException e) {
			System.out.println("\n**Nenhum registro encontrado.Tente novamente!** \n");
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
			System.out.println("\n **Informações registradas com sucesso.** \n");
		} catch (IOException e) {
			System.out.println("\n**Erro ao registrar as informações. Tente novamente!** \n");
		}
	}

	// função para formatar data (ser obrigatório preencher a quantidade de
	// "caracteres")
	public static String formatoData(Scanner input, String data) {
		if (data.matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {
			return data;
		} else {
			System.out.println("\n Digite a data no formato correto: \n");
			data = input.nextLine();
			return formatoData(input, data);
		}
	}

	// função para formato de hora
	public static String formatoHora(Scanner input, String hr) {
		if (hr.matches("\\d{2}\\:\\d{2}")) {
			return hr;
		} else {
			System.out.println("\n Digite a hora no formato correto: \n");
			hr = input.nextLine();
			return formatoHora(input, hr);
		}
	}
}
