import java.util.List;

public class Debug {
    public static void main(String[] args) {
        TestTree arvore = new TestTree();

        System.out.println("Total Words: "+arvore.getTotalWords());
        System.out.println("Total Nodes: "+arvore.getTotalNodes());

        arvore.addWord("Absonancia", "falta de harmonia entre duas ou mais coisas.");
        System.out.println("Total Words: "+arvore.getTotalWords());
        System.out.println("Total Nodes: "+arvore.getTotalNodes());

        arvore.addWord("Acodamento", "ato de ter pressa ou precipitacao.");
        System.out.println("Total Words: "+arvore.getTotalWords());
        System.out.println("Total Nodes: "+arvore.getTotalNodes());
        
        arvore.addWord("Acrimonia", "falta de harmonia entre duas ou mais coisas.relativo a azedar, azedo. Exemplo: Adicione limao ao iogurte e a mistura estara acrimonia.");
        System.out.println("Total Words: "+arvore.getTotalWords());
        System.out.println("Total Nodes: "+arvore.getTotalNodes());

        List<Palavra> lista = arvore.searchAll("");
        for (Palavra palavra : lista) {
            System.out.println(palavra.toString());
        }
    }
}