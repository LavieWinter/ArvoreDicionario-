// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1

import java.util.ArrayList;
import java.util.List;

public class WordTree {
    
    // Classe interna
    private class CharNode {

        private char character;
	    private String significado;
        private CharNode father;
        private List<CharNode> children;
        private int nChilds;

        public CharNode(char character) {
            this.character = character;
            significado = null;
            father = null;
            this.children=new ArrayList<CharNode>();
            nChilds = 0;
        }
        
        public CharNode(char character, String significado) {
            this.character = character;
            this.significado = significado;
            father = null;
            this.children=new ArrayList<CharNode>();
            nChilds = 0;

        }

        /**
        * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
        * @param character - caracter a ser adicionado
        * @param isfinal - se é final da palavra ou não
        */

        public CharNode addChild (char character) {
            CharNode node = new CharNode(character);
            this.children.add(node);
            node.father = this;
            nChilds++;
            return node;
        }

        public CharNode addChild (char character, String significado) {
            CharNode node = new CharNode(character, significado);
            this.children.add(node);
            node.father = this;
            nChilds++;
            return node;
        }
        
        public int getNumberOfChildren () {
            return children.size();
        }
        
        public CharNode getChild (int index) {
            if(index >= 0 && index <= this.nChilds)
                return children.get(index);
            
            else
                return null;
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * @return a palavra
         */
        private String getWord() {
            if(significado != null) {
                List<Character> letters = new ArrayList<>();
                letters.add(this.character);
                CharNode before = father;
                while(before != null) {
                    letters.add(before.character);
                    before = before.father;
                }
                String word = "";
                for(int i = letters.size()-2; i >= 0; i--) {
                    word += letters.get(i);
                }
                return word;
            }
            return null;
        }
        
        public String getSignificado () {
            return significado;
        }

        /**
        * Encontra e retorna o nodo que tem determinado caracter.
        * @param character - caracter a ser encontrado.
        */
        public CharNode findChildChar (char character) {
            CharNode node;
            for(int i = 0; i < this.nChilds; i++)
            {
                node = this.getChild(i);
                if (node.character == character){
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
    public WordTree() {
        this.root = new CharNode(' ');
        this.totalNodes = 0;
        this.totalWords = 0;
    }


    
    // Metodos
    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalNodes() {
        return totalNodes;
    }
    
    /**
    *Adiciona palavra na estrutura em árvore
    *@param word
    */


    public void addWord(String word, String significado) {
        // fazer lógica anterior ao acrescimos das letras
        
        CharNode aux = root;
        int tamanho = word.length();
        int index = 0;
        char[] palavra = word.toLowerCase().toCharArray();


        for (char letra : palavra) {
            CharNode tmp = aux.findChildChar(letra);
            if(tmp != null) {
                aux = tmp;
                index++;
            }
            else break;
        }
        if (index < tamanho) {
            for(int i = index; i < tamanho; i++) {
                CharNode prox;
                if (i != tamanho-1)
                {
                    prox = aux.addChild(palavra[i]);
                    totalNodes++;
                }
                else
                {
                    prox = aux.addChild(palavra[i], significado);
                    totalNodes++;
                    totalWords++;
                }
                aux = prox;
            }
        }
    }
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    // private CharNode findCharNodeForWord(String word) {
        // ...
        // 
    // }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public List<Palavra> searchAll(String prefix) {
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