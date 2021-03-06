package mingun.trainerinmyhand;

import java.util.ArrayList;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FoodInput extends Activity implements OnItemSelectedListener,OnClickListener{

	EditText edtFood;
	Spinner spnFood;
	Button btnFood,btnFoodAdd;
	int selected;
	int foodcount;
	public static ArrayList<String> food=new ArrayList();
	public static ArrayList<Integer> cal=new ArrayList();
	public static ArrayList<String> ifood=new ArrayList();
	public static ArrayList<Integer> iamount=new ArrayList();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_input);
		edtFood=(EditText)findViewById(R.id.edtFood);
		spnFood=(Spinner)findViewById(R.id.spnFood);
		btnFood=(Button)findViewById(R.id.btnFoodIn);
		btnFood.setOnClickListener(this);
		btnFoodAdd=(Button)findViewById(R.id.btnFoodAdd);
		btnFoodAdd.setOnClickListener(this);
		
		SharedPreferences pref=getSharedPreferences("TrainerInMyHand", MODE_PRIVATE);
		foodcount=pref.getInt("foodCount", 0);
		for(int i=0;i<foodcount;i++){
			food.add(pref.getString("food"+i, ""));
			cal.add(pref.getInt("cal"+i, 0));
		}
		ArrayAdapter aa=new ArrayAdapter(this, android.R.layout.simple_spinner_item, food);
		spnFood.setAdapter(aa);
		spnFood.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.food_input, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> av, View v, int pos, long resId) {
			selected=pos;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View v) {
		Button btn=(Button)v;
		if(btn==btnFood){
			TrainerInMyHand.nowEat+=cal.get(selected)*Integer.valueOf(edtFood.getText().toString());
			ifood.add(food.get(selected).toString());
			iamount.add(Integer.valueOf(edtFood.getText().toString()));
		}
		else if(btn==btnFoodAdd){
			Intent intent=new Intent(FoodInput.this,FoodAdd.class);
			startActivity(intent);
		}
	}

}
