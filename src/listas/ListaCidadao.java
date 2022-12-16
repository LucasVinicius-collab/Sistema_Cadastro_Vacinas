package listas;

import java.util.ArrayList;
import java.util.Iterator;
import entidade.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import telas.Relatorio;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListaCidadao {
    
    ArrayList cidadaos=new ArrayList();

    public void adicionarCidadao(Cidadao cidadao){
        
        cidadaos.add(cidadao);
        
        File arquivo = new File("C:\\Users\\Public\\Documents/Cidadao_Vacinados.txt");
        DateTimeFormatter data_atual = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            //escrevendo no arquivo
            FileWriter fw = new FileWriter(arquivo, true);
            Iterator iter=cidadaos.iterator();
            if(iter.hasNext()==true){
                String str = "---Imprimindo Vacinados---\nData da aplicação: "+data_atual.format(LocalDateTime.now());
                while(iter.hasNext()){
                    fw.write(str+="\n"+iter.next());   
                }
	    fw.close();
            }
        } catch (IOException ex) {
        }
    }
    
    public void imprimirArquivo() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents/Cidadao_Vacinados.txt"))) {
            
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            Relatorio relatorio = new Relatorio(fileAsString);
        } 
    }
}