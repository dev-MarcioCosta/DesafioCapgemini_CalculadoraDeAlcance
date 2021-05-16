package views;

import entities.Anuncio;
import java.text.ParseException;
import java.util.Scanner;

public class Views {

    Scanner sc = new Scanner(System.in);
    
    Anuncio anuncio = new Anuncio();

    public void telaInicial() throws ParseException {
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("====================================================");
            System.out.println("=__________________________________________________=");
            System.out.println("=              CALCULADORA DE ALCANCE              =");
            System.out.println("=              CAPGEMINI - SINCE 1967              =");
            System.out.println("=______________________*****_______________________=");
            System.out.println("=                                                  =");
            System.out.println("=                                                  =");
            System.out.println("=  [1] Cadastrar um Novo Anúncio                   =");
            System.out.println("=  [2] Exibir Relatórios                           =");
            System.out.println("=  [3] EXIT                                        =");
            System.out.println("=                                                  =");
            System.out.println("=                      ______                      =");
            System.out.println("====================================================");
            System.out.print("Digite o número da sua opção escolhida: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    anuncio.cadastrar();
                    break;
                case 2:
                    System.out.println("====================================================");
                    System.out.println("=__________________________________________________=");
                    System.out.println("=              CALCULADORA DE ALCANCE              =");
                    System.out.println("=              CAPGEMINI - SINCE 1967              =");
                    System.out.println("=______________________*****_______________________=");
                    System.out.println("=                    Relatório                     =");
                    System.out.println("=                                                  =");
                    System.out.println("=  [1] Filtrar por Data                            =");
                    System.out.println("=  [2] Filtrar por Cliente                         =");
                    System.out.println("=                                                  =");
                    System.out.println("=                                                  =");
                    System.out.println("=                      ______                      =");
                    System.out.println("====================================================");
                    System.out.print("Digite o número da sua opção escolhida: ");
                    int opcaoFiltro = sc.nextInt();
                    
                    switch(opcaoFiltro){
                        case 1:
                        	anuncio.filtroData();
                            break;
                        case 2:
                            anuncio.filtroCliente();
                            break;
                        default: System.out.println("Opção Inválida");
                    }
                    
                    break;
                case 3:
                    break;
                default: System.out.println("Opção Inválida");
                
            }

        }
        System.out.println("Aplicação finalizada!");

    }

}
