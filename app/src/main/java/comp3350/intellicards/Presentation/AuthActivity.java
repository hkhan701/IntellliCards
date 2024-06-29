package comp3350.intellicards.Presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.intellicards.Application.Main;
import comp3350.intellicards.Application.UserSession;
import comp3350.intellicards.Objects.User;
import comp3350.intellicards.Business.UserManager;
import comp3350.intellicards.R;

public class AuthActivity extends Activity {
    private UserManager userManager;
    private Button logInButton;
    private Button registerButton;
    private Button guestButton;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        copyDatabaseToDevice();

        userManager = new UserManager();
        initializeViews();
        setUpListeners();
    }

    private void initializeViews() {
        logInButton = findViewById(R.id.logInButton);
        registerButton = findViewById(R.id.registerButton);
        guestButton = findViewById(R.id.guestButton);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
    }

    private void setUpListeners() {
        setUpLogInButtonListener();
        setUpRegisterButtonListener();
        setUpGuestButtonListener();
    }

    private void setUpRegisterButtonListener() {
        registerButton.setOnClickListener(v -> {
            if (verifyInput()) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (userManager.registerUser(username, password)) {
                    Toast.makeText(this, "Sign up successful! Please log in.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Username already exists! Please try again.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Invalid input! Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpLogInButtonListener() {
        logInButton.setOnClickListener(v -> {
            if (verifyInput()) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                User user = userManager.loginUser(username, password);
                if (user != null) {
                    Toast.makeText(this, "Log in successful!", Toast.LENGTH_LONG).show();
                    UserSession.getInstance().setUsername(user.getUsername());
                    System.out.println(UserSession.getInstance().getUsername());
                    navigateToMainActivity();
                } else {
                    Toast.makeText(this, "Invalid login information! Please try again.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Invalid input! Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpGuestButtonListener() {
        guestButton.setOnClickListener(v -> {
            UserSession.getInstance().setUsername("guest");
            navigateToMainActivity();
        });
    }

    private boolean verifyInput() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        return !username.isEmpty() && !password.isEmpty();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            //Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
