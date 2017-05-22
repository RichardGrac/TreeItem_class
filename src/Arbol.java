import javafx.scene.control.TreeItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 22/05/2017.
 */
public class Arbol {

    ArrayList<String> line = new ArrayList<>();
    TreeItem<String> root;
    int it = 0;  // Iterador

    public Arbol() {
        lee_fichero();
//        print_Tree();
        save_tree();
        System.out.println("Fin");
    }

    private void print_Tree(){
        for (int i = 0; i < line.size(); i++){
            System.out.println(line.get(i));
        }
    }

    private void save_tree(){
        root = new TreeItem<String>(line.get(it));
        it++;
        get_Tree(0, root);
    }

    private void get_Tree(int prevTab, TreeItem<String> root){
        // Contamos el numero de Tabulaciones que tiene la linea
        while (it < line.size()){
            int tabs = count_tabs(line.get(it));
            if (tabs > prevTab){  // Si es hijo
                String [] token = line.get(it).split(" ");
                root.getChildren().add(new TreeItem<>(token[token.length-1]));  // El ultimo del split será el token
                it++;
                get_Tree(tabs, root.getChildren().get(root.getChildren().size()-1));  // El ultimo agregado
            }else{
                break;
            }
        }
    }

    private int count_tabs(String linea){
        int contador = 0;
        for (int i = 0; i < linea.length(); i++){
            if (linea.charAt(i) == ' ')
                    contador++;
        }
        // Al no poder hacer split('\t'), contamos los (espaciosTotales+1)/4
        if (contador > 0){
            contador++;
            contador /= 4;
        }
        return contador;
    }

    private void lee_fichero(){
        try {
            FileReader fr = new FileReader("C:\\Users\\Richa\\PycharmProjects\\Compilador\\Tree.txt");
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while((linea = br.readLine()) != null){
                if(!linea.contains("Syntax error"))
                    line.add(linea);    // Agregamos solo Tokens, No errores.
            }
            fr.close();
        }catch(Exception e) {
            System.out.println("Excepcion leyendo fichero: " + e);
        }
    }

    public static void main(String[] args) {
        Arbol arbol = new Arbol();
    }
}