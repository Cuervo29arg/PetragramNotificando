package py.com.mcespedes.petagram;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by root on 29/12/16.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);

    }

    private void enviarTokenRegistro(String token){

        Log.d(TAG,token);

    }

}
