package agenda;

//import para funcionamento 

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	// Main da agenda

	public static void main(String[] args) {
		    
		    	// criação do scanner coletor para aplicar as opções
		    	
		    	
		    	List<String[]> compromissos = new ArrayList<>();
		    	
		    	Scanner coletor = new Scanner(System.in);
		    	
		    			        
		        // analisa os dados ja existentes
		        
		        leituraDosDados(compromissos);
		        
		        // variaveis de funcionamento e coleta de opções 
		        int opcoes;
		        boolean funcionamento;
		        funcionamento = true;
		        

		        while(funcionamento==true) {
		        	System.out.println("\n************ AGENDA ************\n");
		        	System.out.println("1 - Cadastrar novo compromisso");
		            System.out.println("2 - Editar um compromisso");
		            System.out.println("3 - Remover um ompromisso");
		            System.out.println("4 - Listar compromissos futuros");
		            System.out.println("5 - Buscar Compromissos (DATA/HORA");
		            System.out.println("6 - Encerrar AGENDA");
		            System.out.println("*******************************");
		            opcoes = coletor.nextInt();
		            
		            //pula a linha 
		            coletor.nextLine();  

		            switch (opcoes) {
		                case 1:
		                	cadastro(coletor, compromissos);
		                    registrarCompromissos(compromissos);
		                    break;
		                case 2:
		                	editar(coletor, compromissos);
		                    registrarCompromissos(compromissos);
		                    break;
		                case 3:
		                	remover(coletor, compromissos);
		                    registrarCompromissos(compromissos);
		                    break;
		                case 4:
		                	compromissosFuturos(compromissos);
		                    break;
		                case 5:
		                	buscaPorData(coletor, compromissos);
		                    break;
		                case 6:
		                    System.out.println("Encerrando a Agenda");
		                    funcionamento = false;
		                    
		                    break;
		                default:
		                    System.out.println("Opção inválida. Tente novamente!");
		            }

	// Cadastrando o compromisso na agenda
	public static void cadastro(List<String[]> compromissos) {
		Scanner input = new Scanner(System.in);
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

	public static void editar(List<String[]> compromissos) {
		Scanner input = new Scanner(System.in);
		String data, hr, desc, loc;
		int i;
		;
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
	public static void remover(List<String[]> compromissos) {
		Scanner input = new Scanner(System.in);
		int i;
		System.out.println("Digite a posição do compromisso que deseja remover:");
		i = input.nextInt();
		i = i - 1;
		if (i >= 0 && i < compromissos.size())
			;
		compromissos.remove(i);

	}

	// Função para listar eventos futuros - ABNER
	public static void compromissosFuturos(List<String[]> compromissos) {
		System.out.println("Compromissos futuros:");

		for (String[] compromisso : compromissos) {
			LocalDate dataCompromisso = LocalDate.parse(compromisso[0], formatoData);
			if (dataCompromisso.isAfter(LocalDate.now())) {
				System.out.println("Data: " + compromisso[0] + " Hora: " + compromisso[1] + " Descrição: "
						+ compromisso[2] + " Local: " + compromisso[3]);
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
}
