package comp3350.intellicards.Objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlashcardSet {
    private final String uuid;
    private String flashcardSetName;
    private List<Flashcard> flashcards;

    public FlashcardSet() {
        this.uuid = UUID.randomUUID().toString();
        this.flashcards = new ArrayList<>();
    }

    public FlashcardSet(String name) {
        this();
        this.flashcardSetName = name;

    }

    public String getUUID() {
        return uuid;
    }

    public void addFlashCard(@NonNull Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    // Return a flashcard set that contains only the active flashcards
    public FlashcardSet getActiveFlashcards() {
        FlashcardSet undeletedCards = new FlashcardSet(this.flashcardSetName);
        for (Flashcard card : flashcards) {
            if (!card.isDeleted()) {
                undeletedCards.addFlashCard(card);
            }
        }

        return undeletedCards;
    }

    public String getFlashcardSetName() {
        return flashcardSetName;
    }

    public Flashcard getFlashCardById(String uuid) {
        for (Flashcard card : flashcards) {
            if (card.getUUID().equals(uuid)) {
                return card;
            }
        }
        return null;
    }

    public Flashcard getIndex(int index) {
        return flashcards.get(index);
    }

    public int size() {
        return flashcards.size();
    }

    // Return the number of active flashcards in this set
    public int getActiveCount() {
        return getActiveFlashcards().size();
    }


    @NonNull
    @Override
    public String toString() {
        return String.format("FlashCardSet{uuid=%s, flashcardSetName='%s', flashcards=%s}"
                , uuid
                , flashcardSetName
                , flashcards
        );
    }
}
