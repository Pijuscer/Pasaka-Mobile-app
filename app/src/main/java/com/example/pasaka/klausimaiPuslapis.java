package com.example.pasaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class klausimaiPuslapis extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button buttonPateik;

    EditText tekstIvedimas1kl,tekstIvedimas7kl, tekstIvedimas10kl;
    RadioButton radioButton2kl, radioButton6kl;
    RadioGroup radioButtonGrupe2kl, radioButtonGrupe6kl;
    CheckBox checkBox1KlTrecias, checkBox2KlTrecias, checkBox3KlTrecias, checkBox4KlTrecias, checkBox1KlAstuntas, checkBox2KlAstuntas, checkBox3KlAstuntas;
    ChipGroup chipgroupKetvirtasKl, chipgroupDevintasKl;
    Spinner spinner5kl;
    private int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klausimai_puslapis);

        Spinner spinnerVariantai = findViewById(R.id.spinner5kl);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.variantai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVariantai.setAdapter(adapter);
        spinnerVariantai.setOnItemSelectedListener(this);

        tekstIvedimas1kl = (EditText) findViewById(R.id.tekstasIvedimas1kl);
        tekstIvedimas7kl = (EditText) findViewById(R.id.tekstasIvedimas7kl);
        tekstIvedimas10kl = (EditText) findViewById(R.id.tekstasIvedimas10kl);
        radioButtonGrupe2kl = (RadioGroup) findViewById(R.id.radioButtonGroup2kl);
        radioButtonGrupe6kl = (RadioGroup) findViewById(R.id.radioButtonGroup6kl);
        checkBox1KlTrecias = (CheckBox) findViewById(R.id.checkBox1kl3);
        checkBox2KlTrecias = (CheckBox) findViewById(R.id.checkBox2kl3);
        checkBox3KlTrecias = (CheckBox) findViewById(R.id.checkBox3kl3);
        checkBox4KlTrecias = (CheckBox) findViewById(R.id.checkBox4kl3);
        checkBox1KlAstuntas = (CheckBox) findViewById(R.id.checkBox1Kl8);
        checkBox2KlAstuntas = (CheckBox) findViewById(R.id.checkBox2Kl8);
        checkBox3KlAstuntas = (CheckBox) findViewById(R.id.checkBox3Kl8);
        chipgroupKetvirtasKl = (ChipGroup) findViewById(R.id.chipgroup4kl);
        chipgroupDevintasKl = (ChipGroup) findViewById(R.id.chipgroup9kl);


        buttonPateik = (Button) findViewById(R.id.buttonPateikti);



        buttonPateik.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                JSONArray Atsakymai = new JSONArray();
                String PirmasKlausimas = tekstIvedimas1kl.getText().toString();
                String SeptintasKlausimas = tekstIvedimas7kl.getText().toString();
                String DesimtasKlausimas = tekstIvedimas10kl.getText().toString();

                int selectedRadioId = radioButtonGrupe2kl.getCheckedRadioButtonId();
                radioButton2kl = findViewById(selectedRadioId);

                int selectedRadioId2 = radioButtonGrupe6kl.getCheckedRadioButtonId();
                radioButton6kl = findViewById(selectedRadioId2);

                JSONObject Atsakymas = new JSONObject();
                try {
//                    Susikuriu sd korteleje faila
                    File DownloadAplankalas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File file = new File(DownloadAplankalas, "AnketaAtsakymuJson.json");
                    String JsonString = "";
                    //Papildo json faila
                    if (file.exists()) {

                        FileInputStream fis = new FileInputStream(file);
                        int size = fis.available();
                        byte[] buffer = new byte[size];
                        fis.read(buffer);
                        fis.close();
                        JsonString = new String(buffer, "UTF-8");

                    } else {
                        file.createNewFile();
                    }

                    if(JsonString.isEmpty()) {
                        Atsakymai=new JSONArray();
                    } else {
                        Atsakymai=new JSONArray(JsonString);
                    }

                    Atsakymas.put("PirmasKlausimas", PirmasKlausimas);
                    Atsakymas.put("AntrasKlausimas", radioButton2kl.getText());

                    CheckBox[] checkBoxesMasyvas = new CheckBox[]{checkBox1KlTrecias, checkBox2KlTrecias, checkBox3KlTrecias, checkBox4KlTrecias};
                    JSONArray chckBoxJsonMasyvas = new JSONArray();

                    for (int i=0; i< checkBoxesMasyvas.length; i++) {
                        CheckBox checkBox= checkBoxesMasyvas[i];

                        if(checkBox.isChecked()) {
                            JSONObject chckBoxJsonObj= new JSONObject();
                            chckBoxJsonObj.put("Variantas", checkBox.getText().toString());
                            chckBoxJsonMasyvas.put(chckBoxJsonObj);
                            Atsakymas.put("TreciasKlausimas", chckBoxJsonMasyvas);
                        }
                    }

                    List<Integer> pazymetasdChips = chipgroupKetvirtasKl.getCheckedChipIds();

                    JSONArray ChipMasyvas= new JSONArray();
                    for (int chipId: pazymetasdChips) {
                        Chip chip=chipgroupKetvirtasKl.findViewById(chipId);
                        String chipTekstas=chip.getText().toString();
                        JSONObject chipObj= new JSONObject();

                        try {
                            chipObj.put("Variantas", chipTekstas);
                            ChipMasyvas.put(chipObj);
                            Atsakymas.put("KetvirtasKlausimas", ChipMasyvas);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Spinner spineris = findViewById(R.id.spinner5kl);
                    Atsakymas.put("PenktasKlausimas", spineris.getSelectedItem().toString());

                    Atsakymas.put("SestasKlausimas", radioButton6kl.getText());
                    Atsakymas.put("SeptintasKlausimas", SeptintasKlausimas);

                    CheckBox[] checkBoxesMasyvas2 = new CheckBox[]{checkBox1KlAstuntas, checkBox2KlAstuntas, checkBox3KlAstuntas};
                    JSONArray chckBoxJsonMasyvas2 = new JSONArray();

                    for (int i=0; i< checkBoxesMasyvas2.length; i++) {
                        CheckBox checkBox= checkBoxesMasyvas2[i];

                        if(checkBox.isChecked()) {
                            JSONObject chckBoxJsonObj= new JSONObject();
                            chckBoxJsonObj.put("Variantas", checkBox.getText().toString());
                            chckBoxJsonMasyvas2.put(chckBoxJsonObj);
                            Atsakymas.put("AstuntasKlausimas", chckBoxJsonMasyvas2);
                        }
                    }

                    List<Integer> pazymetasdChips2 = chipgroupDevintasKl.getCheckedChipIds();

                    JSONArray ChipMasyvas2= new JSONArray();
                    for (int chipId: pazymetasdChips2) {
                        Chip chip=chipgroupDevintasKl.findViewById(chipId);
                        String chipTekstas=chip.getText().toString();
                        JSONObject chipObj= new JSONObject();

                        try {
                            chipObj.put("Variantas", chipTekstas);
                            ChipMasyvas2.put(chipObj);
                            Atsakymas.put("DevintasKlausimas", ChipMasyvas2);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Atsakymas.put("DesimtasKlausimas", DesimtasKlausimas);

                    Atsakymai.put(Atsakymas);
                    Atsakymai.toString();
                    FileWriter Writer = new FileWriter(file);
//                    formatavimas tarpu
                    Writer.write(Atsakymai.toString(4));
                    Writer.flush();
                    Writer.close();

                } catch (JSONException e) {
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Intent MygtukasIPradiniPsl = new Intent(klausimaiPuslapis.this, MainActivity.class);
                startActivity(MygtukasIPradiniPsl);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();

        ((TextView) adapterView.getChildAt(0)).setTextSize(23);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}