package exercicios_metodos_vetores;

import java.util.Scanner;

class Main {

 static Scanner console = new Scanner(System.in);

 static final int TOTAL_AVALIACOES = 3;
 static final String[] NOMES_AVALIACOES = { "A1", "A2", "A3" };
 static final double[] NOTA_MAX_AVALIACOES = { 30.00, 30.00, 40.00 };
 
 static double[] notas = new double [TOTAL_AVALIACOES];
 static double notaFinal = 0.0;

 
 /**
         * Ler uma nota do usuário
         * @param mensagem O texto que aparecerá na tela
         * @return um número double representando a nota.
         */
        static double lerNota(String mensagem, double notaMaxima) {

     double nota = 0.0;

     do {

       System.out.printf("%s = ", mensagem);
       nota = console.nextDouble();
       
     } while (nota < 0.00 || nota > notaMaxima);

   return nota;
        }

 
 /**
 * substituir a menor nota e mostrar na tela a situação final do aluno
 * @param indiceNota um número inteiro representando o índice (posição) da nota no vetor
 */
 static void substituirMenorNota(double[] notas) {
	 int menor = Integer.MAX_VALUE;
 	 int indiceMenor = -1;
  
 	 for (int i = 0; i < notas.length; i++) {
 	     if (notas[i] < menor) {
 	    	menor = (int) notas[i];
 	        indiceMenor = i;
 	     }
 	 }
   System.out.println();
   notas[indiceMenor] = lerNota(NOMES_AVALIACOES[indiceMenor], NOTA_MAX_AVALIACOES[indiceMenor]);
 
 } // Fim do método atualizarNota

 /**
 * Atualiza o valor da respectiva nota do estudante
 * @param indiceNota um número inteiro representando o índice (posição) da nota no vetor
 */
 static void atualizarNota(int indiceNota) {

   System.out.println();
   notas[indiceNota] = lerNota(NOMES_AVALIACOES[indiceNota], NOTA_MAX_AVALIACOES[indiceNota]);
 
 } // Fim do método atualizarNota
 
 
/**
 * funcionalidade que leia do usuário a nota da Avaliação Integrada (AI) 
 * @return uma string representando o status final do estudante, são eles: APROVADO, REPROVADO, EM RECUPERAÇÃO.
 */
 static void lerAvaliacaoIntegrada() {

 	 if (avaliarSituacao(notaFinal).equals(StatusEnum.EM_RECUPERAÇÃO.getStatus())) {
 		   substituirMenorNota(notas);
		   System.out.printf("\n    Situação = %s após a Avaliação Integrada (AI)", avaliarSituacao(notaFinal));   
 	 }
 } // Fim do método lerAvaliacaoIntegrada()

 public enum StatusEnum {
	 
	 REPROVADO("REPROVADO"),
	 EM_RECUPERAÇÃO("EM RECUPERAÇÃO"), 
	 APROVADO("APROVADO");

	 private String status;

	 StatusEnum(String descricao) {
	    this.status = descricao;
	 }

	 public String getStatus() {
	     return status;
	 }
}
 
 /**
  * @param notaFinal A soma de todas as avalições feita pelo estudante ao longo do semestre
  * @return uma string representando o status final do estudante, são eles: APROVADO, REPROVADO, EM RECUPERAÇÃO.
  */
  static String avaliarSituacao(double notaFinal) {

    if(notaFinal < 30)
      return StatusEnum.REPROVADO.getStatus();
    else if (notaFinal < 70)
      return StatusEnum.EM_RECUPERAÇÃO.getStatus();
    else
      return StatusEnum.APROVADO.getStatus();
    
  } // Fim do método avaliarSituacao()

 /**
 * Mostra na tela um relatório das notas do estudante
 */
  
 static void mostrarNotas() {


   System.out.println("\n\t\tNOTAS");
   System.out.println();

   for (int i = 0; i < TOTAL_AVALIACOES; i++) {

     System.out.printf("Avaliação %s = %.2f pts", NOMES_AVALIACOES[i], notas[i]);
     System.out.println();
     notaFinal += notas[i];
   
   }

   System.out.printf("\n    Nota Final = %.2f pts", notaFinal);
   System.out.printf("\n    Situação = %s", avaliarSituacao(notaFinal));
   calcularMedia(notas);
   System.out.printf("\n    %s",maiorNota(notas));
   lerAvaliacaoIntegrada();

 } // Fim do método mostrarNotas()


  /**
  * Mostra na tela a nota aritimética
  */
 static void calcularMedia(double[] notas) {
     int media = 0;
     for(int i = 0; i < notas.length; i++)
         media += notas[i];

     int total =  media / notas.length;

 	System.out.printf("\n    Média Aritimética = " + total);
 }// Fim do método calcularMedia()


 /**
  * @param o resultado das 3 avaliações feitas pelo estudante
  * @return uma string com o nome da maior nota dele no semestre dentre as avaliações
  */
  static String maiorNota(double[] notas) {
 	 
 	 int maior = Integer.MIN_VALUE;
 	 int indiceMaior = -1;
 	 String avaliacao = "";
  
 	 for (int i = 0; i < notas.length; i++) {
 	     if (notas[i] > maior) {
 	         maior = (int) notas[i];
 	         indiceMaior = i;
 	     }
 	 }
 	 switch (indiceMaior) {
 	  case 0:
 	    avaliacao = "A1";
 	    break;
 	  case 1:
 		 avaliacao = "A2";
 	    break;
 	  case 2:
 		 avaliacao = "A3";
 	    break;
 	  default:
 		 avaliacao = "Sem avaliação ";
 	 }
 	 
 	 return "A maior nota do semestre é " + maior + " na avaliação " + avaliacao;

    
  } // Fim do método maiorNota()

/**
 * Exibe o menu principal da aplicação
 */
 static void mostrarMenu() {

   System.out.println("\n\n");
   System.out.println("\t\tMENU");
   System.out.println();
   
   System.out.println("[1] Cadastrar Notas A1");
   System.out.println("[2] Cadastrar Nota A2");
   System.out.println("[3] Cadastrar Nota A3");
   System.out.println("[4] Mostrar Notas");
   System.out.println("[0] SAIR");

   System.out.print("\nDigite uma opção:  ");
   byte opcao = console.nextByte();


   switch(opcao) {

     case 0:
       System.exit(0);
       break;
     
     case 1:
       atualizarNota(0);
       break;
     
     case 2:
       atualizarNota(1);
       break;

     case 3:
       atualizarNota(2);
       break;

     case 4:
       mostrarNotas();
       break;

     default:
       mostrarMenu();
       break;

   }

   mostrarMenu();

 } // Fim do método mostrarMenu()

 
 public static void main(String[] args) {
   
   mostrarMenu();
 
 } // Fim do método main();
 
} // Fim da classe Main