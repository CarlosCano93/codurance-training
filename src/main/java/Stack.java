import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List<Object> elements = new ArrayList<>();

    public Object pop() {
        return elements.remove(elements.size() - 1);
    }

    public void push(Object element) {
        elements.add(element);
    }
}
