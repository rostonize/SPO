package Test;

public class DLList<AnyType> {
	//���� "������"
	// �������� ������
    AnyType data;
    // ������ �� ��������� �������
    DLList<AnyType> next;
    // ������ �� ���������� �������
    DLList<AnyType> prev;

    
    //���� ��� ������ �������, �� ������ ����������� ��������
    public DLList(AnyType data) {
        this(null, data, null);
    }

    

	//���� �� ������ �������, �� ����������� ������ �� ��������� � ����������
    public DLList(DLList<AnyType> prev, AnyType data, DLList<AnyType> next) {
        this.data = data; //��������� �� ��������
        this.next = next; //���������  �� ��������� �������
        this.prev = prev; //��������� �� ���������� ����������
    }
}
