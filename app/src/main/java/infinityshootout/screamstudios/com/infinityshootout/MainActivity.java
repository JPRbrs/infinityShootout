package infinityshootout.screamstudios.com.infinityshootout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        String[] values = new String[] {"Android", "iPhone", "Windows Mobile"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }
        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(
                this,
                values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id){
                        final String item = (String) parent.getItemAtPosition(position);
                        view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
                    }
        });
    }

    public class MySimpleArrayAdapter extends ArrayAdapter<String>{
        private final Context context;
        private final String[] values;

        public MySimpleArrayAdapter(Context context, String[] values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
//            Spinner weapon = (Spinner)findViewById(R.id.weapons);
//            Spinner bs = (Spinner) findViewById(R.id.bs);
//            TextView modifiersTV = (TextView) findViewById(R.id.modifiersTV);
//            SeekBar modifiersSB = (SeekBar) findViewById(R.id.modifiersSB);
//
//            String[] weapons = new String[] {"HMG", "Combi Rifle", "Light flamethrower", "Grenades"};
//            ArrayAdapter<String> weapons_adapter = new ArrayAdapter<String>(
//                    this,
//                    android.R.layout.simple_spinner_dropdown_item,
//                    weapons);
//            weapon.setAdapter(weapons_adapter);

            return rowView;
        }
    }
}
