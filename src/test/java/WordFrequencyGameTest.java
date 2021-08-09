import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() throws Exception {
        //Given
        String inputStr = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_process_two_words() throws Exception {
        //Given
        String inputStr = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() throws Exception {
        //Given
        String inputStr = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() throws Exception {
        //Given
        String inputStr = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() throws Exception {
        //Given
        String inputStr = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() throws Exception {
        //Given
        String inputStr = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_throw_null_pointer_exception_when_getResult_given_null_input(){
        //Given
        WordFrequencyGame game = new WordFrequencyGame();
        String inputStr = null;

        assertThrows(NullPointerException.class, () -> game.getResult(inputStr));
    }

    @Test
    public void should_return_1_when_get_result_given_empty_string(){
        //Given
        WordFrequencyGame game = new WordFrequencyGame();
        String inputStr = "";

        assertEquals(game.getResult(inputStr), " 1");
    }

    private void validate_Input_words_process_to_expected_word(String inputStr, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getResult(inputStr);
        //Then
        assertEquals(result, expectResult);
    }
}
