
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Principal es la clase que interactua con el usuario y tiene el metodo main
 * @author Leonel Contreras 18797
 * @version 1.0
 */
public class Principal {

    public static void main(String[] args) {
        // creamos un arraylist donde guardamos todo lo que se lee del archivo de texto
        ArrayList<String> lectura = new ArrayList<String>();
        ArrayList<String> stringsbuenos = new ArrayList<String>();
        HashFactory<String,SingleLinkedList<String>> factoriadehash = new HashFactory<String,SingleLinkedList<String>>();
        Set<String> set = new HashSet<String>();
        Set<String> listallaves = new HashSet<>();
        

        try {
            BufferedReader reader = new BufferedReader(new FileReader("inventario.txt"));
            String line;
            while ((line = reader.readLine())!= null) {
                lectura.add(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // este ciclo for imprime los textos leidos por el bufferreader
        /*for (String string : lectura) {
            System.out.println(string);
        }*/

        for (String string : lectura) {
            String stringaagregar = string.replace("|", "   ");
            stringsbuenos.add(stringaagregar);
        }

        // este ciclo for imprime los textos separados por espacio 
        /*for (String string : stringsbuenos) {
            System.out.println(string);
        }*/

        //Map<String, SingleLinkedList<String>> instancia = factoriadehash.InstanceCreator(1);

        //System.out.println(instancia.getClass());

        for (String string : stringsbuenos) {
            String[] lista = string.split("\\s{3}");
            /*for (String string2 : lista) {
                System.out.println(string2);
                System.out.println("asdf");
            }*/
            String palabra1 = lista[0];
            String palabra2 = lista[1].trim();
            set.add(palabra1);
        }

        // este ciclo for ensena las categorias presentes en el texto
        /*for (String string : set) {
            System.out.println(string);
        }*/

        

        System.out.println("Ingrese la opcion de mapa que desea implementar para guardar la lista del supermercado");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        Scanner scanner = new Scanner(System.in);
        Scanner texto = new Scanner(System.in);
        int numero = scanner.nextInt();
        
        Map<String, SingleLinkedList<String>> instancia = factoriadehash.InstanceCreator(numero);
        for (String string : set) {
            SingleLinkedList<String> singlelinkedlist = new SingleLinkedList<String>();
            instancia.put(string, singlelinkedlist);
        }

        listallaves = instancia.keySet();
        List<String> listatemporal = new ArrayList<String>(listallaves);
        Collections.sort(listatemporal);

        /*for (String string : listatemporal) {
            System.out.println(string);
        }*/
        

        /*System.out.println("Las categorias de productos son:");
        for (String string : listallaves) {
            System.out.println(string);
        }*/

        //int contador = 1;
        for (String string : stringsbuenos) {
            String[] lista = string.split("\\s{3}");
            String key = lista[0];
            String value = lista[1].trim();
            //obtenemos la lista que corresponde a la llave
            SingleLinkedList<String> singlelinkedlist = instancia.get(key);
            singlelinkedlist.InsertAtStart(value);
            //System.out.println(contador + "Agrego un valor");
            //contador++;
        }

        System.out.println("Ingrese la opcion de map que desea implementar el usuario");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        int opcionmapusuario = scanner.nextInt();

        Map<String, SingleLinkedList<String>> usuario = factoriadehash.InstanceCreator(opcionmapusuario);
        //System.out.println(usuario.getClass());

        // hice un cambio aqui
        for (String llave : listatemporal) {
            SingleLinkedList<String> linkedListusuario = new SingleLinkedList<String>();
            usuario.put(llave, linkedListusuario);
        }
        int opcionusuario = 0;

        while (opcionusuario!= 6) {
            System.out.println(" ");
            System.out.println("Ingrese una opcion");
            System.out.println("1. Mostrar todo el inventario");
            System.out.println("2. Mostrar categoria de un producto");
            System.out.println("3. Agregar un producto a la coleccion del usuario");
            System.out.println("4. Mostrar categoria y cantidad de producto que posee el usuario");
            System.out.println("5. Ver productos del usuario");
            System.out.println("6. Salir");
            opcionusuario = scanner.nextInt();

            if (opcionusuario == 3) {
                System.out.println("Ingrese el nombre del producto que desea agregar a la lista del usuario");
                String producto = texto.nextLine();
                // verificamos llave por llave si existe algun producto con el nombre ingresado
                String llave = null;
                for (String key : listallaves) {
                    SingleLinkedList listaarecorrer = instancia.get(key);
                    for (int i = 0; i < listaarecorrer.Count(); i++){
                        if (listaarecorrer.Get(i).equals(producto)) {
                            llave = key;
                            break;
                        }
                    }
                }

                if (llave == null) {
                    try {
                        throw new Exception("No existe producto con el nombre ingresado");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());;
                    }
                } else {
                    System.out.println("Se ha agregado el producto " + producto +" de la categoria " + llave +" exitosamente");
                    SingleLinkedList<String> listadelusuario = usuario.get(llave);
                    listadelusuario.InsertAtStart(producto);
                    //System.out.println(listadelusuario.Count());
                }
            }

            if (opcionusuario ==2) {
                System.out.println("Ingrese el nombre del producto que desea verificar su categoria");
                String producto = texto.nextLine();
                String categoria=null;
                for (String key : listatemporal) {
                    SingleLinkedList listaarecorrer = instancia.get(key);
                    for (int i = 0; i < listaarecorrer.Count(); i++) {
                        if (producto.equals(listaarecorrer.Get(i))) {
                            categoria = key;
                            break;
                        }
                    }
                }
                
                if (categoria==null) {
                    try {
                        throw new Exception("El producto ingresado no pertenece a ninguna categoria ");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("El producto " + producto + " pertenece a la categoria " + categoria);
                }
            }

            if (opcionusuario == 4) {
                System.out.println("Ingrese el producto que desea verificar");
                String producto = texto.nextLine();
                String categoria = " ";
                int contador = 0;
                for (String key : listatemporal) {
                    SingleLinkedList listaarecorrer = usuario.get(key);
                    for (int i = 0; i < listaarecorrer.Count(); i++) {
                        if (producto.equals(listaarecorrer.Get(i))) {
                            contador++;
                            categoria = key;
                        }
                    }
                }

                if (contador == 0) {
                    try {
                        throw new Exception("El usuario no posee el producto ingresado");
                    } catch (Exception e) {
                       System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("El usuario posee " + contador + " articulos de " + producto + " de la categoria " + categoria);
                }
            }

            if (opcionusuario == 5) {
                for (String key : listatemporal) {
                    SingleLinkedList listaarecorrer = usuario.get(key);
                    //System.out.println("Los productos del usuario en la categoria " + key + " son:");
                    int contador =1;
                    if (listaarecorrer.Count()>0) {
                        System.out.println("Los productos del usuario de la categoria " + key + " son:");
                        for (int i = 0; i < listaarecorrer.Count(); i++) {
                            System.out.println(contador +" " + listaarecorrer.Get(i));
                            contador++;
                        }
                    }
                }
            }

            if (opcionusuario == 1) {
                // recorremos el set que contiene las categorias de los productos
                for (String key : listatemporal) {
                    System.out.println("Los productor para la categoria " + key + " son:");
                    // obtenemos el value del map para la respectiva llave
                    SingleLinkedList<String> linkedlista = instancia.get(key);
                    // recorremos cada singlelinkedlist
                    int contador = 1;
                    for (int index = 0; index < linkedlista.Count(); index++) {
                        System.out.println(contador + " " + linkedlista.Get(index));
                        contador++;
                    }
                    System.out.println(" ");
                }
            }
        }

        if (opcionusuario == 6) {
            System.out.println("Usted ha elegido salir");
        }
        
      





    }
}
