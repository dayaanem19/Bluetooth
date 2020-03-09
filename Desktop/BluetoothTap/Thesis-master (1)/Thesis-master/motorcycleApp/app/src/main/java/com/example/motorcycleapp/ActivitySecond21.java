package com.example.motorcycleapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Set;

public class ActivitySecond21 extends AppCompatActivity {

    private Button searchBtn;
    private Button nextBtn;
    private ImageView backBtn;
    private Switch bluetoothSwitch;
    private BluetoothAdapter myBluetoothAdapter;
    private ListView myListView;
    private TextView pDeviceName;
    private Set<BluetoothDevice> pairedDevices;
    private ArrayAdapter<String> BTArrayAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();

    //to disable the functionality of back button in android phones
    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On Screen load
        setContentView(R.layout.activity_second_screenv21);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityThirdV2();
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMainV2();
            }
        });

        myListView = findViewById(R.id.myListView);
        pDeviceName = (TextView) findViewById(R.id.pDeviceName);

        // create the arrayAdapter that contains the BTDevices, and set it to the ListView
        BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myListView.setAdapter(BTArrayAdapter);

        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(myBluetoothAdapter == null) {
            nextBtn.setEnabled(false);
            searchBtn.setEnabled(false);

            //pop up message
            Toast.makeText(getApplicationContext(),"Your device does not support Bluetooth",
                    Toast.LENGTH_LONG).show();
        } else {
            mBTDevices = new ArrayList<>();
            bluetoothSwitch = findViewById(R.id.bluetoothSwitch);

            if (myBluetoothAdapter.isEnabled()) {
                bluetoothSwitch.setChecked(true);
            }

            Log.d("SAMPLE", String.valueOf(bluetoothSwitch.isChecked()));

            // If switch is already turned on or bluetooth is already enabled
            if (bluetoothSwitch.isChecked()) {
                // Search devices button
                searchBtn = findViewById(R.id.searchBtn);
                searchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchDevices(v);
                    }
                });

                // On discovered item click
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        // Broadcasts receiver to detect if device has paired, pairing, or not.
                        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
                        registerReceiver(mBroadcastReceiver4, filter);

                        String selectedItem = (String) parent.getItemAtPosition(position);
                        pairedDevices = myBluetoothAdapter.getBondedDevices();

                        for(BluetoothDevice bt : pairedDevices)
                            Log.v("SAMPLE", "Paired device name: " + bt.getName());

                        Log.v("SAMPLE", "Item selected: " + selectedItem);
                        if (mBTDevices.size() > 0) {
                            pairDevice(position, selectedItem);
                        }
                        else {
                            Toast.makeText(ActivitySecond21.this, "unable to connect", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            // If Bluetooth is already on.
            if (myBluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(),"Bluetooth is already on",
                        Toast.LENGTH_LONG).show();
            }

            // On switch click
            bluetoothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("SAMPLE", "ON CLICK SWITCH!");

                    // If switch is turned on
                    if (isChecked) {
                        Log.d("SAMPLE", "Setting Switch to on!");
                        on(buttonView);

                        // Search devices button
                        searchBtn = findViewById(R.id.searchBtn);
                        searchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                searchDevices(v);
                            }
                        });

                        // On discovered item click
                        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = (String) parent.getItemAtPosition(position);
                                Log.v("SAMPLE", "Item selected: " + selectedItem);
                            }
                        });

                    } else {
                        Log.d("SAMPLE", "Setting Switch to off!");
                        off(buttonView);
                    }
                }
            });

        }

    }

    public void openActivityThirdV2() {
        Intent intent = new Intent(this, ActivityThirdV2.class);
        startActivity(intent);

        // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
    }

    public void openActivityMainV2() {
       Intent intent = new Intent(this, ActivityMainV2.class);
        startActivity(intent);

        // NOTE: To success fully connect to screen, declare class name in AndroidManifest.xml
    }

    final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name and the MAC address of the object to the arrayAdapter
                mBTDevices.add(device);
                BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                BTArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    public void searchDevices(View v) {
        Log.d("SAMPLE", "Inside search devices!");
        Log.d("SAMPLE", String.valueOf(myBluetoothAdapter.isDiscovering()));

        myBluetoothAdapter.cancelDiscovery();
        BTArrayAdapter.clear();
        myBluetoothAdapter.startDiscovery();
        registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    public void on(View view){
        if (!myBluetoothAdapter.isEnabled()) {
            myBluetoothAdapter.enable();
            Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);

            Toast.makeText(getApplicationContext(),"Bluetooth turned on" ,
                    Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Bluetooth is already on",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void off(View view){
        myBluetoothAdapter.disable();

        Toast.makeText(getApplicationContext(),"Bluetooth turned off",
                Toast.LENGTH_LONG).show();
    }
    private void pairDevice(int position, String selectedItem) {
        Log.d("TAG", "onItemClick: You Clicked on a device.");
        String deviceName = mBTDevices.get(position).getName();
        String deviceAddress = mBTDevices.get(position).getAddress();

        Log.d("TAG", "onItemClick: deviceName = " + deviceName);
        Log.d("TAG", "onItemClick: deviceAddress = " + deviceAddress);

        //create the bond.
        //NOTE: Requires API 17+? I think this is JellyBean
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Log.v("TAG", "Trying to pair with " + deviceName);
            mBTDevices.get(position).createBond();
        }
    }

    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d("TAG", "onReceive: ACTION FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d("TAG", "onReceive: " + device.getName() + ": " + device.getAddress());
            }
        }
    };

    private final BroadcastReceiver mBroadcastReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if(action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case1: bonded already
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED){
                    Log.v("TAG", "BroadcastReceiver: BOND_BONDED.");

                    pDeviceName.setVisibility(View.VISIBLE);
                    String deviceName = mDevice.getName();
                    pDeviceName.setText(deviceName);

                    //pop up message
                    Toast.makeText(getApplicationContext(),"Your device has successfully paired!",
                            Toast.LENGTH_LONG).show();
                }
                //case2: creating a bone
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.v("TAG", "BroadcastReceiver: BOND_BONDING.");

                    //pop up message
                    Toast.makeText(getApplicationContext(),"Your device is pairing...",
                            Toast.LENGTH_LONG).show();
                }
                //case3: breaking a bond
                if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.v("TAG", "BroadcastReceiver: BOND_NONE.");

                    //pop up message
                    Toast.makeText(getApplicationContext(),"Your device has not paired.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(bReceiver);
        unregisterReceiver(mBroadcastReceiver3);
        unregisterReceiver(mBroadcastReceiver4);
    }
}
