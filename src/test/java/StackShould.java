import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class StackShould {
    public static final String FIRST_ELEMENT = "first added element";
    public static final String SECOND_ELEMENT = "second added element";

    private Stack stack;

    @BeforeEach
    void beforeEach() {
        stack = new Stack();
    }

    @Test
    void pop_last_element_pushed() {
        stack.push(FIRST_ELEMENT);
        stack.push(SECOND_ELEMENT);

        assertThat(stack.pop()).isEqualTo(SECOND_ELEMENT);
    }

    @Test
    void pop_elements_in_reverse_order_that_pushed() {
        stack.push(FIRST_ELEMENT);
        stack.push(SECOND_ELEMENT);

        assertThat(stack.pop()).isEqualTo(SECOND_ELEMENT);
        assertThat(stack.pop()).isEqualTo(FIRST_ELEMENT);
    }

    @Test
    void throw_an_exception_when_popping_an_empty_stack() {
        assertThrows(IndexOutOfBoundsException.class, stack::pop);
    }
}
