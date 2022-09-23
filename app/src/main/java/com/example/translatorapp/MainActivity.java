package com.example.translatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.mlkit.nl.translate.TranslateLanguage;

public class MainActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText mainEdit;
    private TextView translatedText;
    private Button translateButton;

    // Source Array of String - Spinners Data
    String[] fromLanguages = {
            "from", "Afrikaans", "Arabic", "Belarusian", "Bulgarian", "Bengali", "Catalan", "Czech",
            "Welsh", "Danish", "German", "Greek", "English", "Esperanto", "Spanish", "Estonian",
            "Persian", "Finnish", "French", "Irish", "Galician", "Gujarati", "Hebrew", "Hindi",
            "Croatian", "Haitian", "Hungarian", "Indonesian", "Icelandic", "Italian", "Japanese",
            "Georgian", "Kannada", "Korean", "Lithuanian", "Latvian", "Macedonian", "Marathi",
            "Malay", "Maltese", "Dutch", " Norwegian", "Polish", "Portuguese", "Romanian", "Russian",
            "Slovak", "Slovenian", "Albanian" , "Swedish" , "Swahili", "Tamil", "Telugu", "Thai",
            "Tagalog", "Turkish", "Ukrainian", "Urdu", "Vietnamese", "Chinese"
    };
    String[] toLanguages = {
            "to", "Afrikaans", "Arabic", "Belarusian", "Bulgarian", "Bengali", "Catalan", "Czech",
            "Welsh", "Danish", "German", "Greek", "English", "Esperanto", "Spanish", "Estonian",
            "Persian", "Finnish", "French", "Irish", "Galician", "Gujarati", "Hebrew", "Hindi",
            "Croatian", "Haitian", "Hungarian", "Indonesian", "Icelandic", "Italian", "Japanese",
            "Georgian", "Kannada", "Korean", "Lithuanian", "Latvian", "Macedonian", "Marathi",
            "Malay", "Maltese", "Dutch", " Norwegian", "Polish", "Portuguese", "Romanian", "Russian",
            "Slovak", "Slovenian", "Albanian" , "Swedish" , "Swahili", "Tamil", "Telugu", "Thai",
            "Tagalog", "Turkish", "Ukrainian", "Urdu", "Vietnamese", "Chinese"
    };

    // Permissions
    private static final int REQUEST_CODE = 1;

    String languageCode;
    int fromLanguageCode;
    int toLanguageCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromSpinner = findViewById(R.id.idFromSpinner);
        toSpinner = findViewById(R.id.idToSpinner);
        mainEdit = findViewById(R.id.editMain);
        translateButton = findViewById(R.id.button);
        translatedText = findViewById(R.id.translatedTextVew);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguageCode = GetLanguageCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String GetLanguageCode(String language) {
        String LanguageCode = "";

        switch (language){
            case "English":
                languageCode = TranslateLanguage.ENGLISH;
                break;

            case "Afrikaans":
                languageCode = TranslateLanguage.AFRIKAANS;
                break;

            case "Arabic":
                languageCode = TranslateLanguage.ARABIC;
                break;

            case "Belarusian":
                languageCode = TranslateLanguage.BELARUSIAN;
                break;

            case "Bulgarian":
                languageCode = TranslateLanguage.BULGARIAN;
                break;

            case "Bengali":
                languageCode = TranslateLanguage.BENGALI;
                break;

            case "Catalan":
                languageCode = TranslateLanguage.CATALAN;
                break;

            case "Czech":
                languageCode = TranslateLanguage.CZECH;
                break;

            case "Welsh":
                languageCode = TranslateLanguage.WELSH;
                break;

            case "Danish":
                languageCode = TranslateLanguage.DANISH;
                break;

            case "German":
                languageCode = TranslateLanguage.GERMAN;
                break;

            case "Greek":
                languageCode = TranslateLanguage.GREEK;
                break;

            case "Esperanto":
                languageCode = TranslateLanguage.ESPERANTO;
                break;

            case "Spanish":
                languageCode = TranslateLanguage.SPANISH;
                break;

            case "Estonian":
                languageCode = TranslateLanguage.ESTONIAN;
                break;

            case "Persian":
                languageCode = TranslateLanguage.PERSIAN;
                break;

            case "Finnish":
                languageCode = TranslateLanguage.FINNISH;
                break;

            case "French":
                languageCode = TranslateLanguage.FRENCH;
                break;

            case "Irish":
                languageCode = TranslateLanguage.IRISH;
                break;

            case "Galician":
                languageCode = TranslateLanguage.GALICIAN;
                break;

            case "Gujarati":
                languageCode = TranslateLanguage.GUJARATI;
                break;

            case "Hebrew":
                languageCode = TranslateLanguage.HEBREW;
                break;

            case "Hindi":
                languageCode = TranslateLanguage.HINDI;
                break;

            case "Croatian":
                languageCode = TranslateLanguage.CROATIAN;
                break;

            case "Haitian":
                languageCode = TranslateLanguage.HAITIAN_CREOLE;
                break;

            case "Hungarian":
                languageCode = TranslateLanguage.HUNGARIAN;
                break;

            case "Indonesian":
                languageCode = TranslateLanguage.INDONESIAN;
                break;

            case "Icelandic":
                languageCode = TranslateLanguage.ICELANDIC;
                break;

            case "Italian":
                languageCode = TranslateLanguage.ITALIAN;
                break;

            case "Japanese":
                languageCode = TranslateLanguage.JAPANESE;
                break;

            case "Georgian":
                languageCode = TranslateLanguage.GEORGIAN;
                break;

            case "Kannada":
                languageCode = TranslateLanguage.KANNADA;
                break;

            case "Korean":
                languageCode = TranslateLanguage.KOREAN;
                break;

            case "Lithuanian":
                languageCode = TranslateLanguage.LITHUANIAN;
                break;

            case "Latvian":
                languageCode = TranslateLanguage.LATVIAN;
                break;

            case "Macedonian":
                languageCode = TranslateLanguage.MACEDONIAN;
                break;

            case "Marathi":
                languageCode = TranslateLanguage.MARATHI;
                break;

            case "Malay":
                languageCode = TranslateLanguage.MALAY;
                break;

            case "Maltese":
                languageCode = TranslateLanguage.MALTESE;
                break;

            case "Dutch":
                languageCode = TranslateLanguage.DUTCH;
                break;

            case "Norwegian":
                languageCode = TranslateLanguage.NORWEGIAN;
                break;

            case "Polish":
                languageCode = TranslateLanguage.POLISH;
                break;

            case "Portuguese":
                languageCode = TranslateLanguage.PORTUGUESE;
                break;

            case "Romanian":
                languageCode = TranslateLanguage.ROMANIAN;
                break;

            case "Russian":
                languageCode = TranslateLanguage.RUSSIAN;
                break;

            case "Slovak":
                languageCode = TranslateLanguage.SLOVAK;
                break;

            case "Slovenian":
                languageCode = TranslateLanguage.SLOVENIAN;
                break;

            case "Albanian":
                languageCode = TranslateLanguage.ALBANIAN;
                break;

            case "Swedish":
                languageCode = TranslateLanguage.SWEDISH;
                break;

            case "Swahili":
                languageCode = TranslateLanguage.SWAHILI;
                break;

            case "Tamil":
                languageCode = TranslateLanguage.TAMIL;
                break;

            case "Telugu":
                languageCode = TranslateLanguage.TELUGU;
                break;

            case "Thai":
                languageCode = TranslateLanguage.THAI;
                break;

            case "Tagalog":
                languageCode = TranslateLanguage.TAGALOG;
                break;

            case "Turkish":
                languageCode = TranslateLanguage.TURKISH;
                break;

            case "Ukrainian":
                languageCode = TranslateLanguage.UKRAINIAN;
                break;

            case "Urdu":
                languageCode = TranslateLanguage.URDU;
                break;

            case "Vietnamese":
                languageCode = TranslateLanguage.VIETNAMESE;
                break;

            case "Chinese":
                languageCode = TranslateLanguage.CHINESE;
                break;

            default:
                languageCode = "";
        }
        return languageCode;
    }
}