package bny.mobile.converter;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WeightActivity extends Activity implements OnClickListener {
	
	
	double kilo, pound, ounce;
	EditText etKilo, etPound, etOunce;
	ImageButton bDistance,bWeight,bTemperature;
	TextView tMetric;
	final DecimalFormat doubleFormat = new DecimalFormat("#.####");
	final static String DATA="DATA",ID="ID";
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_weight);
	    
//	    link widgets to layout
	    etKilo=(EditText) findViewById(R.id.EditTextKilo);
	    etPound=(EditText) findViewById(R.id.EditTextPound);
	    etOunce=(EditText)findViewById(R.id.editTextOunces);
		bDistance=(ImageButton)findViewById(R.id.imageButtonDistance);
		bTemperature=(ImageButton)findViewById(R.id.imageButtonTemperature);
		bWeight=(ImageButton)findViewById(R.id.imageButtonWeight);
		tMetric=(TextView)findViewById(R.id.TextViewKilo);
	    
//	    set the OnClickListener on widgets
		bDistance.setOnClickListener(this);
		bTemperature.setOnClickListener(this);
		bWeight.setOnClickListener(this);
		etKilo.setOnClickListener(this);
	    etPound.setOnClickListener(this);
	    etOunce.setOnClickListener(this);
		tMetric.setOnClickListener(this);
	    
//	    add TextWatcher to widgets
		etKilo.addTextChangedListener(kiloWatcher);
	    etPound.addTextChangedListener(poundWatcher);
	    etOunce.addTextChangedListener(ounceWatcher);
	    
	    tMetric.setText("kg");
	    tMetric.setGravity(Gravity.CENTER);
	    
	}//end onCreate
	
	

public TextWatcher kiloWatcher = new TextWatcher() {

	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyKilo()){
			
			etPound.setText("");
			etOunce.setText("");
			
		}//end if
		
	}//end afterTextChanged

	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyKilo()){
			
			//get number to be converted
			kilo = getKiloInput();
			
			//convert to other units
			pound = kilo*2.20462262;
			ounce = kilo*35.2739619;
			
			//return results up to 2 decimal points
			etPound.setText(doubleFormat.format(pound));
			etOunce.setText(doubleFormat.format(ounce));
			
		}//end if
		
	}//end onTextChanged
	
};	//end kiloWatcher
public TextWatcher poundWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyPounds()){
			
			etOunce.setText("");
			etKilo.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyPounds()){
			
			//get number to be converted
			String input = etPound.getText().toString();
			pound = Double.parseDouble(input);
					
			//convert to other units
			kilo = pound*0.45359237;
			ounce = pound*16;
			
			//return results up to 2 decimal points
			etKilo.setText(doubleFormat.format(kilo));
			etOunce.setText(doubleFormat.format(ounce));
			setKiloInput();
			
		}//end if
		
	}//end onTextChanged
	
};	//end poundWatcher
public TextWatcher ounceWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyOunce()){
			
			etPound.setText("");
			etKilo.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyOunce()){
			
			//get number to be converted
			String input = etOunce.getText().toString();
			ounce = Double.parseDouble(input);
					
			//convert to other units
			kilo = ounce*0.0283495231;
			pound = ounce*0.0625;
			
			//return results up to 2 decimal points
			etKilo.setText(doubleFormat.format(kilo));
			etPound.setText(doubleFormat.format(pound));
			setKiloInput();
			
		}//end if
		
	}//end onTextChanged
	
};	//end ounceWatcher

//end TextWatchers	
//OnClickListener Method
	@Override
	public void onClick(View view) {
		
		Intent intent;
		
		switch (view.getId()) {
		
		case R.id.imageButtonDistance:
			
			intent = new Intent(WeightActivity.this, DistanceActivity.class);
			startActivity(intent);
			finish();
			
			break;
			
		case R.id.imageButtonTemperature:
			
			intent = new Intent(WeightActivity.this, TemperatureActivity.class);
			startActivity(intent);
			finish();
			
			break;
			
		case R.id.imageButtonWeight:
			
			showToast("You are here already!");
			
			break;
			
		case R.id.TextViewKilo:
			
			etKilo.removeTextChangedListener(kiloWatcher);
			
			if(etKilo.getText().toString().length()>0){
				
				setToggleKiloUnit();
				
			}else{
				
				toggleKiloUnit();
				
			}//end if-else
			toggleGravity();
			etKilo.addTextChangedListener(kiloWatcher);

			break;

		default:
			
			//showToast("ID:"+view.getId());
			
			break;
			
		}//end switch-case
		
	}//end OnClick
	
	
	
