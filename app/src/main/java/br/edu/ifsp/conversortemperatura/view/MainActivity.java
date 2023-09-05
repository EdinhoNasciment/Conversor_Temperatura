package br.edu.ifsp.conversortemperatura.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.conversortemperatura.R;
import br.edu.ifsp.conversortemperatura.model.FahrenStrategy;
import br.edu.ifsp.conversortemperatura.model.CelsiusStrategy;
import br.edu.ifsp.conversortemperatura.model.ConversorTemperatura;

public class MainActivity extends AppCompatActivity {

    //Instanciando os elementos visuais

    private EditText inputEditText;
    private Button celsiusButton;
    private Button fahrenButton;
    private TextView saidaTextView;
    private TextView msgSaidaTextView;
    private ConversorTemperatura conversorStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById();
        setClick();
    }

    private void findById(){
        inputEditText = findViewById(R.id.edittext_temperatura);
        celsiusButton = findViewById(R.id.btn_celsius);
        fahrenButton = findViewById(R.id.btn_fahren);
        saidaTextView = findViewById(R.id.textview_resultado);
        msgSaidaTextView = findViewById(R.id.textview_resultado2);
    }

    private void setClick(){
        celsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processo(CelsiusStrategy.getInstance(), "ºC");
            }
        });


        fahrenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processo(FahrenStrategy.getInstance(),"ºF");
            }
        });
    }

    private double lerTemp()throws NumberFormatException{
        double retorno;

        try {
            retorno = Double.parseDouble(inputEditText.getText().toString());
        } catch (NumberFormatException e){
            Toast.makeText(this,"Erro na leitura da temperatura", Toast.LENGTH_SHORT).show();
            throw  new NumberFormatException();
        }

        return retorno;
    }

    private void processo(ConversorTemperatura strategy, String escala){
        conversorStrategy = strategy;
        double value;

        try {
            value = lerTemp();
            saidaTextView.setText(String.format("%.2f %s", conversorStrategy.converter(value),escala));
            if (escala.equals("ºC")){
                msgSaidaTextView.setText(getString(R.string.msgFtoC));
            }else{
                msgSaidaTextView.setText(getString(R.string.msgCtoF));
            }
        }catch (NumberFormatException e){
            value = 0;
        }
    }
}