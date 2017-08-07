package infinityshootout.screamstudios.com.infinityshootout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //String[] values = new String[] {"Android", "iPhone", "caca"};
    ArrayList<Troop> values = new ArrayList<Troop>();
    MySimpleArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        values.add(new Troop());
        values.add(new Troop());

        final ListView listView =(ListView) findViewById(R.id.listview);

        Button addARO_BT = (Button) findViewById(R.id.add_ARO);
        addARO_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.add(new Troop());
                adapter.notifyDataSetChanged();
            }
        });

        Button remove_ARO_BT = (Button) findViewById(R.id.remove_ARO);
        remove_ARO_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (values.size() > 2) {
                    values.remove(values.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        Button shoot = (Button) findViewById(R.id.shootBT);
        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "calculate");
                String message = Integer.toString(values.get(0).shoot());
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new MySimpleArrayAdapter(
                this,
                values);
        listView.setAdapter(adapter);
    }

    public class Troop {
        private String weapon;
        private int bs;
        private int total_modifier;

        public Troop() {

        }

        public String getWeapon() {
            return weapon;
        }

        public int getBs() {
            return bs;
        }

        public int getTotal_modifier() {
            return total_modifier;
        }

        public void setTotalModifier(int total_modifier) {
            this.total_modifier = total_modifier;
        }
        public void setWeapon( String weapon) {
            this.weapon = weapon;
        }

        public void setBs(int bs) {
            this.bs = bs;
        }

        public int roll_dice () {
            return  (int) Math.round(Math.random() * 20);
        }

        public int shoot(){
            int roll = roll_dice();
            Log.i("TAG", Integer.toString(roll));
            Log.i("TAG", Integer.toString(this.bs));
            if (roll == this.bs){
                return 0;
            }
            else if (roll > this.bs){
                return -1;
            }
            else {
                return roll;
            }
        }
    }

    public class MySimpleArrayAdapter extends ArrayAdapter {
        private final Context context;
        private ArrayList<Troop> troopers;

        public MySimpleArrayAdapter(Context context, ArrayList<Troop> values) {
            super(context, -1, values);
            this.context = context;
            this.troopers = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
                Spinner weapon = rowView.findViewById(R.id.weapons_spinner);

                String[] weapons = new String[] {"HMG", "Combi Rifle", "Light flamethrower", "Grenades"};
                ArrayAdapter<String> weapons_adapter = new ArrayAdapter<String>(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        weapons);
                weapon.setAdapter(weapons_adapter);

                String[] possible_bs = new String[] {"10", "11", "12", "13", "15", "16"};
                Spinner bs_spinner = rowView.findViewById(R.id.bs_spinner);
                ArrayAdapter<String> bs_adapter = new ArrayAdapter<String>(
                        context,
                        android.R.layout.simple_spinner_dropdown_item,
                        possible_bs);
                bs_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                bs_spinner.setAdapter(bs_adapter);


                final TextView totalMod = rowView.findViewById(R.id.totalMod);
                SeekBar extraModifiers = rowView.findViewById(R.id.modifiersBS);
                extraModifiers.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        int total_mod = (i - 4 ) * 3;
                        String text_mod;
                        if (total_mod > 0) {
                            text_mod = "+ " + Integer.toString(Math.abs(total_mod));
                        }
                        else if (total_mod < 0) {
                            text_mod = " - " + Integer.toString(Math.abs(total_mod));
                        }
                        else {
                            text_mod = Integer.toString(Math.abs(total_mod));
                        }
                        totalMod.setText(text_mod);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

            // rowView.setBackgroundColor(Color.RED);
            return rowView;
        }
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos),
                    Toast.LENGTH_SHORT).show();
            //values.get(pos).setBs(10);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }
}
