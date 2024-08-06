package comp3350.intellicards.Persistence.stubs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import comp3350.intellicards.Objects.FlashcardSet;
import comp3350.intellicards.Persistence.FlashcardSetPersistence;

public class FlashcardSetPersistenceStub implements FlashcardSetPersistence {

    private Map<String, FlashcardSet> flashcardSets;

    public FlashcardSetPersistenceStub() {
        flashcardSets = new LinkedHashMap<>();

        FlashcardSet set1 = new FlashcardSet("set1", "user1", "Math");
        FlashcardSet set2 = new FlashcardSet("set2", "user1", "Science");
        FlashcardSet set3 = new FlashcardSet("set3", "user2", "History");
        FlashcardSet set4 = new FlashcardSet("set4", "user3", "Geography");

        flashcardSets.put(set1.getUUID(), set1);
        flashcardSets.put(set2.getUUID(), set2);
        flashcardSets.put(set3.getUUID(), set3);
        flashcardSets.put(set4.getUUID(), set4);
    }

    @Override
    public FlashcardSet getFlashcardSet(String uuid) {
        return flashcardSets.get(uuid);
    }

    @Override
    public List<FlashcardSet> getAllFlashcardSets() {
        return new ArrayList<>(flashcardSets.values());
    }

    @Override
    public List<FlashcardSet> getFlashcardSetsByKey(String key) {
        List<FlashcardSet> searchedFlashcardSets = new ArrayList<>();
        String lowerKey = key.toLowerCase();

        for(FlashcardSet set : flashcardSets.values()) {
            if(set.getFlashcardSetName().toLowerCase().contains(lowerKey)) {
                searchedFlashcardSets.add(set);
            }
        }

        return searchedFlashcardSets;
    }

    @Override
    public void insertFlashcardSet(FlashcardSet newFlashcardSet) {
        flashcardSets.put(newFlashcardSet.getUUID(), newFlashcardSet);
    }

}