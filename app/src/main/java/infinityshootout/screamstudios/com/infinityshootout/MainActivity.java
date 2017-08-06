package infinityshootout.screamstudios.com.infinityshootout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //String[] values = new String[] {"Android", "iPhone", "caca"};
    ArrayList<String> values = new ArrayList<String>();
    MySimpleArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        values.add("Active troop");
        values.add("ARO troop");

        final ListView listView =(ListView) findViewById(R.id.listview);

        Button addARO_BT = (Button) findViewById(R.id.addARO_BT);
        addARO_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", Integer.toString(values.size()));
                values.add("new troop");
                adapter.notifyDataSetChanged();
            }
        });
         adapter = new MySimpleArrayAdapter(
                this,
                values);
         listView.setAdapter(adapter);
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<String>{
        private final Context context;

        public MySimpleArrayAdapter(Context context, ArrayList<String> values) {
            super(context, -1, values);
            this.context = context;
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
}
