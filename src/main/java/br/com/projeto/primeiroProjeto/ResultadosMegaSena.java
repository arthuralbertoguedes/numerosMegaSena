package br.com.projeto.primeiroProjeto;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class ResultadosMegaSena {

	private static String [] numerosSorteados;
	private final static String URL = "http://www1.caixa.gov.br/_newincludes/home_2011/resultado_megasena.asp";
	
	public ResultadosMegaSena(){
		
	}
	
	public static void receberNumerosSorteados() {
		//Objeto http que executara as requisicoes
		HttpClient http = new DefaultHttpClient();
		try {
			//Objeto get com a url que sera executado o metodo
			HttpGet get = new HttpGet(URL);
			//Objeto que trata da resposta  --Esses dois objetos sao parametros para executar a requisicao
			ResponseHandler<String> handler = new BasicResponseHandler();
			
			//Executando requisicao
			String resposta = http.execute(get,handler);
			obterResposta(resposta);
		
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {
			 http.getConnectionManager().shutdown();
		}
	}
	
	private static void obterResposta(String resposta) {
		String divNome = "<div id='concurso_resultado'>";
		int parteInicial = resposta.indexOf(divNome);
		int parteFinal = resposta.indexOf("</div>");
		String extracaoValores = resposta.substring(parteInicial+divNome.length(), parteFinal).replaceAll(" ","");
		
		numerosSorteados = extracaoValores.split("-");
		System.out.println("Os números da Mega-Sena sorteados foram:");
		for(String i : numerosSorteados) {
			System.out.print(i + " ");
		}
	}
	
	
	public String[] getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(String[] numerosSorteados) {
		ResultadosMegaSena.numerosSorteados = numerosSorteados;
	}
	
	
	
}
