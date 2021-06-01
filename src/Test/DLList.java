package Test;

public class DLList<AnyType> {
	//Сама "запись"
	// Значение внутри
    AnyType data;
    // Ссылка на следующий элемент
    DLList<AnyType> next;
    // Ссылка на предыдущий элемент
    DLList<AnyType> prev;

    
    //Если это первый элемент, то просто добавляется значение
    public DLList(AnyType data) {
        this(null, data, null);
    }

    

	//Если не первый элемент, то добавляются ссылки на следующий и предыдущий
    public DLList(DLList<AnyType> prev, AnyType data, DLList<AnyType> next) {
        this.data = data; //Ссылаемся на значение
        this.next = next; //Ссылаемся  на следующий элемент
        this.prev = prev; //Ссылаемся на предыдущий предыдущий
    }
}
