package com.example.translatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.NaturalLanguageTranslateRegistrar;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

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

    String languageCode, fromLanguageCode, toLanguageCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromSpinner = findViewById(R.id.idFromSpinner);
        toSpinner = findViewById(R.id.idToSpinner);
        mainEdit = findViewById(R.id.editMain);
        translateButton = findViewById(R.id.button);
        translatedText = findViewById(R.id.translatedTextVew);


        // Spinner 1
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                fromLanguageCode = GetLanguageCode(fromLanguages[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Adapter allows us to bind data from the array from languages and display it into the spinner
        // and to make an action to select the data from the spinner
        ArrayAdapter fromAdapter = new ArrayAdapter(this,
                R.layout.spinner_item, fromLanguages);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);


        // Spinner 2
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                toLanguageCode = GetLanguageCode(toLanguages[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter toAdapter = new ArrayAdapter(this, R.layout.spinner_item,
                toLanguages);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);



        // Translate button
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translatedText.setText("");

                if(mainEdit.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Text",
                            Toast.LENGTH_SHORT).show();
                } else if(fromLanguageCode.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Select Main Language", Toast.LENGTH_SHORT).show();
                } else if(toLanguageCode.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Select Target Language", Toast.LENGTH_SHORT).show();
                }else{
                    TranslateText(fromLanguageCode, toLanguageCode, mainEdit.getText().toString());
                }
            }
        });


    }

    private void TranslateText(String fromLanguageCode, String toLanguageCode, String src) {

        translatedText.setText("Downloading Language Model");


        try{
            TranslatorOptions options = new TranslatorOptions.Builder().setSourceLanguage(fromLanguageCode)
                    .setTargetLanguage(toLanguageCode).build();


            Translator translator = Translation.getClient(options);

            DownloadConditions conditions = new DownloadConditions.Builder().build();

            translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    translatedText.setText("Translating...");

                    translator.translate(src).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            translatedText.setText(s);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Failed To Translate", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Failed To Download The Language", Toast.LENGTH_SHORT).show();
                }
            })
            ;

        } catch (Exception e) {
            e.printStackTrace();
        }

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