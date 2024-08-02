public class CustomStack<T> implements ICustom<T> {
    private Node<T> lastNode;
    private Node<T> firstNode;

    @Override
    public void push(T value) {
        // Insertar
        if(lastNode==null){
            lastNode= new Node<T>(value);
            firstNode=lastNode;
            }else{
            Node<T> current = new Node<T> (value);
            current.setNext(firstNode);
            firstNode=current;
            }
    }

    @Override
    public T pop() {
        // Eliminar
        if (firstNode!= null){
            if(firstNode.equals(lastNode)){
               T value=lastNode.getValue();
               lastNode=null;
               firstNode=null;
                return value;
        
        
            }else{
                T valuef=firstNode.getValue();
                firstNode=firstNode.getNext();
        
                return valuef;
        
        
            }
            
    
        }else{
            return null;
        }

    }

}




   