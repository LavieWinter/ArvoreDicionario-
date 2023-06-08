// 4645G-04 - Algoritmos e Estruturas de Dados I
// 2023-1

import java.util.ArrayList;
import java.util.List;

public class WordTree {
    
    // Classe interna
    private class CharNode {

        private char character;
	    private String significado;
        private boolean isFinal;
        private CharNode father;
        private List<CharNode> children;
        private int nChilds;

        public CharNode(char character) {
            this.character = character;
            significado = "";
            isFinal = false;
            father = null;
            this.children=new ArrayList<CharNode>();
            nChilds = 0;
        }
        
        public CharNode(char character, boolean isFinal) {
            this.character = character;
            significado = "";
            this.isFinal = isFinal;
            father = null;
            this.children=new ArrayList<CharNode>();
            nChilds = 0;

        }

        /**
        * Adiciona um filho (caracter) no nodo. Não pode aceitar caracteres repetidos.
        * @param character - caracter a ser adicionado
        * @param isfinal - se é final da palavra ou não
        */

        public CharNode addChild (char character, boolean isfinal) {
            CharNode node = new CharNode(character, isfinal);
            this.children.add(node);
            nChilds++;
            return node;
        }
        
        public int getNumberOfChildren () {
            return children.size();
        }
        
        public CharNode getChild (int index) {
            return null;
        }

        /**
         * Obtém a palavra correspondente a este nodo, subindo até a raiz da árvore
         * @return a palavra
         */
        private String getWord() {
            ...
        }
        
        /**
        * Encontra e retorna o nodo que tem determinado caracter.
        * @param character - caracter a ser encontrado.
        */
        public CharNode findChildChar (char character) {
         
            return null;
        }
        
    }


    
    // Atributos
    private CharNode root;
    private int totalNodes = 0;
    private int totalWords = 0;
    


    // Construtor
    public WordTree() {
        CharNode newNode = new CharNode(' ');
            this.root=newNode;
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


    public void addWord(String word) {
        // fazer lógica anterior ao acrescimos das letras
        
        CharNode aux = root;
        int tamanho = word.length();
        int index = -1;
        char[] palavra = word.toCharArray();


        for (char letra : word.toCharArray()) {
            if(aux.findChildChar(letra) != null) {
                aux = aux.findChildChar(letra);
                index++;
            }
            else break;
        }
        if ((tamanho - index) != 0) {
            for(int i = index; i < tamanho; i++) {
                if ((tamanho - index) > 1)
                {
                    aux.addChild(palavra[i], false);
                    totalNodes++;
                }
                else
                {
                    aux.addChild(palavra[i], true);
                    totalNodes++;
                    totalWords++;
                }
            }
        }
    }
    
    /**
     * Vai descendo na árvore até onde conseguir encontrar a palavra
     * @param word
     * @return o nodo final encontrado
     */
    private CharNode findCharNodeForWord(String word) {
        ...
        
    }

    /**
    * Percorre a árvore e retorna uma lista com as palavras iniciadas pelo prefixo dado.
    * Tipicamente, um método recursivo.
    * @param prefix
    */
    public List<String> searchAll(String prefix) {
        ...
    }   

}
