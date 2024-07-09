package comp3350.intellicards.Presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

import java.util.List;

import comp3350.intellicards.Application.UserSession;
import comp3350.intellicards.Objects.FlashcardSet;
import comp3350.intellicards.Business.FlashcardManager;
import comp3350.intellicards.Business.FlashcardSetManager;
import comp3350.intellicards.R;

public class MainActivity extends Activity {

    private FlashcardSetManager flashcardSetManager;
    private GridLayout gridLayout;
    private String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = UserSession.getInstance().getUsername(); // Get the username from the UserSession singleton

        initializePersistence();
        setupButtons();
    }

    private void initializePersistence() {
        flashcardSetManager = new FlashcardSetManager();
        FlashcardManager flashcardManager = new FlashcardManager();

        gridLayout = findViewById(R.id.gridLayout);

        loadFlashcardSets();
    }

    // Load all Flashcard Sets from the database
    private void loadFlashcardSets() {
        gridLayout.removeAllViews();

        List<FlashcardSet> flashcardSets = flashcardSetManager.getFlashcardSetsByUsername(username);
        for (FlashcardSet set : flashcardSets) {
            Button flashcardSetButton = new Button(this);
            String title = set.getFlashcardSetName() + " (" + set.getActiveCount() + ") ";
            flashcardSetButton.setText(title);
            flashcardSetButton.setLayoutParams(new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f)));
            flashcardSetButton.setPadding(16, 16, 16, 16);
            flashcardSetButton.setOnClickListener(v -> openFlashcardSetActivity(set.getUUID()));
            flashcardSetButton.setHeight(350);
            flashcardSetButton.setTextSize(25);
            flashcardSetButton.setWidth(55);
            flashcardSetButton.setBackgroundColor(0xFFFFFFFD);
            gridLayout.addView(flashcardSetButton);
        }
    }

    private void openFlashcardSetActivity(String flashcardSetUUID) {
        Intent intent = new Intent(MainActivity.this, FlashcardSetActivity.class);
        intent.putExtra("flashcardSetUUID", flashcardSetUUID);
        startActivity(intent);
    }

    private void showCreateNewSetDialog() {
        final int MAX_NAME_LENGTH = 25;

        EditText newSetNameInput = new EditText(this);
        newSetNameInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_NAME_LENGTH)});

        new android.app.AlertDialog.Builder(this)
                .setTitle("Create New Flashcard Set")
                .setMessage("Enter the name for the new Flashcard Set:")
                .setView(newSetNameInput)
                .setPositiveButton("Create", (dialog, whichButton) -> {
                    String newSetName = newSetNameInput.getText().toString().trim();
                    if (!newSetName.isEmpty()) {

                        FlashcardSet newFlashcardSet = new FlashcardSet(username, newSetName);
                        flashcardSetManager.insertFlashcardSet(newFlashcardSet);
                        loadFlashcardSets(); // Refresh the list of Flashcard Sets
                    } else {
                        dialog.dismiss(); // Dismiss the dialog if the user didn't enter a name
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void setupButtons() {
        setupProfilePageButton();
        setupCreateNewSetButton();
    }

    private void setupProfilePageButton() {
        AppCompatImageButton profilePageButton = findViewById(R.id.profileButton);
        profilePageButton.setOnClickListener(v -> openProfileActivity());
    }

    private void openProfileActivity() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void setupCreateNewSetButton() {
        Button createNewSetButton = findViewById(R.id.createNewSetButton);
        createNewSetButton.setOnClickListener(v -> showCreateNewSetDialog());
    }
}
