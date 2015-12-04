/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami.parte1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextArea;

/**
 *
 * @author Gabriel Zempoalteca Garrido
 */
public class UAMI {

    public static File archivo_tpl, archivo_err, archivo_obj;
    public static PrintWriter wr1, wr2, wr3;
    public static BufferedWriter bw1, bw2, bw3;
    public static FileReader Archivo_fte;
    public static int linea = 1;
    public static String tokenval = null;
    public static int errores;
    JTextArea panelCompilacion;
    Alex A;
    Tabla_de_Simbolos T;
    Generador_Codigo_Intermedio GI;

    private void crearArchivos(String Nombre_Archivo) {
        int tamaño_N = Nombre_Archivo.length();
        String Nombre_sin_Extension = Nombre_Archivo.substring(0, (tamaño_N - 3));
        try {
            archivo_tpl = new File(Nombre_sin_Extension + "tpl");
            archivo_err = new File(Nombre_sin_Extension + "err");
            archivo_obj = new File(Nombre_sin_Extension + "obj");
            if (!archivo_tpl.exists() && !archivo_err.exists() && !archivo_obj.exists()) {
                archivo_tpl.createNewFile();
                archivo_err.createNewFile();
                archivo_obj.createNewFile();
                panelCompilacion.append("Se han creado los achivos *.tpl y *.err con exito\n\n");
               
                FileWriter w1 = new FileWriter(archivo_tpl);
                bw1 = new BufferedWriter(w1);
                wr1 = new PrintWriter(bw1);
                
                FileWriter w2 = new FileWriter(archivo_err);
                bw2 = new BufferedWriter(w2);
                wr2 = new PrintWriter(bw2);
                wr2.append("* Archivo error *\n");
                wr2.append("Muestra los errores que se presentaron en el proceso de compilación:\n");
               
                FileWriter w3 = new FileWriter(archivo_obj);
                bw3 = new BufferedWriter(w3);
                wr3 = new PrintWriter(bw3);
                wr3.append("Contenido del archivo obj: \n");
                
            } else {
                archivo_tpl.delete();
                archivo_err.delete();
                archivo_obj.delete();
                crearArchivos(Nombre_Archivo);
            }
        } catch (IOException e) {
            panelCompilacion.append("No se pudieron crear los archivos *.tpl, *.err y *.obj Error: " + e + "\n");
        }
    }

    private void cierraArchivo() throws IOException {
        wr2.close();
        bw2.close();
        wr1.close();
        bw1.close();
        wr3.close();
        bw3.close();
    }

    public void compilador(String ruta_ArchFte, JTextArea panelResComp) throws IOException {
        panelCompilacion = panelResComp;
        panelCompilacion.append("Realizando el Análisis Lexicográgico de:\n" + ruta_ArchFte + "\n");
        panelCompilacion.append("\nEspere un momento por favor...\n\n");
        crearArchivos(ruta_ArchFte);
        errores = 0;
        linea = 1;
        tokenval = "";

        Archivo_fte = new FileReader(ruta_ArchFte);
        
        //Inicializa las palabras reservadas
        T = new Tabla_de_Simbolos();
        T.Inicializa_Palabras_Reservadas();
        
        //LLena el buffer por primera vez
        A = new Alex(Archivo_fte);
        A.Llena_Buffer();
        
        wr1.write("* Archivo Tupla *\n  En este archivo se encuentran los Lexemas reconocidos por el Analizador Lexicografico");
        wr1.append("\n\n                    Lexema");
        
        GI = new Generador_Codigo_Intermedio();
        Globales G = new Globales();
        //Llamada al analizador sintactico
        Parser P = new Parser(A,G,T,GI);
        P.Inicio();
        panelResComp.append("\nLineas analizadas: " + (linea - 1));
        wr1.append("\nLineas analizadas: " + (linea - 1)+"\n");
        panelResComp.append("\nErrores encontrados: " + errores);
        T.Imprimir_Tabla();
        cierraArchivo();

    }
}
