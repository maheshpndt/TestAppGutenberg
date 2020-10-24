package com.example.gutendex.base_class;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gutendex.R;

abstract public class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    public void showLoading() {
        if (this.getContext()!=null) {
            hideLoading();
            mProgressDialog = showLoadingDialog(this.getContext());
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    /**
     * show toast message
     * @param context
     * @param message
     */
    public void showMessage(Context context , String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @return : ProgressDialog
     * @Function : showLoadingDialog()
     * @params : Context:context
     * @Usage : Get ProgressDialog instance for displaying ProgressDialog on UI.
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        try {
            progressDialog.show();
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e){}
        return progressDialog;
    }

    /**
     * check if the application is in portait on landscape
     * @return
     */
    public boolean isPortrait(){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * fragment call
     * @param fragment
     */
    public void callFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

    /**
     * check the internet connection
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }

    /**
     * Use to show alert dialog
     * @param context
     * @param message : a message to show on dialog box
     */
    public void showAlert(Context context,String message){
        new AlertDialog.Builder(context)
                .setMessage(message)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
