package in.journeywheels.www.jw;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    TextView login_reg;
    ImageView signup_reg;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_signup, container, false);

//        login_reg=(TextView)view.findViewById(R.id.login_reg);
//        login_reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                LoginFragment frag1=new LoginFragment();
//                fragmentTransaction.replace(R.id.log,frag1);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//
//        signup_reg=(ImageView) view.findViewById(R.id.signup_reg_btn);
//        signup_reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Siginup2Fragment frag1=new Siginup2Fragment();
//                fragmentTransaction.replace(R.id.log,frag1);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });

        return view;

    }

}
