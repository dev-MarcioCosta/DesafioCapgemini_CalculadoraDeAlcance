package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Anuncio {

	Scanner sc = new Scanner(System.in);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private String cliente;
	private String anuncio;
	private Date inicio;
	private Date termino;
	private double investimento;
	private ArrayList<Anuncio> anuncios = new ArrayList<>();

	public Anuncio() {
	}

	public Anuncio(String cliente, String anuncio, Date inicio, Date termino, double investimento) {
		this.cliente = cliente;
		this.anuncio = anuncio;
		this.inicio = inicio;
		this.termino = termino;
		this.investimento = investimento;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(String anuncio) {
		this.anuncio = anuncio;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public double getInvestimento() {
		return investimento;
	}

	public void setInvestimento(double investimento) {
		this.investimento = investimento;
	}

	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}

	public String relatorio() {
		long qtdDias = qtdDias(inicio, termino);
		
		return "\nDetalhes do Cadastro"
        + "\nAnúncio: " + anuncio
        + "\nCliente: " + cliente
        + "\nPeriodo do Anuncio: " + sdf.format(inicio) + " - " + sdf.format(termino)
        + "\nTotal de Dias: " + qtdDias
        + "\nInvestimento Diário: " + investimento
        + ""
        + "\nValor total investido: " + qtdDias * investimento
        + "\nQauntidade Máxima de Vizualizações no Anúncio original " + viewsAnuncioOriginal(investimento, qtdDias)
        + "\nQuantidade Máxima de Vizualizações no total: " + totalViews(qtdViewsShares(qtdShares(qtdCliks(viewsAnuncioOriginal(investimento, qtdDias)))), viewsAnuncioOriginal(investimento, qtdDias))
        + "\nQuantidade Máxima de Cliques: " + qtdCliks(viewsAnuncioOriginal(investimento, qtdDias))
        + "\nQuantidae  Máxima de Compartilhamentos: " +  qtdShares(qtdCliks(viewsAnuncioOriginal(investimento, qtdDias)));
}

	
	public void cadastrar() throws ParseException {

		System.out.println("Preencha os dados solicitados abaixo: ");
		System.out.println("");
		System.out.print("Nome do Cliente: ");
		String nomeCliente = sc.nextLine();
		System.out.print("Nome do Anúncio: ");
		String nomeAnuncio = sc.nextLine();

		Date dataInicio;
		
		String dataSistema = sdf.format(new Date());
		
		do { //bloco que permite somente inserção de datas iguais ou posteriores em relação à data do sistema
			System.out.print("Data de Início do Anúncio (dd/MM/yyyy): ");
			dataInicio = sdf.parse(sc.nextLine());
		}while(dataInicio.before(sdf.parse(dataSistema)));

		
		Date dataTermino;
		
		do{ //bloco que permite somente inserção de datas posteriores em relação à data de início.
			System.out.print("Data de Término do Anúncio (dd/MM/yyyy): ");
			dataTermino = sdf.parse(sc.nextLine());
		}while(dataTermino.before(dataInicio) || dataTermino.equals(dataInicio));
		

		double valorInvestimento = 0;
		
		do { //bloco que permite somente investimento => à R$1.00
			System.out.print("Informe um Valor de Investimento diário >= 1: ");
			valorInvestimento = sc.nextDouble();
		}while(valorInvestimento < 1);

		anuncios.add(new Anuncio(nomeCliente.toUpperCase(), nomeAnuncio, dataInicio, dataTermino, valorInvestimento));

		System.out.println("\nAnúncio Cadastro com Sucesso! \nPressione ENTER para continuar...");
		sc.nextLine();
		sc.nextLine();
	}

	public void filtroData() throws ParseException {

		ArrayList<Anuncio> anunciosAfter = new ArrayList<>();
		boolean contemDataInicio = false;
		boolean contemDataTermino = false;
		
		System.out.print("Informe a Data de Início: ");
		Date dataInicio = sdf.parse(sc.nextLine());

		System.out.print("Informe a Data de Termino: ");
		Date dataTermino = sdf.parse(sc.nextLine());
		
		for (Anuncio a : anuncios) {
			if (a.getInicio().equals(dataInicio) || a.getInicio().after(dataInicio)) {
				anunciosAfter.add(a);
				contemDataInicio = true;
			}
		}
			for (Anuncio b : anunciosAfter) {
				if (b.getTermino().equals(dataTermino) || b.getTermino().before(dataTermino)) {
					System.out.println(b.relatorio());
				contemDataTermino = true;
			}
		}
			if(contemDataInicio == false || contemDataTermino == false) {
				System.out.println("\nNenhum resultado encontrado para os intervalos de datas informados");
			}
			
			System.out.println("\nPressione ENTER para continuar...");
			sc.nextLine();
	}
	
	

	public void filtroCliente() {
		System.out.print("Nome do Cliente: ");
		String nomeCliente = sc.nextLine();

		boolean contem = false;
	
			for (Anuncio p : anuncios) {

				if (p.getCliente().contains(nomeCliente.toUpperCase())) {
					System.out.println(p.relatorio());
					contem = true;
			}
		}

		if (contem == false) {
			System.out.println("\nNenhum resultado encontrado para: " + nomeCliente);
		}

		System.out.println("\nPressione ENTER para continuar...");
		sc.nextLine();
	}

	private long qtdDias(Date dataInicio, Date dataTermino) {

		return (dataTermino.getTime() - dataInicio.getTime()) / 3600000 / 24;

	}

	// CALCULADORA
	
	/*
	 * Função que retorna a quantidade de vizualizações no anúncio original pelo
	 * valor de investimento
	 */
	private static int viewsAnuncioOriginal(double investimento , long qtdDias) {
		return (int) Math.round(investimento * 30 * qtdDias);
	}
	
	/*
	 * função que retorna a quantidade de cliques a partir do anúncio original
	 */
	private static int qtdCliks(int viewsAnuncioOriginal) {
		return (int) (Math.round(viewsAnuncioOriginal * 12) / 100);
	}

	/*
	 * função que retorna a quantidade de compartilhamentos gerados pelo número de
	 * cliques no anuncio original
	 */
	private static int qtdShares(int qtdClicks) {
		return (int) (Math.round(qtdClicks * 3) / 20);
	}

	/*
	 * função que retorna a quantidade de vizualizações possiveis pelo número de compartilhamentos
	 */
	private static int qtdViewsShares(int qtdShares) {
		return qtdShares * 40;
	}

	/*
	 *  função que retorna o total de vizualizações multiplicado pelo número de
	 * compartilhamentos em sequência
	 */
	private static int totalViews(int qtdViewsShares, int viewsAnuncioOriginal) {
		return (qtdViewsShares + viewsAnuncioOriginal) * 4;
	}

}
