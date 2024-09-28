package agenda;

//import para funcionamento 

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

				//Main da agenda
	
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
		        } 
		        
		        
		     