//	toggleGravity
	public void toggleGravity(){
		
		int left = (Gravity.LEFT|Gravity.CENTER_VERTICAL);
		int right = (Gravity.RIGHT|Gravity.CENTER_VERTICAL);
		int center = (Gravity.CENTER);
		int grav = tMetric.getGravity();
		
		if(grav==left){
			
			tMetric.setGravity(center);
			
		}if(grav==center){
			
			tMetric.setGravity(right);
			
		}if(grav==right){
			
			tMetric.setGravity(left);
			
		}//end ifs
		
	}//end toggleGravity
	
	
//	methods to handle switch from cm-m-km	
	public void toggleKiloUnit(){//User can decide input
		String newUnit = "";
		String current = tMetric.getText().toString();
		if(current.contentEquals("kg")){
			
			newUnit = "t";
			
		}else if(current.contentEquals("t")){
			newUnit = "g";
			
		}else if(current.contentEquals("g")){
			newUnit = "kg";
			
		}//end if-else if-else
		
		tMetric.setText(newUnit);	
		
	}//end togglekiloUnit
	public void setToggleKiloUnit(){//User can decide input
		String newUnit = "";
		double thiskilo=0;
		String current = tMetric.getText().toString();
		double input = getKiloInput();
		if(current.contentEquals("kg")){
			
			newUnit = "t";
			thiskilo = input/1000;
			
		}else if(current.contentEquals("t")){
			newUnit = "g";
			thiskilo = input*1000;
			
		}else if(current.contentEquals("g")){
			newUnit = "kg";
			thiskilo = input;
			
		}//end if-else if-else
		int selection = (doubleFormat.format(thiskilo)).length();
		etKilo.setText(doubleFormat.format(thiskilo));
		tMetric.setText(newUnit);	
		etKilo.setSelection(selection);
		
	}//end setTogglekiloUnit
	
	public double getKiloInput(){
		double thiskilo=0;
		String current = tMetric.getText().toString();
		String putin = etKilo.getText().toString();
		double input = Double.parseDouble(putin);
		
		if(current.contentEquals("kg")){
			
			thiskilo = input;
			
		}else if(current.contentEquals("t")){
			
			thiskilo = input*1000;
			
		}else if(current.contentEquals("g")){
			
			thiskilo = input*0.001;
			
		}//end if-else if-else
		
		return thiskilo;
		
	}//end togglekiloUnit
	public void setKiloInput(){
		
		String newUnit = "";
		double thiskilo=0;
		String current = tMetric.getText().toString();
		String putin = etKilo.getText().toString();
		double input = Double.parseDouble(putin);
		
		int left = (Gravity.LEFT|Gravity.CENTER_VERTICAL);
		int right = (Gravity.RIGHT|Gravity.CENTER_VERTICAL);
		int center = (Gravity.CENTER);
		int grav=0;
		
		if(input>1000){
			
			newUnit = "t";
			thiskilo = input*0.001;
			grav = right;
			
		}else if(input<1){
			
			newUnit = "g";
			thiskilo = input*1000;
			grav = left;
			
		}else{
			
			newUnit = "kg";
			thiskilo = input;
			grav = center;
			
		}//end if-else if-else
		
		etKilo.setText(thiskilo+"");
		tMetric.setText(newUnit);
		tMetric.setGravity(grav);
		
	}//end setKiloInput
//end methods to handle switch from cm-m-km
	
//   booleans that handle the focus of the various EditTexts to avoid stackOverFlow with the TextWatchers 
	public boolean onlyKilo(){
		boolean kilo=false;
		
		if (etKilo.isFocused()) {
			
			boolean pound = etPound.isFocused();
			boolean ounce = etOunce.isFocused();
			
			kilo=!pound&&!ounce;
			
		}//end onlykilos
		
		return kilo;
		
	}//end if
	
	public boolean onlyPounds(){
		
		boolean pound=false;
		
		if (etPound.isFocused()) {
			boolean kilo = etKilo.isFocused();
			boolean ounce = etOunce.isFocused();
			
			pound=!kilo&&!ounce;
			
		}//end if
		
		return pound;
		
	}//end onlypounds
	
	public boolean onlyOunce(){
		
		boolean ounce=false;
		
		if (etOunce.isFocused()) {
			
			boolean pound = etPound.isFocused();
			boolean kilo = etKilo.isFocused();
			
			ounce=!kilo&&!pound;
			
		}//end if
		
		return ounce;
		
	}//end onlyounce
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
    
}//end WeightActivity
