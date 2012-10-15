package bny.mobile.converter;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TemperatureActivity extends Activity implements OnClickListener {
	
	
	double celcius,fahhenheit,kelvin;
	EditText etCelcius, etFahrenheit, etKelvin;
	ImageButton bDistance,bWeight,bTemperature;
	final DecimalFormat doubleFormat = new DecimalFormat("#.####");
	final static String DATA="DATA",ID="ID";
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_temperature);
	    
//	    link widgets to layout
	    etCelcius=(EditText) findViewById(R.id.EditTextCelsius);
	    etFahrenheit=(EditText) findViewById(R.id.EditTextFahrenheit);
	    etKelvin=(EditText)findViewById(R.id.EditTextKelvin);
		bDistance=(ImageButton)findViewById(R.id.imageButtonDistance);
		bTemperature=(ImageButton)findViewById(R.id.imageButtonTemperature);
		bWeight=(ImageButton)findViewById(R.id.imageButtonWeight);
	    
//	    set the OnClickListener on widgets
		bDistance.setOnClickListener(this);
		bTemperature.setOnClickListener(this);
		bWeight.setOnClickListener(this);
	    
//	    add TextWatcher to widgets
		etCelcius.addTextChangedListener(celciusWatcher);
		etFahrenheit.addTextChangedListener(fahrenheitWatcher);
		etKelvin.addTextChangedListener(kelvinWatcher);
	    
	}//end onCreate
	
	

public TextWatcher celciusWatcher = new TextWatcher() {

	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyCelsius()){
			
			etFahrenheit.setText("");
			etKelvin.setText("");
			
		}//end if
		
	}//end afterTextChanged

	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		
		if(ignoreMinus(sequence.toString()) && onlyCelsius()){
			
			//get number to be converted
			String input = etCelcius.getText().toString();
			celcius = Double.parseDouble(input);
			
			//convert to other units
			fahhenheit =  celcius * 1.8 + 32;
			kelvin = celcius+ 273.15;
			
			//return results up to 2 decimal points
			etFahrenheit.setText(doubleFormat.format(fahhenheit));
			etKelvin.setText(doubleFormat.format(kelvin));
			
		}//end if
		
	}//end onTextChanged
	
};	//end meterWatcher
public TextWatcher fahrenheitWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyFahrenheit()){
			
			etCelcius.setText("");
			etKelvin.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyFahrenheit()){
			
			//get number to be converted
			String input = etFahrenheit.getText().toString();
			fahhenheit = Double.parseDouble(input);
			
			//convert to other units
			celcius =  (fahhenheit - 32) / 1.8;
			kelvin = (fahhenheit + 459.67) / 1.8;
			
			//return results up to 2 decimal points
			etCelcius.setText(doubleFormat.format(celcius));
			etKelvin.setText(doubleFormat.format(kelvin));
			
		}//end if
		
	}//end onTextChanged
	
};	//end mileWatcher
public TextWatcher kelvinWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyKelvin()){
			
			etCelcius.setText("");
			etFahrenheit.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyKelvin()){
			
			//get number to be converted
			String input = etKelvin.getText().toString();
			kelvin = Double.parseDouble(input);
			
			//convert to other units
			celcius =  kelvin - 273.15;
			fahhenheit = (kelvin * 1.8) - 459.67;
			
			//return results up to 2 decimal points
			etCelcius.setText(doubleFormat.format(celcius));
			etFahrenheit.setText(doubleFormat.format(fahhenheit));
			
		}//end if
		
	}//end onTextChanged
	
};	//end mileWatcher

//end TextWatchers	
//OnClickListener Method
	@Override
	public void onClick(View view) {
		
		Intent intent;
		
		switch (view.getId()) {
		
		case R.id.imageButtonDistance:
			
			intent = new Intent(TemperatureActivity.this, DistanceActivity.class);
			startActivity(intent);
			finish();
			
			break;
			
		case R.id.imageButtonTemperature:
			
			showToast("You are here already!");
			
			break;
			
		case R.id.imageButtonWeight:
			
			intent = new Intent(TemperatureActivity.this, WeightActivity.class);
			startActivity(intent);
			finish();
			
			break;
			
		

		default:
			
			//showToast("ID:"+view.getId());
			
			break;
			
		}//end switch-case
		
	}//end OnClick
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

	
//   booleans that handle the focus of the various EditTexts to avoid stackOverFlow with the TextWatchers 
	public boolean onlyCelsius(){
		boolean celcius=false;
		
		if (etCelcius.isFocused()) {
			
			boolean fahrenheit = etFahrenheit.isFocused();
			boolean kelvin = etKelvin.isFocused();
			
			celcius=!fahrenheit&&!kelvin;
			
		}//end onlyMeters
		
		return celcius;
		
	}//end if
	
	public boolean onlyFahrenheit(){
		
		boolean fahrenheit=false;
		
		if (etFahrenheit.isFocused()) {
			
			boolean celcius = etCelcius.isFocused();
			boolean kelvin = etKelvin.isFocused();
			
			fahrenheit=!celcius&&!kelvin;
			
		}//end if
		
		return fahrenheit;
		
	}//end onlyMiles
	
	public boolean onlyKelvin(){
		
		boolean kelvin=false;
		
		if (etKelvin.isFocused()) {
			
			boolean fahrenheit = etFahrenheit.isFocused();
			boolean celcius = etCelcius.isFocused();

			
			kelvin=!fahrenheit&&!celcius;
			
		}//end if
		
		return kelvin;
		
	}//end onlyFeet
//end  booleans that handle the focus ..
	
	
	
//ignore the minus when checking for input
	public boolean ignoreMinus(String sequence){
		
		boolean bool;
		
		if(sequence.startsWith("-")){
			
			bool=(sequence.length()>1);
			
		}else{
			
			bool=(sequence.length()>0);
			
		}//end if-else
		
		
		return bool;
		
	}//end ignoreMinus
	
//	makes toasts a lot easier
	public void showToast(String Message){
		
		Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
		
	}//end toasted
    
}//end DistanceActivity
