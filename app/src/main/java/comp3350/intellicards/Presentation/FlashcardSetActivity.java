package comp3350.intellicards.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.intellicards.Business.FlashcardManager;
import comp3350.intellicards.Objects.Flashcard;
import comp3350.intellicards.Objects.FlashcardSet;
import comp3350.intellicards.Business.FlashcardSetManager;
import comp3350.intellicards.Business.StubManager;
import comp3350.intellicards.R;

public class FlashcardSetActivity extends Activity {

    private RecyclerView flashcardsRecyclerView;
    private TextView flashcardSetTitle;
    private FlashcardSetManager flashcardSetManager = new FlashcardSetManager(StubManager.getFlashcardSetPersistence());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_set);

        flashcardSetTitle = findViewById(R.id.flashcardSetTitle);
        flashcardsRecyclerView = findViewById(R.id.flashcardsRecyclerView);
        flashcardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the flashcard set UUID from the intent
        String flashcardSetId = getIntent().getStringExtra("flashcardSetId");

        if (flashcardSetId != null) {
            FlashcardSet flashcardSet = flashcardSetManager.getFlashcardSet(flashcardSetId);

            if (flashcardSet != null) {
                flashcardSetTitle.setText(flashcardSet.getFlashcardSetName());
                // Set up the RecyclerView with flashcards
                flashcardsRecyclerView.setAdapter(new CardViewAdapter(flashcardSet));
            }
        }

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(FlashcardSetActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Retrieve the updated flashcard ID from the result
            String updatedFlashcardID = data.getStringExtra("flashcardID");

            // Update the TextView in your ViewHolder based on the updated flashcard
            // Get the flashcard set UUID from the intent
            String flashcardSetId = getIntent().getStringExtra("flashcardSetId");
            FlashcardSet flashcardSet = flashcardSetManager.getFlashcardSet(flashcardSetId);
            Flashcard updatedFlashcard = flashcardSet.getFlashCardById(updatedFlashcardID);
            if (updatedFlashcard != null) {
                flashcardsRecyclerView.setAdapter(new CardViewAdapter(flashcardSet));
            }
        }
    }
}
