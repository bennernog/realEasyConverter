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

public class DistanceActivity extends Activity implements OnClickListener {
	
	
	double meter, miles, feet,  inches, yards;
	EditText etMeter, etMile, etFeet, etInches,etYards;
	ImageButton bDistance,bWeight,bTemperature;
	TextView tMetric;
	final DecimalFormat doubleFormat = new DecimalFormat("#.####");
	final static String DATA="DATA",ID="ID";
	int id;
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_distance);
	    
//	    link widgets to layout
	    etMeter=(EditText) findViewById(R.id.EditTextMeter);
	    etMile=(EditText) findViewById(R.id.EditTextMile);
	    etFeet=(EditText)findViewById(R.id.EditTextFeet);
		etInches=(EditText)findViewById(R.id.EditTextInches);
		etYards=(EditText)findViewById(R.id.editTextYards);
		bDistance=(ImageButton)findViewById(R.id.imageButtonDistance);
		bTemperature=(ImageButton)findViewById(R.id.imageButtonTemperature);
		bWeight=(ImageButton)findViewById(R.id.imageButtonWeight);
		tMetric=(TextView)findViewById(R.id.TextViewMeter);
	    
//	    set the OnClickListener on widgets
		bDistance.setOnClickListener(this);
		bTemperature.setOnClickListener(this);
		bWeight.setOnClickListener(this);
	    etMeter.setOnClickListener(this);
	    etMile.setOnClickListener(this);
	    etFeet.setOnClickListener(this);
		etInches.setOnClickListener(this);
		etYards.setOnClickListener(this);
		tMetric.setOnClickListener(this);
	    
//	    add TextWatcher to widgets
	    etMeter.addTextChangedListener(meterWatcher);
	    etMile.addTextChangedListener(mileWatcher);
	    etFeet.addTextChangedListener(feetWatcher);
		etInches.addTextChangedListener(inchWatcher);
		etYards.addTextChangedListener(yardWatcher);
	    
	    
	    tMetric.setText("m");
	    tMetric.setGravity(Gravity.CENTER);
	    
	}//end onCreate
	
	

