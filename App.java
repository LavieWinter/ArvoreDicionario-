import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        WordTree arvore = new WordTree();
        leitor(arvore);    
        menu(arvore);
    }

    public static void leitor(WordTree arvore) {
        String aux[];
        Path path1 = Paths.get("dicionario.csv");
        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("UTF-8"))) {// Charset.defaultCharset())
            String line = reader.readLine();
            while (line != null) {
                aux = line.split(";");
                Palavra p = new Palavra(aux[0],aux[1]);
                arvore.addWord(p.getPalavra(), p.getSignificado());
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }  
    }        

    public static void menu(WordTree arvore) {
        System.out.println("Insira o prefixo");
        Scanner sc = new Scanner(System.in);
        String resposta = sc.nextLine();

        List<Palavra> lista = arvore.searchAll(resposta);
        
        System.out.println("\nEscolha a palavra");
        for(Palavra p : lista) {
            System.out.println(p.getPalavra());
        }

        System.out.println("\nDigite a palavra");
        String pesquisa = sc.nextLine();

        for(Palavra p : lista){
            if(p.getPalavra().equals(pesquisa)){
                System.out.println("\n"+ p.getPalavra() +": "+ p.getSignificado());
            }
        }
    }
}