package com.brobwind.bronil;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

import android.util.Log;


public class NilActivity extends Activity {
    private final static String TAG = "NilActivity";

    private TextView mInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mInfo = (TextView)findViewById(R.id.info);

        Intent intent = new Intent(this, ProxyService.class);
        startService(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent intent = new Intent(this, ProxyService.class);
        bindService(intent, mProxyConnection,
            Context.BIND_AUTO_CREATE | Context.BIND_NOT_FOREGROUND | Context.BIND_NOT_VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBound) {
            unbindService(mProxyConnection);
        }
    }

    private boolean mBound;
    private ServiceConnection mProxyConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName component) {
            mBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName component, IBinder binder) {
            IProxyCallback callbackService = IProxyCallback.Stub.asInterface(binder);
            if (callbackService != null) {
                try {
                    callbackService.getProxyPort(new IProxyPortListener.Stub() {
                        @Override
                        public void setProxyPort(final int port) throws RemoteException {
                            if (port != -1) {
                                Log.d(TAG, "Local proxy is bound on " + port);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mInfo.setText("http://127.0.0.1:" + port);
                                    }
                                });
                            } else {
                                Log.e(TAG, "Received invalid port from Local Proxy,"
                                        + " PAC will not be operational");
                            }
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            mBound = true;
        }
    };
}
