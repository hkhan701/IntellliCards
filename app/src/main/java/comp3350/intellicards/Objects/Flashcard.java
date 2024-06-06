package comp3350.intellicards.Objects;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Flashcard {

    private final String uuid;
    private String answer;
    private String question;
    private String hint;
    private boolean deleted;

    // Private constructor used internally to ensure UUID is always set
    private Flashcard(@NonNull String answer, @NonNull String question, String hint) {
        this.uuid = UUID.randomUUID().toString();
        this.answer = answer;
        this.question = question;
        this.hint = hint;
        this.deleted = false;
    }

    public String getUUID() {
        return uuid;
    }


    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getHint() {
        return hint;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void markDeleted() {
        deleted = true;
    }

    public void markRecovered() {
        deleted = false;
    }

    @NonNull
    @Override
    public String toString() {
        String flashcardInfo = "uuid='" + uuid + "'\n" +
                ", question='" + question + "'\n" +
                ", answer='" + answer + "'\n";

        if (hint != null && !hint.trim().isEmpty()) {
            flashcardInfo += ", hint = '" + hint + "'\n";
        }

        return flashcardInfo;
    }
}
