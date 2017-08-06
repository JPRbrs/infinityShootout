package infinityshootout.screamstudios.com.infinityshootout;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView =(ListView) findViewById(R.id.listview);
        String[] values = new String[] {"Android", "iPhone", "caca"};
//
//        final ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < values.length; i++) {
//            list.add(values[i]);
//        }
        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(
                this,
                values);
        listView.setAdapter(adapter);

    }

    public class MySimpleArrayAdapter extends ArrayAdapter<String>{
        private final Context context;

        public MySimpleArrayAdapter(Context context, String[] values) {
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

            // rowView.setBackgroundColor(Color.RED);




            return rowView;
        }
    }
}
