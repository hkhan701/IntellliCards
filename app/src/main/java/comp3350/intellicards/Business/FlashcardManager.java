package comp3350.intellicards.Business;

import java.util.List;

import comp3350.intellicards.Objects.Flashcard;
import comp3350.intellicards.Persistence.FlashcardPersistence;
import comp3350.intellicards.Persistence.stubs.FlashcardPersistenceStub;

public class FlashcardManager {
    private FlashcardPersistence flashcardPersistence;

    public FlashcardManager() {
        flashcardPersistence = new FlashcardPersistenceStub();
    }

    public FlashcardManager(final FlashcardPersistence flashcardPersistence) {
        this.flashcardPersistence = flashcardPersistence;
    }

    List<Flashcard> getAllActiveFlashcards() {
        return this.flashcardPersistence.getAllActiveFlashcards();
    }

    List<Flashcard> getAllDeletedFlashcards() {
        return this.getAllDeletedFlashcards();
    }

    Flashcard getFlashcard(String id) {
        return this.getFlashcard(id);
    }

    public Flashcard insertFlashcard(Flashcard currFlashcard) {
        return flashcardPersistence.insertFlashcard(currFlashcard);
    }

    public Flashcard updateFlashcard(Flashcard currFlashcard) {
        return flashcardPersistence.updateFlashcard(currFlashcard);
    }

    boolean markFlashcardAsDeleted(String id) {
        return flashcardPersistence.markFlashcardAsDeleted(id);
    }

    boolean restoreFlashcard(String id) {
        return flashcardPersistence.restoreFlashcard(id);
    }
}