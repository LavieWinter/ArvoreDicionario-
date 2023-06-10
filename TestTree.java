// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1

import java.util.ArrayList;
import java.util.List;

public class TestTree {

    // Classe interna
    private class CharNode {
        private char character;
	    private String significado;
        private CharNode father;
        private List<CharNode> children;

        public CharNode (char character) {
            this.character = character;
            this.significado = null;
            this.father = null;
            this.children = new ArrayList<CharNode>();
        }
        
        public CharNode (char character, String significado) {
            this.character = character;
            this.significado = significado;
            this.father = null;
            this.children = new ArrayList<CharNode>();
        }

        public CharNode addChild (char character) {
            CharNode node = new CharNode(character);
            node.father = this;
            children.add(node);
            return node;
        }

        public CharNode addChild (char character, String significado) {
            CharNode node = new CharNode(character, significado);
            node.father = this;
            children.add(node);
            return node;
        }

        public int getNumberOfChildren () {
            return children.size();
        }
        
        public CharNode getChild (int index) {
            return (index >= 0 && index < children.size()) ? children.get(index) : null;
        }

        public String getWord () {
            if(significado != null) {
                List<Character> letters = new ArrayList<>();
                letters.add(this.character);
                CharNode before = father;
                while(before != null) {
                    letters.add(before.character);
                    before = before.father;
                }
                String word = "";
                for(int i = letters.size()-1; i >= 0; i--) {
                    word += letters.get(i);
                }
                return word;
            }
            return null;
        }
    
        public String getSignificado () {
            return significado;
        }

        public CharNode findChildChar (char character) {
            CharNode node;
            for(int i = 0; i < children.size(); i++) {
                node = getChild(i);
                if (node.character == character) {
                    return node;
                }
            }
            return null;
        }
    }

    // Atributos
    private CharNode root;
    private int totalNodes;
    private int totalWords;
    
    // Construtor
    public TestTree () {
        this.root = new CharNode(' ');
        this.totalNodes = 0;
        this.totalWords = 0;
    }

    // Metodos
    public int getTotalWords () {
        return totalWords;
    }

    public int getTotalNodes () {
        return totalNodes;
    }
    
    /**
    *Adiciona palavra na estrutura em árvore
    *@param word
    */
    public void addWord (String word, String significado) {
        CharNode aux = root;
        int tamanho = word.length();
        char[] palavra = word.toLowerCase().toCharArray();
        int index = 0;

        while (index < tamanho) {
            CharNode tmp = aux.findChildChar(palavra[index]);
            if(tmp != null) {
                aux = tmp;
                index++;
            }
            else break;
        }
        if (index < tamanho) {
            while (index < tamanho) {
                CharNode next;
                if (index == tamanho-1) {
                    next = aux.addChild(palavra[index], significado);
                    totalNodes++;
                    totalWords++;
                }
                else
                {
                    next = aux.addChild(palavra[index]);
                    totalNodes++;  
                }
                aux = next;
                index++;
            }
        }
    }
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     * @throws Exception
     */
    private CharNode findCharNodeForWord (String word) throws Exception {

        throw new Exception("Nao implementado");        

    }

    public List<Palavra> searchAll (String prefix) {
        List<Palavra> palavras = new ArrayList<>();
        char[] letras = prefix.toLowerCase().toCharArray();
        CharNode aux = root;
        for(int i = 0; i < letras.length; i++) {
            aux = aux.findChildChar(letras[i]);
        }
        findWords(aux, palavras);
        return palavras;
    }

    private void findWords (CharNode node, List<Palavra> lista) {
        if(node == null) {
            return;
        }
        if(node.significado != null) {
            lista.add(new Palavra(node.getWord(), node.getSignificado()));
        }
        for(int i = 0; i < node.getNumberOfChildren(); i++) {
            findWords(node.getChild(i), lista);
        }
    }
}