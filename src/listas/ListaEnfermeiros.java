package listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import entidade.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import telas.Relatorio;

public class ListaEnfermeiros {
    
    ArrayList enfermeiros;
    
    public ListaEnfermeiros(){
        enfermeiros=new ArrayList();
    }
    
    public void adicionarEnfermeiro(Enfermeira enfermeira){
        enfermeiros.add(enfermeira);
        
        File arquivo = new File("C:\\Users\\Public\\Documents/Cidadao_Vacinados.txt");
        
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
         
            //caso seja um diretório, é possível listar seus arquivos e diretórios
            File[] arquivos = arquivo.listFiles();

            //escrevendo no arquivo
            FileWriter fw = new FileWriter(arquivo, true);
            Collections.sort(enfermeiros);
            Iterator iter=enfermeiros.iterator();
            if(iter.hasNext()==true){
                while(iter.hasNext()){
                    fw.write("\n"+iter.next()+"\n\n");   
                }
	    fw.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();      
        }
    }
    
    public void imprimirArquivoEnfermeiros() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents/Cidadao_Vacinados.txt"))) {
            
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            new Relatorio(fileAsString);
        } 
    }
}
