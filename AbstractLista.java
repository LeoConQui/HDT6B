
/**
 * AbstractList es la clase abstracta que sirve como clase padre de los dos tipos de lista. Esta clase implementa la interfaz IList y sirve en el diseno 
 * creacional factory como un contenedor en el cual puedo tener referencias a cualquier tipo de lista. Se declaran los metodos abstractos para delegar
 * la implmentacion de los mismos a cada subclase
 * @author Leonel Contreras 18797
 * @version 1.0
 */
public abstract class AbstractLista<T> {

    public abstract void InsertAtStart(T value);

    public abstract void InsertAtEnd(T value);

    public abstract void Insert(T value, int index);

    public abstract T Delete(int index);

    public abstract T DeleteAtStart();

    public abstract T DeleteAtEnd();

    public abstract T Get(int index);

    public abstract boolean IsEmpty();

    public abstract int Count();


    
}