public TextWatcher meterWatcher = new TextWatcher() {

	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyMeters()){
			
			etMile.setText("");
			etFeet.setText("");
			etInches.setText("");
			etYards.setText("");
			
		}//end if
		
	}//end afterTextChanged

	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyMeters()){
			
			//get number to be converted
			meter = getMeterInput();
			
			//convert to other units
			miles = (meter* 0.6214)/1000;
			feet = meter*3.281;
			inches= meter*39.37;
			yards = meter/0.9144;
			
			//return results up to 2 decimal points
			etMile.setText(doubleFormat.format(miles));
			etFeet.setText(doubleFormat.format(feet));
			etInches.setText(doubleFormat.format(inches));
			etYards.setText(doubleFormat.format(yards));
			
		}//end if
		
	}//end onTextChanged
	
};	//end meterWatcher
public TextWatcher mileWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyMiles()){
			
			etFeet.setText("");
			etInches.setText("");
			etYards.setText("");
			etMeter.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyMiles()){
			
			//get number to be converted
			String input = etMile.getText().toString();
			miles = Double.parseDouble(input);
					
			//convert to other units
			meter = miles*1609.3;
			feet = miles*5280;
			inches= feet*12;
			yards = feet/3;
			
			//return results up to 2 decimal points
			etMeter.setText(doubleFormat.format(meter));
			etFeet.setText(doubleFormat.format(feet));
			etInches.setText(doubleFormat.format(inches));
			etYards.setText(doubleFormat.format(yards));
			setMeterInput();
			
		}//end if
		
	}//end onTextChanged
	
};	//end mileWatcher
public TextWatcher feetWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyFeet()){
			
			etMile.setText("");
			etInches.setText("");
			etYards.setText("");
			etMeter.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyFeet()){
			
			//get number to be converted
			String input = etFeet.getText().toString();
			feet = Double.parseDouble(input);
					
			//convert to other units
			meter = feet*0.3048;
			miles = feet/5280;
			inches= feet*12;
			yards = feet/3;
			
			//return results up to 2 decimal points
			etMeter.setText(doubleFormat.format(meter));
			etMile.setText(doubleFormat.format(miles));
			etInches.setText(doubleFormat.format(inches));
			etYards.setText(doubleFormat.format(yards));
			setMeterInput();
			
		}//end if
		
	}//end onTextChanged
	
};	//end mileWatcher
public TextWatcher inchWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyInches()){
			
			etMile.setText("");
			etFeet.setText("");
			etYards.setText("");
			etMeter.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyInches()){
			
			//get number to be converted
			String input = etInches.getText().toString();
			inches = Double.parseDouble(input);
					
			//convert to other units
			meter = inches*0.0254;
			feet= inches/12;
			miles = feet/5280;
			yards = feet/3;
			
			//return results up to 2 decimal points
			etMeter.setText(doubleFormat.format(meter));
			etMile.setText(doubleFormat.format(miles));
			etFeet.setText(doubleFormat.format(feet));
			etYards.setText(doubleFormat.format(yards));
			setMeterInput();
			
		}//end if
		
	}//end onTextChanged
	
};	//end mileWatcher
public TextWatcher yardWatcher = new TextWatcher() {
	
	@Override public void afterTextChanged(Editable editable) {
		
		if(editable.length() == 0 && onlyYards()){
			
			etMile.setText("");
			etFeet.setText("");
			etInches.setText("");
			etMeter.setText("");
			
		}//end if
		
	}//end afterTextChanged
	
	@Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	
	@Override
	public void onTextChanged(CharSequence sequence, int start, int before, int count){
		
		if(ignoreMinus(sequence.toString()) && onlyYards()){
			
			//get number to be converted
			String input = etYards.getText().toString();
			yards = Double.parseDouble(input);
					
			//convert to other units
			meter = yards*0.9144;
			feet= yards*3;
			miles = feet/5280;
			inches = feet*12;
			
			//return results up to 2 decimal points
			etMeter.setText(doubleFormat.format(meter));
			etMile.setText(doubleFormat.format(miles));
			etFeet.setText(doubleFormat.format(feet));
			etInches.setText(doubleFormat.format(inches));
			setMeterInput();
			
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
			
			showToast("You are here already!");
			
			break;
			
		case R.id.imageButtonTemperature:
			
			intent = new Intent(DistanceActivity.this, TemperatureActivity.class);
			startActivity(intent);
			finish();
			
			break;
			
		case R.id.imageButtonWeight:
			
			intent = new Intent(DistanceActivity.this, WeightActivity.class);
			startActivity(intent);
			finish();
			
			break;
			
		case R.id.TextViewMeter:
			
			etMeter.removeTextChangedListener(meterWatcher);
			
			if(etMeter.getText().toString().length()>0){
				
				setToggleMeterUnit();
				
			}else{
				
				toggleMeterUnit();
				
			}//end if-else
			toggleGravity();
			
			etMeter.addTextChangedListener(meterWatcher);

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
	public void toggleMeterUnit(){//User can decide input
		String newUnit = "";
		String current = tMetric.getText().toString();
		if(current.contentEquals("m")){
			
			newUnit = "km";
			
		}else if(current.contentEquals("km")){
			newUnit = "cm";
			
		}else if(current.contentEquals("cm")){
			newUnit = "m";
			
		}//end if-else if-else
		
		tMetric.setText(newUnit);	
		
	}//end toggleMeterUnit
	public void setToggleMeterUnit(){//User can decide input
		String newUnit = "";
		double thisMeter=0;
		String current = tMetric.getText().toString();
		double input = getMeterInput();
		if(current.contentEquals("m")){
			
			newUnit = "km";
			thisMeter = input/1000;
			
		}else if(current.contentEquals("km")){
			newUnit = "cm";
			thisMeter = input*100;
			
		}else if(current.contentEquals("cm")){
			newUnit = "m";
			thisMeter = input;
			
		}//end if-else if-else
		int selection = (doubleFormat.format(thisMeter)).length();
		etMeter.setText(doubleFormat.format(thisMeter));
		tMetric.setText(newUnit);	
		etMeter.setSelection(selection);
		
	}//end setToggleMeterUnit
	
	public double getMeterInput(){
		double thisMeter=0;
		String current = tMetric.getText().toString();
		String putin = etMeter.getText().toString();
		double input = Double.parseDouble(putin);
		
		if(current.contentEquals("m")){
			
			thisMeter = input;
			
		}else if(current.contentEquals("km")){
			
			thisMeter = input*1000;
			
		}else if(current.contentEquals("cm")){
			
			thisMeter = input/100;
			
		}//end if-else if-else
		
		return thisMeter;
		
	}//end toggleMeterUnit
	public void setMeterInput(){
		
		String newUnit = "";
		double thisMeter=0;
		String current = tMetric.getText().toString();
		String putin = etMeter.getText().toString();
		double input = Double.parseDouble(putin);
		
		int left = (Gravity.LEFT|Gravity.CENTER_VERTICAL);
		int right = (Gravity.RIGHT|Gravity.CENTER_VERTICAL);
		int center = (Gravity.CENTER);
		int grav=0;
		
		if(input>1000){
			
			newUnit = "km";
			thisMeter = input/1000;
			grav = right;
			
		}else if(input<1){
			
			newUnit = "cm";
			thisMeter = input*100;
			grav = left;
			
		}else{
			
			newUnit = "m";
			thisMeter = input;
			grav = center;
			
		}//end if-else if-else
		
		etMeter.setText(thisMeter+"");
		tMetric.setText(newUnit);
		tMetric.setGravity(grav);
		
	}//end setMeterInput
//end methods to handle switch from cm-m-km
	
//   booleans that handle the focus of the various EditTexts to avoid stackOverFlow with the TextWatchers 
	public boolean onlyMeters(){
		boolean meter=false;
		
		if (etMeter.isFocused()) {
			
			boolean mile = etMile.isFocused();
			boolean feet = etFeet.isFocused();
			boolean inch = etInches.isFocused();
			boolean yard = etYards.isFocused();
			
			meter=!mile&&!feet&&!inch&&!yard;
			
		}//end onlyMeters
		
		return meter;
		
	}//end if
	
	public boolean onlyMiles(){
		
		boolean mile=false;
		
		if (etMile.isFocused()) {
			boolean meter = etMeter.isFocused();
			boolean feet = etFeet.isFocused();
			boolean inch = etInches.isFocused();
			boolean yard = etYards.isFocused();
			
			mile=!meter&&!feet&&!inch&&!yard;
			
		}//end if
		
		return mile;
		
	}//end onlyMiles
	
	public boolean onlyFeet(){
		
		boolean feet=false;
		
		if (etFeet.isFocused()) {
			
			boolean mile = etMile.isFocused();
			boolean meter = etMeter.isFocused();
			boolean inch = etInches.isFocused();
			boolean yard = etYards.isFocused();
			
			feet=!meter&&!mile&&!inch&&!yard;
			
		}//end if
		
		return feet;
		
	}//end onlyFeet
	
	public boolean onlyInches(){
		
		boolean inch=false;
		
		if (etInches.isFocused()) {
			boolean mile = etMile.isFocused();
			boolean meter = etMeter.isFocused();
			boolean feet = etFeet.isFocused();
			boolean yard = etYards.isFocused();
			inch=!meter&&!feet&&!mile&&!yard;
		}//end if
		
		return inch;
		
	}//end onlyInches
	
	public boolean onlyYards(){
		
		boolean yard=false;
		
		if (etYards.isFocused()) {
			
			boolean mile = etMile.isFocused();
			boolean meter = etMeter.isFocused();
			boolean feet = etFeet.isFocused();
			boolean inch = etInches.isFocused();
			
			yard=!meter&&!feet&&!inch&&!mile;
			
		}//end if
		
		return yard;
		
	}//end onlyYards
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
