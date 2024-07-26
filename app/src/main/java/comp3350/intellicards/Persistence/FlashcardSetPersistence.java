package comp3350.intellicards.Persistence;

import java.util.List;

import comp3350.intellicards.Objects.Flashcard;
import comp3350.intellicards.Objects.FlashcardSet;

public interface FlashcardSetPersistence {
    FlashcardSet getFlashcardSet(String flashcardSetUUID);

    List<FlashcardSet> getAllFlashcardSets();

    void insertFlashcardSet(FlashcardSet newFlashcardSet);
}
